package com.example.mini_project_be.repository;

import com.example.mini_project_be.domain.User;
import com.example.mini_project_be.dto.UserDtoForEdit;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {

  private final EntityManager entityManager;

  @Autowired
  public JpaUserRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public User save(User user) {
    System.out.println("JpaUserRepository.findByEmail : " + user);
    entityManager.persist(user); // id는 자동으로 들어감 << auto increment
    return user;
  }

  @Override
  public Optional<User> findById(Long id) {
    return Optional.ofNullable(entityManager.find(User.class, id)); // id를 키로 찾아서 User 클래스로 반환, NPE 방지, 값이 없으면 빈 리스트 반환
  }

  @Override
  public Optional<User> findByEmail(String email) {
    Optional<User> temp = entityManager.createQuery("select u from User u where email = :email", User.class)
        .setParameter("email", email) // 쿼리문 안의 email을 매개변수 email로 넣어준다
        .getResultList() // 리스트로 가져옴
        .stream()
        .filter(m -> m.getEmail().equals(email)) // 비교
        .findAny();

    System.out.println("JpaUserRepository.findByEmail : " + temp);
    return temp;
  }

  @Override
  public List<User> findAll() {
    // u이란 이름의 User 객체에서 이름이 setParameter()로 준 값과 같은 필드를 리스트로 가져온다
    // u은 SQL에서 앨리어스
    // select u은 select *와 같다
    return entityManager.createQuery("select u from User u", User.class)
        .getResultList(); // 리스트로 반환
  }

  @Override
  public void update(UserDtoForEdit userDtoForEdit) {
    User temp = findByEmail(userDtoForEdit.getEmail()).get();
    temp.setPassword(userDtoForEdit.getPassword());
  }

  @Override
  public Long remove(Long id) {
    User temp = findById(id).get();
    entityManager.remove(temp);

    return id;
  }
}
