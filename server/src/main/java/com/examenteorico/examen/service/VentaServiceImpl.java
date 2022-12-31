package com.examenteorico.examen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;
import com.examenteorico.examen.repository.VentaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

  private final VentaRepository ventaRepository;
  private final UserService userService;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  public VentaServiceImpl(VentaRepository ventaRepository, UserService userService) {
    this.ventaRepository = ventaRepository;
    this.userService = userService;
  }

  @Override
  public Venta addVenta(Long userId, VentaRequestDto ventaRequestDto) {
    Venta venta = new Venta();
    venta.setDescription(ventaRequestDto.getDescription());
    venta.setQuantitySold(ventaRequestDto.getQuantitySold());
    venta.setUnitaryPrice(ventaRequestDto.getUnitaryPrice());
    venta.setFolio(ventaRequestDto.getFolio());
    venta.setUser(userService.getUser(userId));

    return ventaRepository.save(venta);
  }

  @Transactional
  @Override
  public List<Venta> getVentas(Long userId) {
    String query = "FROM Venta WHERE user=:user";
    List<Venta> ventas = entityManager.createQuery(query)
        .setParameter("user", userService.getUser(userId))
        .getResultList();
    return ventas;
  }

  @Override
  public Venta getVenta(Long ventaId) {
    return ventaRepository
        .findById(ventaId)
        .orElseThrow(() -> new IllegalArgumentException("venta with ventaId: " + ventaId + " could not be found"));
  }

  @Override
  public void deleteVenta(Long ventaId) {
    Venta venta = getVenta(ventaId);
    ventaRepository.delete(venta);
  }

  @Override
  public void editVenta(Long ventaId, VentaRequestDto ventaRequestDto) {
    Venta ventaToEdit = getVenta(ventaId);
    ventaToEdit.setDescription(ventaRequestDto.getDescription());
    ventaToEdit.setQuantitySold(ventaRequestDto.getQuantitySold());
    ventaToEdit.setUnitaryPrice(ventaRequestDto.getUnitaryPrice());
    ventaToEdit.setFolio(ventaRequestDto.getFolio());
    ventaRepository.save(ventaToEdit);
  }

}
