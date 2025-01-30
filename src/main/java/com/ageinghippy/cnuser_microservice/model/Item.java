package com.ageinghippy.cnuser_microservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item {

    private Long itemId;

    private String name;

    private String description;
}
