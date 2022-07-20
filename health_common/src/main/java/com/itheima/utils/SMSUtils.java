package com.itheima.utils;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
/**
 * 短信发送工具
 */
public class SMSUtils {

	public static final String VALIDATE_CODE = "SMS_154950909";//发送短信验证码(阿里云测试通知)
	public static final String SHORT_MESSAGE_TEST = "阿里云短信测试";//发送短信验证码(阿里云测试通知)
	public static final String DOMAIN_NAME_VISITED = "dysmsapi.aliyuncs.com";//访问的域名
	private static final String ACCESS_KEY_Id = "";//accessKeyId
	private static final String ACCESS_KEY_SECRET = "";//accessKeySecret
	public static final String ORDER_NOTICE = "SMS_159771588";//体检预约成功通知

	public static void sendShortMessage(String  phoneNumbers,String param) throws Exception {
		Client client = createClient();
		SendSmsRequest sendSmsRequest = new SendSmsRequest()
				.setSignName(SHORT_MESSAGE_TEST)
				.setTemplateCode(VALIDATE_CODE)
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
		config.endpoint = DOMAIN_NAME_VISITED;
		return new Client(config);
	}
}
