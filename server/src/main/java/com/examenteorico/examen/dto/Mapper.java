package com.examenteorico.examen.dto;

import com.examenteorico.examen.dto.responseDto.VentaResponseDto;
import com.examenteorico.examen.model.Venta;

public class Mapper {
  public static VentaResponseDto ventaToVentaResponseDto(Venta venta) {
    VentaResponseDto ventaResponseDto = new VentaResponseDto();
    ventaResponseDto.setId(venta.getId());
    ventaResponseDto.setDescription(venta.getDescription());
    ventaResponseDto.setQuantitySold(venta.getQuantitySold());
    ventaResponseDto.setUnitaryPrice(venta.getUnitaryPrice());
    ventaResponseDto.setTotal(venta.getTotal());

    return ventaResponseDto;
  }
}
