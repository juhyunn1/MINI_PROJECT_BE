package com.example.mini_project_be.service;

import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.user.UserDtoForEdit;
import com.example.mini_project_be.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // reaadOnly가 true이면 c, u, d가 안됨 >> c, u, d가 필요한 곳에서는 별도로 @Transactional 해주어야
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public Long join(User user) {
    // isDuplicateEmail(user.getEmail());
    System.out.println("UserService.join: " + user.getEmail());
    return userRepository.save(user).getId();
  }

  // 이메일 중복확인
  public boolean isDuplicateEmail(String email) {
    if(!findUserByEmail(email).isEmpty()) // 이미 동일한 이메일이 존재하면
      // throw new IllegalStateException("이미 존재하는 이메일 입니다."); // 예외를 호출한 함수로 던진다
      return true;

    return false;
  }


  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<User> findUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public void editPassword(UserDtoForEdit userDtoForEdit) {
    userRepository.update(userDtoForEdit);
  }

  @Transactional
  public Long deleteUser(Long id) {
    userRepository.remove(id);
    return id;
  }
}
