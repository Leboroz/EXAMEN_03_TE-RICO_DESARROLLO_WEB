package com.examenteorico.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenteorico.examen.authorization.JWT;
import com.examenteorico.examen.dto.requestDto.VentaRequestDto;
import com.examenteorico.examen.model.Venta;
import com.examenteorico.examen.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {

  private final VentaService ventaService;
  private final JWT jwt;

  @Autowired
  public VentaController(VentaService ventaService, JWT jwt) {
    this.ventaService = ventaService;
    this.jwt = jwt;
  }

  @PostMapping("/add")
  public ResponseEntity<Venta> addVenta(@RequestHeader(value = "authorization") final String token,
      @RequestBody final VentaRequestDto ventaRequestDto) {
    Long userId = jwt.getKey(token);
    Venta venta = ventaService.addVenta(userId, ventaRequestDto);
    return new ResponseEntity<>(venta, HttpStatus.OK);
  }

  @GetMapping("/getall")
  public ResponseEntity<List<Venta>> getVentaById(@RequestHeader(value = "authorization") final String token) {
    Long userId = jwt.getKey(token);
    List<Venta> ventas = ventaService.getVentas(userId);
    return new ResponseEntity<>(ventas, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Venta> deleteVenta(
      @PathVariable final Long id) {
    ventaService.deleteVenta(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/edit/{id}")
  public ResponseEntity<Venta> editVenta(@RequestBody final VentaRequestDto ventaRequestDto,
      @PathVariable final Long id) {
    ventaService.editVenta(id, ventaRequestDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
