package com.chris.design.pattern.state;

import org.junit.Test;

public class Client {


    @Test
    public void testLift01() {
        Context context = new Context();
        context.setLiftState(Context.CLOSING_STATE);
        context.open();
        context.close();
        context.run();
        context.stop();


    }

}
