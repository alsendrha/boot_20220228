package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 크롬에서 주소를 입력한게 일치하면
    // 밑에 있는 메소드가 실행
    @GetMapping(value = { "/", "/home" })
    public String homeGET(HttpSession httpSession) {
        System.out.println(httpSession.getId());
        // home.jsp를 표시하라
        return "home";
    }
}
