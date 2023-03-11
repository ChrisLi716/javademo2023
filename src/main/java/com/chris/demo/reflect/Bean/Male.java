package com.chris.demo.reflect.Bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 12:43
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Male extends HumanBeing {

    public static final Class CLZ_TYPE = Male.class;

    static {
        System.out.println("init static block for " + CLZ_TYPE.getTypeName());
    }

    {
        System.out.println("init block for " + CLZ_TYPE.getTypeName());
    }

    public static void desc() {
        System.out.println("init static method for " + CLZ_TYPE.getTypeName());
    }

    public Male() {
        System.out.println("init default constructor for " + CLZ_TYPE.getTypeName());
    }

    public Male(String name, String address) {
        super(name, address);
        System.out.println("init default constructor for " + CLZ_TYPE.getTypeName());
    }

    public Male(String name, String address, String gendar) {
        super(name, address, gendar);
        System.out.println("init full paramters constructor for " + CLZ_TYPE.getTypeName());
    }

    private List<String> carList;

}
