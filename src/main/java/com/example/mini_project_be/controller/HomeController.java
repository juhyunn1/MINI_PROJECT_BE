package com.example.mini_project_be.controller;

import com.example.mini_project_be.controller.session.SessionConst;
import com.example.mini_project_be.controller.session.UserOnSession;
import com.example.mini_project_be.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j // 로그 사용
@Controller
public class HomeController {

  UserService memberService;

  public HomeController(UserService userService) {
    this.memberService = userService;
  }

  // 세션 사용
  @GetMapping("/")
  public String home(HttpServletRequest request, Model model) {

    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환

    if (session == null) // 로그인하지 않은 사람은
      return "/login"; // login.html으로 이동
    else { // 로그인한 사람은
      UserOnSession userOnSession = (UserOnSession)session.getAttribute(SessionConst.NAME); // loggedinUser라는 이름의 세션을 가져온다, Object를 반환하니깐 UserOnSession으로 다운캐스팅
      model.addAttribute("user", userOnSession);
      return "/userHome"; // useHome.html으로 이동
    }
  }

}