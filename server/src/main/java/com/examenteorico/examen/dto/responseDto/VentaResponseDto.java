package com.examenteorico.examen.dto.responseDto;

import lombok.Data;

@Data
public class VentaResponseDto {
  private Long id;
  private String description;
  private Integer quantitySold;
  private Float unitaryPrice;
  private double total;
}
