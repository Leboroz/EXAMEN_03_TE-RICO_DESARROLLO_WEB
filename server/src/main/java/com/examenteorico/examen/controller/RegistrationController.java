package com.examenteorico.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import com.examenteorico.examen.dto.requestDto.UserRequestDto;
import com.examenteorico.examen.model.User;
import com.examenteorico.examen.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<User> addUser(@RequestBody final UserRequestDto userRequestDto) {

    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    userRequestDto.setPassword(argon2.hash(1, 1024, 1, userRequestDto.getPassword()));
    User user = userService.addUser(userRequestDto);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
