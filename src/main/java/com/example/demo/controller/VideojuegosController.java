package com.example.demo.controller;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.VideoJuegos;
import com.example.demo.service.VideoJuegosService;

@RestController
@RequestMapping("/videoJuegos")
public class VideojuegosController {
	@Autowired
	private VideoJuegosService videoJuegosService;
	
	@GetMapping("/getAllVideoJuegos")
	public List<VideoJuegos> getAllVideoJuegos(){
		return videoJuegosService.findAllVideoJuegos();
	}
	
	@PostMapping("/savedVideoJuegos")
	public ResponseEntity<VideoJuegos> saveVideoJuegos(@RequestBody VideoJuegos videoJuegos) throws Exception{
		VideoJuegos savedVideoJuegos = videoJuegosService.saveVideoJuegos(videoJuegos);
		return ResponseEntity.ok(savedVideoJuegos);
	}
	
	@DeleteMapping("/{id}")
	public String deleteVideoJuegos(@PathVariable String id){
		videoJuegosService.deleteVideoJuegos(id);
		return "El videoJuego con " + id + "se ha eliminado correctamente";
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VideoJuegos> updateVideoJuego(@PathVariable String id,@RequestBody VideoJuegos videoJuegoActualizar) throws Exception{
		VideoJuegos updateVideoJuegos = videoJuegosService.updateDataVideoJuegos(id, videoJuegoActualizar);
		return ResponseEntity.ok(updateVideoJuegos);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.HEAD)
    public ResponseEntity<?> handleHeadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> Options() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ALLOW, "GET, POST, PATCH, PUT, DELETE, OPTIONS");

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
	
	@PatchMapping("/updateData/{id}")
    public VideoJuegos updateDataVideoJuegos(@PathVariable String id, @RequestBody VideoJuegos videoJuegoActualizar) throws Exception{
        return videoJuegosService.updateDataVideoJuegos(id, videoJuegoActualizar);
    }
	
	
}
