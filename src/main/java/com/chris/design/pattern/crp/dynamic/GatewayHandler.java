package com.chris.design.pattern.crp.dynamic;

import lombok.Data;

public abstract class GatewayHandler {

    protected GatewayHandler next;

    public void setNext(GatewayHandler next) {
        this.next = next;
    }

    public abstract void service();


}
