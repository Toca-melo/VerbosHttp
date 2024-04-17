package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.VideoJuegos;

@Repository
public interface VideoJuegosRepository extends MongoRepository<VideoJuegos, String> {
	List<VideoJuegos> findAll();
	List<VideoJuegos> findAllByCategoria(String categoria);
	List<VideoJuegos> findAllByNombre(String nombre);
}
