package com.chris.demo.hutool;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

/**
 * @Author Lilun
 * @Date 2020-11-19 15:15
 * @Description
 **/
public class DateUtilTest {


    @Test
    public void testDate(){
        String format = DateUtil.format(new Date(), "yyyyMMddHHmmss.S");
        System.out.println(format);
    }


}
