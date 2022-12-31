package com.examenteorico.examen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;

@Service
public interface VentaService {
  Venta addVenta(Long userId, VentaRequestDto ventaRequestDto);

  List<Venta> getVentas(Long userId);

  Venta getVenta(Long ventaId);

  void deleteVenta(Long ventaId);

  void editVenta(Long ventaId, VentaRequestDto ventaRequestDto);
}
