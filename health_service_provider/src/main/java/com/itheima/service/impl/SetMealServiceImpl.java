package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetMealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Value("${out_put_path}")
    private String outPutPath;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        //添加检查套餐基本信息
        setMealDao.add(setmeal);
        //将检查套餐的图片信息保存到redis
        if(setmeal.getImg()!=null&&setmeal.getImg().length()>0){
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        }
        //设置套餐和检查组之间的关联
        for (Integer checkGroupId : checkGroupIds) {
            setMealDao.setSetMealAndCheckGroup(setmeal.getId(), checkGroupId);
        }
        //更新静态页面
        generateMobileStaticHtml();
    }

    /**
     * 生成静态页面
     */
    public void generateMobileStaticHtml(){
        //准备模板文件中所需的数据
        List<Setmeal> sealList = getAllSetMeal();
        //生成套餐列表静态页面
        generateMobileSetMealListHtml(sealList);
        //生成套餐详情静态页面（多个)
        generateMobileSetMealDetailHtml(sealList);
    }
    /**
     * 生成套餐页面
     * @param setMealList
     */
    public void generateMobileSetMealListHtml(List<Setmeal> setMealList){
        Map<String,Object> map=new HashMap<>();
        map.put("setmealList",setMealList);
        generateHtml("mobile_setmeal.ftl","m_setmeal.html",map);
    }
    /**
     *生成套餐详情页面(多个)
     * @param setMealList
     */
    public void generateMobileSetMealDetailHtml(List<Setmeal> setMealList){
        if (setMealList!=null&&setMealList.size()>0){
            for (Setmeal setmeal : setMealList) {
                Map<String,Object> map=new HashMap<>();
                map.put("setmeal",this.findDetailById(setmeal.getId()));
                generateHtml("mobile_setmeal_detail.ftl","setmeal_detail_"+setmeal.getId()+".html",map);
            }
        }
    }
    /**
     * 通用方法:生成静态页面
     * @param templateName
     * @param htmlPageName
     * @param dataMap
     */
    public void generateHtml(String templateName, String htmlPageName, Map<String, Object> dataMap){
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Writer writer=null;
        try {
            Template template = configuration.getTemplate(templateName);
            writer=new FileWriter(new File(outPutPath + "\\" + htmlPageName));
            template.process(dataMap,writer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer!=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //开启分页查询
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //封装分页结果
        Page<Setmeal> page = setMealDao.queryPage(queryPageBean.getQueryString());
        //返回分页结果
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetMealId(Integer id) {
        return setMealDao.findCheckGroupIdsBySetMealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkGroupIds) {
        //修改检查套餐的基本信息
        setMealDao.edit(setmeal);
        //删除检查套餐所包含的检查组
        setMealDao.deleteAssociation(setmeal.getId());
        //重新设置更新此套餐所包含的检查组信息
        for (Integer checkGroupId : checkGroupIds) {
            setMealDao.setSetMealAndCheckGroup(setmeal.getId(), checkGroupId);
        }
        //更新静态页面
        generateMobileStaticHtml();
    }

    @Override
    public void delete(Integer id, String imgName) {
        //删除检查套餐包含的检查组
        setMealDao.deleteAssociation(id);
        //从Redis集合中删除图片名称
        jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, imgName);
        //删除此检查套餐
        setMealDao.delete(id);
        //更新静态页面
        generateMobileStaticHtml();
    }

    @Override
    public List<Setmeal> getAllSetMeal() {
        return setMealDao.getAllSetMeal();
    }

    @Override
    public Setmeal findDetailById(int id) {
        return setMealDao.findDetailById(id);
    }

    @Override
    public List<Map<String, Object>> getSetMealCount() {
        return setMealDao.getSetMealCount();
    }
}
