package com.chris.design.pattern.crp.dynamic;

import com.chris.design.pattern.crp.dynamic.GatewayEntity;

public interface GatewayDao {
    /**
     * 根据 handlerId 获取配置项
     *
     * @param handlerId
     * @return GatewayEntity
     */
    GatewayEntity getGatewayEntity(Integer handlerId);

    /**
     * 获取第一个处理者
     *
     * @return GatewayEntity
     */
    GatewayEntity getFirstGatewayEntity();
}
