package de.robertz.springsecurityfundamentals2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    @GetMapping("/hello")
    public String hello() {
        // Note: Because of CSRF and ReST semantics, logout needs to be a POST!
        return "<form action=\"/logout\" method=\"post\">\n" +
            "    <input type=\"hidden\" name=\"_csrf\" value=\"${_csrf.token}\"/>\n" +
            "    <input type=\"submit\" value=\"Logout\"/>\n" +
            "</form>";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye bye!";
    }
}
