package ru.akaleganov.cyberforum.controller;

import javax.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class TestController {

    @GetMapping
    public void get(@RequestBody(required = false) Item item, @RequestParam String param, HttpServletRequest request) {
        log.error("request.getMethod() = {}", request.getMethod());
        log.error("item = {}", item);
        log.error("param = {}", param);
    }

}
