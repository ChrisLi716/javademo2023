package com.chris.design.pattern.crp.dynamic;

public class GetewayClient {
    public static void main(String[] args) {
        GatewayHandler firstGetewayHandler = GatewayHandlerEnumFactory.getFirstGatewayHandler();
        assert firstGetewayHandler != null;
        firstGetewayHandler.service();
    }
}
