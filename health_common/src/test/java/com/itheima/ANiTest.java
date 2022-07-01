package com.itheima;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;


public class ANiTest {
    public static final String VALIDATE_CODE = "SMS_159620392";//发送短信验证码
    public static final String ORDER_NOTICE = "SMS_159771588";//体检预约成功通知
    private static final String ACCESS_KEY_Id = "LTAI5tFUDFXi5r6GtCGtk6Pb";//accessKeyId
    private static final String ACCESS_KEY_SECRET = "jzxKmOZd1mDyHL2vrEVvADzByDNCKN";//accessKeySecret

    public static void sendShortMessage(String  phoneNumbers,String param) throws Exception {
        Client client = createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phoneNumbers)
                .setTemplateParam("{\"code\":\""+param+"\"}");
        RuntimeOptions runtime = new RuntimeOptions();
        SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(Common.toJSONString(TeaModel.buildMap(resp)));
    }
    /**
     * 使用AK&SK初始化账号Client
     * @return
     * @throws Exception
     */
    public static Client createClient() throws Exception {
        Config config = new Config().setAccessKeyId(ACCESS_KEY_Id).setAccessKeySecret(ACCESS_KEY_SECRET);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
