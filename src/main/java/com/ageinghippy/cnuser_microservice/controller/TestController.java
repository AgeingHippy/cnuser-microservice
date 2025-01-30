package com.ageinghippy.cnuser_microservice.controller;

import com.ageinghippy.cnuser_microservice.model.Item;
import com.ageinghippy.cnuser_microservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems() {
        List<Item> items;
        try {
            items= testService.getAllItems();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
        }

        return ResponseEntity.ok(items);
    }

    @GetMapping("")
    public ResponseEntity<?> getItem(@RequestParam Long id) {
        Item item;
        try {
            item = testService.getItem(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
        }

        return ResponseEntity.ok(item);
    }
}
