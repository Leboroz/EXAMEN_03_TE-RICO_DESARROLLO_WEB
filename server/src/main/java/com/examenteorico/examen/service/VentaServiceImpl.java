package com.examenteorico.examen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;
import com.examenteorico.examen.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService{

  private final VentaRepository ventaRepository;

  @Autowired
  public VentaServiceImpl(VentaRepository ventaRepository){
      this.ventaRepository = ventaRepository;
  }

	@Override
	public Venta addVenta(VentaRequestDto ventaRequestDto) {
    Venta venta = new Venta();
    venta.setDescription(ventaRequestDto.getDescription());
    venta.setQuantitySold(ventaRequestDto.getQuantitySold());
    venta.setUnitaryPrice(ventaRequestDto.getUnitaryPrice());
    venta.setTotal(ventaRequestDto.getTotal());

		return ventaRepository.save(venta);
	}

	@Override
	public List<Venta> getVentas() {
    List<Venta> ventas = new ArrayList<>();
    ventaRepository.findAll().forEach(ventas::add);
		return ventas;
	}

	@Override
	public Venta getVenta(Long ventaId) {
		return ventaRepository.findById(ventaId).orElseThrow(() -> 
      new IllegalArgumentException("venta with ventaId: " + ventaId + " could not be found"));
	}

	@Override
	public Venta deleteVenta(Long ventaId) {
    Venta venta = getVenta(ventaId);
    ventaRepository.delete(venta);
    return venta;
	}

	@Override
	public Venta editVenta(Long ventaId, VentaRequestDto ventaRequestDto) {
    Venta ventaToEdit = getVenta(ventaId);
    ventaToEdit.setDescription(ventaRequestDto.getDescription());
    ventaToEdit.setQuantitySold(ventaRequestDto.getQuantitySold());
    ventaToEdit.setUnitaryPrice(ventaRequestDto.getUnitaryPrice());
    ventaToEdit.setTotal(ventaRequestDto.getTotal());

		return ventaToEdit;
	}
  
}
