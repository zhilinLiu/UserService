package com.kando.entity;

import lombok.Data;
//短信接口结果实体类
@Data
public class SendSms {
    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;

}
