package model;

public class Telefone {
	
	private Long id;
	private String numero;
	private String tipo;
	
	private Long usuariopessoa; //fk para usuario
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUsuariopessoa() {
		return usuariopessoa;
	}

	public void setUsuariopessoa(Long usuariopessoa) {
		this.usuariopessoa = usuariopessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", usuariopessoa=" + usuariopessoa
				+ "]";
	}

	
	
	

}
