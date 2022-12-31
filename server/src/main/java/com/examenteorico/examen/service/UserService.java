package com.examenteorico.examen.service;

import org.springframework.stereotype.Service;

import com.examenteorico.examen.dto.requestDto.UserRequestDto;
import com.examenteorico.examen.model.User;

@Service
public interface UserService {
  User addUser(UserRequestDto userRequestDto);

  User getUser(Long userId);

  User getUserByCredentials(UserRequestDto userRequestDto);
}
