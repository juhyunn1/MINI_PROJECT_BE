package com.example.mini_project_be.controller;

import com.example.mini_project_be.controller.session.SessionConst;
import com.example.mini_project_be.controller.session.UserOnSession;
import com.example.mini_project_be.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AlertController {

  @GetMapping("/alert-unregister")
  public String alertUnregister(Model model) {
    model.addAttribute("willDelete", true); // alert 띄운다
    return "/alert";
  }

  @GetMapping("/alert-wrong-password")
  public String alertWrongPassword(Model model) {
    model.addAttribute("isWrongPassword", true); // alert 띄운다
    return "/alert";
  }
}
