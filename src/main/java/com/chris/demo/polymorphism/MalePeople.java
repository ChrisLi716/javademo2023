package com.chris.demo.polymorphism;

import java.sql.SQLOutput;

public class MalePeople extends AbstracePeople {

    void whoAmI(String name) {
        super.whoAmI(name);
    }

    @Override
    public void sayName(String name) {
        System.out.println("male people say name");
        System.out.println(name);
    }
}
