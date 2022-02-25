package br.senai.sp.crudspringmvc.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Cliente {
	private Long id;
	private String nome;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataNascimento;
	private Genero genero;
	private String endereco;
	private String telefone;
	private String email;
	private String prodInteresse;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String numero) {
		this.telefone = numero;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProdInteresse() {
		return prodInteresse;
	}
	public void setProdInteresse(String prodInteresse) {
		this.prodInteresse = prodInteresse;
	}
	public int getIdade() {
		int dia = dataNascimento.get(Calendar.DAY_OF_MONTH);
		int mes = dataNascimento.get(Calendar.MONTH);
		int ano = dataNascimento.get(Calendar.YEAR);
		LocalDate dataNasc = LocalDate.of(ano, mes+1, dia);
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNasc, dataAtual);
		return periodo.getYears();		
	}
			
}
