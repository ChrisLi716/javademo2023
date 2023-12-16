package com.chris.design.pattern.crp.dynamic;

public class GatewayHandlerEnumFactory {

    private static final GatewayDao gatewayDao = new GatewayDaoImpl();

    // 提供静态方法，获取第一个handler
    public static GatewayHandler getFirstGatewayHandler() {

        GatewayEntity firstGatewayEntity = gatewayDao.getFirstGatewayEntity();
        GatewayHandler firstGatewayHandler = newGatewayHandler(firstGatewayEntity);
        if (firstGatewayHandler == null) {
            return null;
        }

        GatewayEntity tmpGatewayEntity = firstGatewayEntity;
        Integer nextHandlerId = null;
        GatewayHandler tmpGatewayHandler = firstGatewayHandler;
        // 迭代遍历所有handler，以及将它们链接起来
        while ((nextHandlerId = tmpGatewayEntity.getNextHandlerId()) != null) {
            GatewayEntity gatewayEntity = gatewayDao.getGatewayEntity(nextHandlerId);
            GatewayHandler gatewayHandler = newGatewayHandler(gatewayEntity);
            assert tmpGatewayHandler != null;
            tmpGatewayHandler.setNext(gatewayHandler);
            tmpGatewayHandler = gatewayHandler;
            tmpGatewayEntity = gatewayEntity;
        }
        // 返回第一个handler
        return firstGatewayHandler;
    }

    /**
     * 反射实体化具体的处理者
     *
     * @param gatewayEntity GatewayEntity
     * @return GatewayHandler
     */
    private static GatewayHandler newGatewayHandler(GatewayEntity gatewayEntity) {
        // 获取全限定类名
        String className = gatewayEntity.getConference();
        try {
            // 根据全限定类名，加载并初始化该类，即会初始化该类的静态段
            Class<?> clazz = Class.forName(className);
            return (GatewayHandler) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
