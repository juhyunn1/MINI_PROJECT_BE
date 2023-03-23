package com.example.mini_project_be.controller;

import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.UserDtoForJoin;
import com.example.mini_project_be.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

  private final UserService userService;

  @GetMapping("/join")
  public String join() {
    return "/join";
  }

  @PostMapping("/join")
  public String join(
      @Validated @ModelAttribute("user") UserDtoForJoin userDtoForJoin,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) { // 처리할 에러가 있으면
      log.info("errors={}", bindingResult); // 로그 찍고
      return "/join"; // 회원가입 폼으로 이동
    }

    userService.join(new User(userDtoForJoin.getName(), userDtoForJoin.getEmail(), userDtoForJoin.getPassword()));
    return "redirect:/login";
  }
}
