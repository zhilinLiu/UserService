/*
 * 项目名称:platform-plus
 * 类名称:SysLog.java
 * 包名称:com.platform.common.annotation
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    观度科技      初版做成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.kando.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志注解
 *
 * @author 观度科技
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    String value() default "操作日志";
}
