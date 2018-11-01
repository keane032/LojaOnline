package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService serv;
	
	@GetMapping("/")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/listar")
	public List<Usuario> Lista(){
		return serv.listar();
	}
	
	
}
