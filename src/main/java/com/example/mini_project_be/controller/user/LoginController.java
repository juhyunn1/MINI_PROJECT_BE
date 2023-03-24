package com.example.mini_project_be.controller.user;

import com.example.mini_project_be.controller.session.SessionConst;
import com.example.mini_project_be.controller.session.UserOnSession;
import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.user.UserDtoForLogin;
import com.example.mini_project_be.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final UserService userService;

  @GetMapping("/login")
  public String login() {
    return "/user/login";
  }

  @PostMapping("/login")
  public String login(
      @ModelAttribute("user") UserDtoForLogin userDtoForLogin, // 폼에서 받아온 필드만 userDto에 매핑, 폼에 없는 필드는 null
      Model model,
      HttpServletRequest request
  ) {
    String email = userDtoForLogin.getEmail();
    Optional<User> temp = userService.findUserByEmail(email);
    log.info("UserController.userDto : " + userDtoForLogin);
    log.info("UserController.temp : " + temp);

    model.addAttribute("doesFailed", false);

    if(!temp.isEmpty() && temp.get().getPassword().equals(userDtoForLogin.getPassword())) { // 성공, 사용자가 존재하고, 사용자의 password가 화면에서 입력한 password와 일치해야
      // 세션 저장소에 넣을 userOnSession 객체 생성, 세팅
      UserOnSession userOnSession = new UserOnSession();
      userOnSession.setId(temp.get().getId());
      userOnSession.setName(temp.get().getName());
      userOnSession.setEmail(temp.get().getEmail());

      HttpSession session = request.getSession(true); // 기존에 세션이 있으면 반환, 없으면 새로 만든다
      session.setAttribute(SessionConst.NAME, userOnSession); // loggedinUser라는 이름으로 userOnSession을 session에 추가

      return "redirect:/";
    }
    else {
      System.out.println("실패");
      model.addAttribute("doesFailed", true); // alert 띄운다
      return "/alert";
    }
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false); // 없으면 null 반환

    if(session != null) // 있으면
      session.invalidate(); // 삭제

    return "redirect:/";
  }
}
