package com.examenteorico.examen.dto;

import com.examenteorico.examen.dto.responseDto.VentaResponseDto;
import com.examenteorico.examen.model.Venta;

import com.examenteorico.examen.dto.responseDto.UserResponseDto;
import com.examenteorico.examen.model.User;

public class Mapper {
  public static VentaResponseDto ventaToVentaResponseDto(Venta venta) {
    VentaResponseDto ventaResponseDto = new VentaResponseDto();
    ventaResponseDto.setId(venta.getId());
    ventaResponseDto.setDescription(venta.getDescription());
    ventaResponseDto.setQuantitySold(venta.getQuantitySold());
    ventaResponseDto.setUnitaryPrice(venta.getUnitaryPrice());
    ventaResponseDto.setFolio(venta.getFolio());

    return ventaResponseDto;
  }

  public static UserResponseDto userToUserResponseDto(User user) {
    UserResponseDto userResponseDto = new UserResponseDto();
    userResponseDto.setId(user.getId());
    userResponseDto.setName(user.getName());
    userResponseDto.setPassword(user.getPassword());

    return userResponseDto;
  }
}
