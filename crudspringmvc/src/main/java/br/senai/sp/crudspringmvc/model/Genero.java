package br.senai.sp.crudspringmvc.model;

public enum Genero {
	

	MASCULINO("Masculino"), FEMININO("Feminino");
		
	String desc;
		
	private Genero(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
	
}



