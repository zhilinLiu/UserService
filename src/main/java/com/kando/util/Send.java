package com.kando.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/*发送短信模板*/
public class Send {
    public Boolean SendSms(String phone) {
        Boolean bool = false;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrSHjXVDhBqGHroXmzw", "q4UGSwP5ViyjM5QHdLLvBWCth0wRkW");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "MEIO");
        request.putQueryParameter("TemplateCode", "SMS_176524110");
        request.putQueryParameter("TemplateParam", "{\"code\":\"4567\"}                           ");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            bool = true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return bool;
    }
}