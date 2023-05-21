package com.example.demo.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String error() {
        return "";
    }
}
