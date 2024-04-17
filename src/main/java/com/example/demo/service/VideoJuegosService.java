package com.example.demo.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.VideoJuegos;
import com.example.demo.repository.VideoJuegosRepository;

@Service
public class VideoJuegosService {
	@Autowired
	private VideoJuegosRepository videoJuegosRepository;
	
	public List<VideoJuegos> findAllVideoJuegos(){
		return videoJuegosRepository.findAll();
	}
	
	public List<VideoJuegos> findByAllByCategoria(String categoria){
		return videoJuegosRepository.findAllByCategoria(categoria);
	}
	
	public VideoJuegos saveVideoJuegos(VideoJuegos videoJuegoNuevo) throws Exception {
		if(validacionesVideoJuegos(videoJuegoNuevo)) {
			return videoJuegosRepository.save(videoJuegoNuevo);
		}
		else {
			throw new Exception("El videojuego no se puedo guardar");
		}
	} 
	
	public String deleteVideoJuegos(String id){
		videoJuegosRepository.deleteById(id);
		return "El videoJuego con " + id + "se ha eliminado correctamente";
	}
	
	
	public VideoJuegos updateVideoJuego(String id, VideoJuegos videoJuegoActualizar) throws Exception {
        Optional<VideoJuegos> buscarVideoJuegos = videoJuegosRepository.findById(id);
        if(!buscarVideoJuegos.isPresent()) {
            throw new Exception("La prenda no fue encontrada");
        }
        VideoJuegos videoJuegoExistente = buscarVideoJuegos.get();
        videoJuegoExistente.setNombre(videoJuegoActualizar.getNombre());
        videoJuegoExistente.setCategoria(videoJuegoActualizar.getCategoria());
        videoJuegoExistente.setCalificacion(videoJuegoActualizar.getCalificacion());
        videoJuegoExistente.setPrecio(videoJuegoActualizar.getPrecio());
        videoJuegoExistente.setDistribuidora(videoJuegoActualizar.getDistribuidora());
        return videoJuegosRepository.save(videoJuegoActualizar);
    }
	
	public VideoJuegos updateDataVideoJuegos(String id, VideoJuegos videoJuegoActualizar) throws Exception {
        Optional<VideoJuegos> buscarVideoJuego = videoJuegosRepository.findById(id);
        if(!buscarVideoJuego.isPresent()) {
            throw new Exception("El videojuego no fue encontrada");
        }
        VideoJuegos videoJuegoExistente = buscarVideoJuego.get();
        if (videoJuegoActualizar.getNombre() != null) {
            videoJuegoExistente.setNombre(videoJuegoActualizar.getNombre());
        }
        if (videoJuegoActualizar.getDistribuidora() != null) {
            videoJuegoExistente.setDistribuidora(videoJuegoActualizar.getDistribuidora());
        }
        if (videoJuegoActualizar.getCategoria() != null) {
            videoJuegoExistente.setCategoria(videoJuegoActualizar.getCategoria());
        }
        if (videoJuegoActualizar.getPrecio() > 0) {
        	videoJuegoExistente.setPrecio(videoJuegoActualizar.getPrecio());
        }
        if (videoJuegoActualizar.getCalificacion() > 0) {
        	videoJuegoExistente.setCalificacion(videoJuegoActualizar.getCalificacion());
        }
        return videoJuegosRepository.save(videoJuegoExistente);

    }
	
	public Boolean validacionesVideoJuegos(VideoJuegos videoJuego) throws Exception {
		 if(videoJuego.getNombre() == null || videoJuego.getNombre().isBlank()) {
			 throw new Exception("El nombre de la prenda no puede ser nulo ");
		 }
		 if(videoJuego.getDistribuidora() == null || videoJuego.getDistribuidora().isBlank()) {
			 throw new Exception("El nombre de la prenda no puede ser nulo ");
		 }
		 if(videoJuego.getCategoria() == null || videoJuego.getCategoria().isBlank()) {
			 throw new Exception("El nombre de la prenda no puede ser nulo ");
		 }
		 if(videoJuego.getCalificacion()<0) {
			 throw new Exception("La calificacion del videojuego no puede ser negativo");
		 }
		 if(videoJuego.getPrecio()<0) {
			 throw new Exception("El precio del videoJuego no puede ser negativo");
		 }
		 return true;
}}
