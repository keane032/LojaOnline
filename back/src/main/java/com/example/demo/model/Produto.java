package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull 
	@Size(min=5,max=50, message="O tamanho deve ser entre {min} e {max}")
	private String nome;
	
	@NotNull
	private double valor;

	@NotNull
	private Integer qtd;
	
	public Produto() {}
	
	public Produto(int id){
		this.id = id;
	}	
	public Produto(int id,String nome,String valor,int qtd){
		this.id=id;
		this.nome=nome;
		this.valor=Float.parseFloat(valor);
		this.qtd=qtd;			
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	
	
	
	
	
	
}
