package ru.hogwarts.school.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {


    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public String getPort() {
        return "Используемый порт" + port;
    }


    @GetMapping
    public void startT1() {

    }

}
