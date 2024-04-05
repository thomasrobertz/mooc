package de.robertz.springsecurityfundamentals1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/test")
    public String test() {
        // Note: Because of CSRF and ReST semantics, logout needs to be a POST!
        return "<form action=\"/logout\" method=\"post\">\n" +
            "    <input type=\"hidden\" name=\"_csrf\" value=\"${_csrf.token}\"/>\n" +
            "    <input type=\"submit\" value=\"Logout\"/>\n" +
            "</form>";
    }
}
