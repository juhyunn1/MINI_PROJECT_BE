package com.example.mini_project_be.controller.user;

import com.example.mini_project_be.controller.session.SessionConst;
import com.example.mini_project_be.controller.session.UserOnSession;
import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.user.UserDtoForJoin;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

  private final UserService userService;

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("user", new UserDtoForJoin()); // 빈 객체 넘겨준다 << join.html에서 th:object를 쓰고 있다
    return "/user/join";
  }

  @PostMapping("/join")
  public String join(
      @Validated @ModelAttribute("user") UserDtoForJoin userDtoForJoin,
      BindingResult bindingResult,
      Model model
  ) {
    if(bindingResult.hasErrors()) { // 처리할 에러가 있으면
      log.info("errors={}", bindingResult); // 로그 찍고
      return "/user/join"; // 회원가입 폼으로 이동
    }

    if(userService.isDuplicateEmail(userDtoForJoin.getEmail())) { // 이메일 중복 검사
      model.addAttribute("isDuplicate", true); // alert 띄운다
      return "/alert";
    }

    // 정상 로직
    userService.join(new User(userDtoForJoin.getName(), userDtoForJoin.getEmail(), userDtoForJoin.getPassword()));

    model.addAttribute("doesSucceed", true); // alert 띄운다
    return "/alert";
  }

  @GetMapping("/unregister")
  public String unregister(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환

    if(session != null) {
      UserOnSession userOnSession = (UserOnSession)session.getAttribute(SessionConst.NAME); // loggedinUser라는 이름의 세션을 가져온다, Object를 반환하니깐 UserOnSession으로 다운캐스팅
      session.invalidate(); // 세션 삭제
      userService.deleteUser(userOnSession.getId()); // 회원정보 삭제
    }

    return "redirect:/";
  }
}
