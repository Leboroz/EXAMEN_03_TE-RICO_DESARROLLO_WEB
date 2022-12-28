package com.examenteorico.examen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examenteorico.examen.model.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {
  
}
