package com.chris.demo.inner;

import lombok.Getter;

@Getter
public class Outer {
    private String name = "outer";
    private String outer = "I'm outer";
    private Inner innerInstance;

    public Inner getInnerInstance() {
        if (null == innerInstance) {
            innerInstance = new Inner();
    }
        return innerInstance;
    }


    @Getter
    class Inner {

        private String name = "inner";

        public Inner() {
            System.out.println("construct inner cls");
            System.out.println("outer:" + outer + ", " + name);

            // if you wanna visit outer'name
            System.out.println("outer:" + outer + ", " + Outer.this.name);
        }
    }

}
