package com.examenteorico.examen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.examenteorico.examen.dto.requestDto.UserRequestDto;
import com.examenteorico.examen.model.User;
import com.examenteorico.examen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User addUser(UserRequestDto userRequestDto) {
    User user = new User();
    user.setName(userRequestDto.getName());
    user.setPassword(userRequestDto.getPassword());

    return userRepository.save(user);
  }

  @Override
  public User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("user with userId: " + userId + " could not be found"));
  }

  @Override
  public User getUserByCredentials(UserRequestDto userRequestDto) {
    String query = "FROM User WHERE name=:name";
    List<User> user = entityManager.createQuery(query)
        .setParameter("name", userRequestDto.getName())
        .getResultList();

    if (user.isEmpty()) {
      return null;
    }

    String passwordHashed = user.get(0).getPassword();
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    return (argon2.verify(passwordHashed, userRequestDto.getPassword())) ? user.get(0) : null;
  }

}
