package com.examenteorico.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;
import com.examenteorico.examen.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {

  private final VentaService ventaService;

  @Autowired
  public VentaController(VentaService ventaService) {
    this.ventaService = ventaService;
  }

  @PostMapping("/add")
  public ResponseEntity<Venta> addVenta(@RequestBody final VentaRequestDto ventaRequestDto) {
    Venta venta = ventaService.addVenta(ventaRequestDto);
    return new ResponseEntity<>(venta, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Venta> getVentaById(@PathVariable final Long id) {
    Venta venta = ventaService.getVenta(id);
    return new ResponseEntity<>(venta, HttpStatus.OK);
  }

  @GetMapping("/getall")
  public ResponseEntity<List<Venta>> getVentaById() {
    List<Venta> ventas = ventaService.getVentas();
    return new ResponseEntity<>(ventas, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Venta> deleteVenta(@PathVariable final Long id) {
    Venta venta = ventaService.deleteVenta(id);
    return new ResponseEntity<>(venta, HttpStatus.OK);
  }

  @PostMapping("/edit/{id}")
  public ResponseEntity<Venta> editVenta(@RequestBody final VentaRequestDto ventaRequestDto,
                                         @PathVariable final Long id) {
    Venta venta = ventaService.editVenta(id, ventaRequestDto);
    return new ResponseEntity<>(venta, HttpStatus.OK);
  }
}
