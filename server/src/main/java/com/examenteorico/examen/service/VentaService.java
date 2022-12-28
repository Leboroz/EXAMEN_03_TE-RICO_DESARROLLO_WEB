package com.examenteorico.examen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;

@Service
public interface VentaService {
  public Venta addVenta(VentaRequestDto ventaRequestDto);
  public List<Venta> getVentas();
  public Venta getVenta(Long ventaId);
  public Venta deleteVenta(Long ventaId);
  public Venta editVenta(Long ventaId, VentaRequestDto ventaRequestDto);
}
