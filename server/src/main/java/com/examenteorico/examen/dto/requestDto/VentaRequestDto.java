package com.examenteorico.examen.dto.requestDto;

import lombok.Data;

@Data
public class VentaRequestDto {
  private String description;
  private Integer quantitySold;
  private Float unitaryPrice;
  private Integer folio;
}
