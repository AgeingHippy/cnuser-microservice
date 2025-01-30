package com.ageinghippy.cnuser_microservice.service;

import com.ageinghippy.cnuser_microservice.model.Item;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

@Service
public class TestService {

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    RestTemplate balancedRestTemplate;

    public List<Item> getAllItems() {
        ResponseEntity<Item[]> response =
                restTemplate.getForEntity(itemServiceUrl() + "/item", Item[].class);
        if (!HttpStatus.valueOf(response.getStatusCode().value()).is2xxSuccessful()) {
           throw new RuntimeException(response.getStatusCode().toString() + " error fetching items");
        }
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    public Item getItem(Long id) {
        ResponseEntity<Item> response =
                balancedRestTemplate.getForEntity("http://CN-ITEM-MICROSERVICE/item/"+id, Item.class);
        if (!HttpStatus.valueOf(response.getStatusCode().value()).is2xxSuccessful()) {
            throw new RuntimeException(response.getStatusCode().toString() + " error fetching items");
        }
        return Objects.requireNonNull(response.getBody(),"No item returned");
    }

    public String itemServiceUrl() {
        InstanceInfo instance =
                eurekaClient.getNextServerFromEureka(
                        "CN-ITEM-MICROSERVICE", false);
        return instance.getHomePageUrl();
    }

}
