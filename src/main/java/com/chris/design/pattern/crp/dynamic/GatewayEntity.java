package com.chris.design.pattern.crp.dynamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayEntity {
    private String name;

    private String conference;

    private Integer handlerId;

    private Integer preHandlerId;

    private Integer nextHandlerId;


    public GatewayEntity(Integer handlerId, String name, String conference, Integer preHandlerId, Integer nextHandlerId) {
        this.handlerId = handlerId;
        this.name = name;
        this.conference = conference;
        this.preHandlerId = preHandlerId;
        this.nextHandlerId = nextHandlerId;
    }
}
