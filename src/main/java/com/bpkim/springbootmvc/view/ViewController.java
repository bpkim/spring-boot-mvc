package com.bpkim.springbootmvc.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/content")
    public String content(){
        return "content";
    }

    @RequestMapping(value = "/popup")
    public String popup(){
        return "popup";
    }


}
