package com.nitish.counter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {

    @RequestMapping("/")
   public String getSwaggerPage(){
        return "redirect:swagger-ui.html";
    }
}
