package com.chris.demo.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import com.chris.demo.entities.Employee;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lilun
 * @Date 2020-11-03 10:43
 * @Description
 **/
public class BeanUtilTest {

    private Employee employee = new Employee(1, "Chris", 23, 23000.43);

    @Test
    public void copyBean() {
        Employee employee2 = new Employee();
        BeanUtil.copyProperties(employee, employee2, "name", "age");
        System.out.println(employee2.toString());
    }


    @Test
    public void bean2Map() {
        Map<String, Object> map = MapUtil.newHashMap();
        BeanUtil.copyProperties(employee, map);
        Map<String, Object> map2 = BeanUtil.beanToMap(employee);
        System.out.println(map.toString());
        System.out.println(map2);
    }


    @Test
    public void map2Bean() {
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("m_id", 455);
        map.put("m_name", "Wanderful");
        map.put("m_age", 12);
        map.put("salary", 12.23);

        // 设置别名，用于对应bean的字段名
        HashMap<String, String> mapping = MapUtil.newHashMap();
        mapping.put("m_id", "id");
        mapping.put("m_name", "name");
        mapping.put("m_age", "age");

        Employee employee = BeanUtil.mapToBean(map, Employee.class, false, CopyOptions.create().setFieldMapping(mapping));
        Employee employee2 = BeanUtil.toBean(map, Employee.class, CopyOptions.create().setFieldMapping(mapping).setIgnoreCase(true));
        System.out.println(employee.toString());
        System.out.println(employee2.toString());
    }

    @Test
    public void map2BeanWithAlias() {
        PersonWithAlias personWithAlias = new PersonWithAlias();
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("name", "Chris");
        map.put("aliasSubName", "Chris");

        PersonWithAlias personWithAlias1 = BeanUtil.mapToBean(map, PersonWithAlias.class, false, CopyOptions.create().setIgnoreCase(true));
        System.out.println(personWithAlias1);
    }

    @Test
    public void bean2MapWithAlias() {
        PersonWithAlias personWithAlias = new PersonWithAlias();
        personWithAlias.setName("Chris");
        personWithAlias.setSubName("Paul");

        Map<String, Object> map = BeanUtil.beanToMap(personWithAlias);
        System.out.println(map);

    }


}
