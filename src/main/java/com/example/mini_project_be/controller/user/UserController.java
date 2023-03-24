package com.example.mini_project_be.controller.user;

import com.example.mini_project_be.controller.session.SessionConst;
import com.example.mini_project_be.controller.session.UserOnSession;
import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.user.UserDtoForEdit;
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
public class UserController {

  private final UserService userService;

  @GetMapping("/profile")
  public String profile(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환
    
    if(session != null) {
      UserOnSession userOnSession = (UserOnSession)session.getAttribute(SessionConst.NAME); // loggedinUser라는 이름의 세션을 가져온다, Object를 반환하니깐 UserOnSession으로 다운캐스팅
      model.addAttribute("user", userOnSession); // 모델에 user라는 이름으로 userOnSession 담아서
    } else {
      model.addAttribute("user", new UserOnSession()); // 오류 방지위해 빈 객체 넘겨준다
    }

    return "/user/profile"; // profile.html으로 이동
  }

  @PostMapping("/check-password")
  public String checkPassword(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환

    if(session != null) {
      UserOnSession userOnSession = (UserOnSession)session.getAttribute(SessionConst.NAME); // loggedinUser라는 이름의 세션을 가져온다, Object를 반환하니깐 UserOnSession으로 다운캐스팅
      String currentPassword = userService.findUserById(userOnSession.getId()).get().getPassword();

      model.addAttribute("currentPassword", currentPassword); // 비교를 위해 사용자의 비밀번호를 전달
    } else {
      model.addAttribute("currentPassword", "");
    }

    return "/user/checkPassword";
  }


  @GetMapping("/change-password")
  public String changePassword(Model model) {
    model.addAttribute("user", new UserDtoForEdit());
    return "/user/changePassword";
  }

  @PostMapping("/change-password")
  public String changePassword(
      @Validated @ModelAttribute("user") UserDtoForEdit userDtoForEdit, // 폼에서 받아온 필드만 userDto에 매핑, 폼에 없는 필드는 null
      BindingResult bindingResult,
      Model model,
      HttpServletRequest request
  ) {
    if(bindingResult.hasErrors()) { // 처리할 에러가 있으면
      log.info("errors={}", bindingResult); // 로그 찍고
      return "/user/changePassword"; // 비밀번호 변경 폼으로 이동
    }

    HttpSession session = request.getSession(false); // 기존에 세션이 있으면 반환, 없으면 null 반환

    if(session != null) { // 있으면
      UserOnSession userOnSession = (UserOnSession)session.getAttribute(SessionConst.NAME); // loggedinUser라는 이름의 세션을 가져온다, Object를 반환하니깐 UserOnSession으로 다운캐스팅
      String email = userService.findUserById(userOnSession.getId()).get().getEmail(); // 사용자 이메일 가져온다

      User temp = userService.findUserByEmail(email).get(); // 이메일로 사용자 정보 가져와서
      if(temp.getPassword().equals(userDtoForEdit.getCurrentPassword())) { // 비밀번호 비교해서 같으면
        userDtoForEdit.setEmail(email); // 사용자 이메일 넣어서
        userService.editPassword(userDtoForEdit); // 업뎃하고

        model.addAttribute("doesChangePasswordSucceed", true); // alert 띄운다
      } else {
        model.addAttribute("isWrongPassword", true); // alert 띄운다
      }
      return "/alert";

    } else {
      return "redirect:/";
    }
  }
}
