package com.bpkim.springbootmvc.view;

import com.bpkim.springbootmvc.common.userlog.MoveLogging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @MoveLogging
    @RequestMapping(value = "/content")
    public String content(){
        return "content";
    }

    @MoveLogging
    @RequestMapping(value = "/popup")
    public String popup(){
        return "popup";
    }


}
