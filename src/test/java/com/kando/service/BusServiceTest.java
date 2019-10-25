package com.kando.service;

import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrNounEntity;
import com.kando.service.fegin.PrDictGroupService;
import com.kando.service.fegin.PrDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusServiceTest {
    @Autowired
    private PrDictGroupService prDictGroupService;
    @Autowired
    private PrDictService service;
    @Test
    public void test(){
        PrNounEntity prNounEntity = new PrNounEntity();
        prNounEntity.setId("3");
        prNounEntity.setName("hhzzasasa");
        prNounEntity.setPrExplain("sasaaaaaaaaaaaaaaasas");

    }

    @Test
    public void test1(){
        JSONResponse info = service.info("1");
        System.out.println(info);
    }
}