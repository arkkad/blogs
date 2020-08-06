package com.maesto.blogsApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MVCController {

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String main(Map<String, Object> model) {
        return "index";
    }
}
