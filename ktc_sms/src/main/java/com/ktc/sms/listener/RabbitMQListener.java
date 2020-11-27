package com.ktc.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.ktc.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/11/26 18:02
 * @Description
 */
@Component
@RabbitListener(queues = "sms")
public class RabbitMQListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    String template;

    @Value("${aliyun.sms.sign_name}")
    String singName;

    @RabbitHandler
    public void sendCode(Map<String,String> map) throws Exception {
        String code = map.get("code");
        String mobile = map.get("mobile");

        System.out.println(code);  //json
        System.out.println(mobile);
        String param="{\"code\":\""+code+"\"}";
        SendSmsResponse response = smsUtil.sendSms(mobile, template, singName, param);
        System.out.println(response.getCode());
        System.out.println(response.getMessage());

    }

}
