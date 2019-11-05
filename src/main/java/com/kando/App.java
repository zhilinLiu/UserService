package com.kando;

import com.kando.common.IdWorker;
import com.kando.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
@MapperScan("com.kando.dao")
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableCaching
@ImportResource(locations={"classpath:kaptcha.xml"}) //支持图片验证码
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
        User user = new User();
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
