package com.chris.demo.reflect.Bean;

import lombok.Data;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 12:41
 * @Description
 */
@Data
public class HumanBeing {

    public static void desc() {
        System.out.println("init static method for " + CLZ_TYPE.getTypeName());
    }

    public static final Class CLZ_TYPE = HumanBeing.class;

    static {
        System.out.println("init static block for " + CLZ_TYPE.getTypeName());
    }

    {
        System.out.println("init block for " + CLZ_TYPE.getTypeName());
    }

    HumanBeing() {
        System.out.println("init default constructor for " + CLZ_TYPE.getTypeName());
    }

    HumanBeing(String name, String address) {
        this.name = name;
        this.address = address;
        System.out.println("init default constructor for " + CLZ_TYPE.getTypeName());
    }

    HumanBeing(String name, String address, String gendar) {
        System.out.println("init full paramters constructor for " + CLZ_TYPE.getTypeName());
        this.name = name;
        this.address = address;
        this.gendar = gendar;
    }

    private String name;

    private String address;

    private String gendar;

}
