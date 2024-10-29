package com.Mutation.Tr.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("main.htmlContentPath")
    private String mainHtmlContentPath;

    @RequestMapping(value = {"/home",""})
    public String getMain(){
        System.err.println("main");
        return mainHtmlContentPath+"index";
    }

}
