package com.examenteorico.examen.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenteorico.examen.authorization.JWT;
import com.examenteorico.examen.dto.requestDto.UserRequestDto;
import com.examenteorico.examen.model.User;
import com.examenteorico.examen.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

  private final UserService userService;
  private final JWT jwt;

  @Autowired
  public LoginController(UserService userService, JWT jwt) {
    this.userService = userService;
    this.jwt = jwt;
  }

  @PostMapping("/")
  public ResponseEntity<User> getToken(@RequestHeader(value = "Authorization") final String token) {
    Long userId = jwt.getKey(token);

    if (userId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
  }

  @PostMapping("/create")
  public String createToken(@RequestBody final UserRequestDto userRequestDto) {
    User loggedUser = userService.getUserByCredentials(userRequestDto);

    if (loggedUser != null) {
      String token = jwt.create(loggedUser.getId() + "", loggedUser.getName());
      return token;
    }

    return "Error";
  }
}
