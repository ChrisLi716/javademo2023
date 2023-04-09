package com.chris.demo.polymorphism;

public class FemalePeople extends AbstracePeople {

    void whoAmI(String name) {
        super.whoAmI(name);
    }

    @Override
    public void sayName(String name) {
        System.out.println("Jenny");
    }
}
