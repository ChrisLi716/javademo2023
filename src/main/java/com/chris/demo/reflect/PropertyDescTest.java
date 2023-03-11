package com.chris.demo.reflect;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.chris.demo.reflect.Bean.HumanBeing;
import com.chris.demo.reflect.Bean.Male;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author Lilun
 * @Date 2021-04-06 15:12
 * @Description
 **/
@Slf4j
public class PropertyDescTest {

    @Test
    public void buildPropertyDesc() {
        HumanBeing male = new Male("Chris", "SZ");
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] propertyDescriptors = propertyUtilsBean.getPropertyDescriptors(male);
        System.out.println(JSONUtil.toJsonPrettyStr(propertyDescriptors));

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            Class<?> propertyType = propertyDescriptor.getPropertyType();

            JSONObject obj = JSONUtil.createObj();
            obj.set("name", name);
            obj.set("readMethod", readMethod);
            obj.set("writeMethod", writeMethod);
            obj.set("propertyType", propertyType);

            String s = JSONUtil.toJsonPrettyStr(obj);
            System.out.println(s);
        }

    }

    @Test
    public void testCopyValueForSameProperty() {
        HumanBeing male = new Male("Chris", "SZ");
        Male m = null;
        try {
            m = copyValueForSameProperty(male, Male.class);
            log.info("Male:{}", m);
        } catch (Exception e) {
            log.error("error happened when copy propeties", e);
        }
    }


    private <T> T copyValueForSameProperty(Object source, Class<T> targetClz) throws Exception {
        if (Objects.isNull(source)) {
            return null;
        }

        T target = targetClz.newInstance();

        // 获取source的所有属性及方法
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(source);

        for (PropertyDescriptor propItem : descriptors) {
            // 获取某一个属性的全部信息
            // 过滤setclass/getclass属性
            if ("class".equals(propItem.getName())) {
                continue;
            }
            try {
                // 通过get方法获取对应属性的值
                Method method = propItem.getReadMethod();
                Object val = method.invoke(source);
                // 如果是空，不做处理
                if (null == val) {
                    continue;
                }
                // 值复制,调用写方法，设置值
                // 获取target的当前属性propItem.getName()的信息
                PropertyDescriptor prop = propertyUtilsBean.getPropertyDescriptor(target, propItem.getName());
                if (null != prop && prop.getWriteMethod() != null) {
                    prop.getWriteMethod().invoke(target, val);
                }
            } catch (Exception e) {
                log.error("复制出错prop : " + propItem.getDisplayName() + ", source class: " + source.getClass()
                        + ";target type :" + targetClz, e);
            }
        }
        return target;
    }
}
