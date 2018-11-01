package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository rep;
	
	public List<Produto> listar(){
		return rep.findAll();
	}
	
	public Produto add(Produto produto) {
		return rep.save(produto);
	}
	
	
	
}
