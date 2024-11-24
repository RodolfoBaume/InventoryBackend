package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="valor_atributos")
public class ValorAtributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValorAtributo;
    
    @Column(nullable = false)
    private String valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atributo_id")
    private Atributo atributo;

	
    public ValorAtributo() {
		super();
 	}


	public ValorAtributo(Long idValorAtributo, String valor, Atributo atributo) {
		super();
		this.idValorAtributo = idValorAtributo;
		this.valor = valor;
		this.atributo = atributo;
	}

	public Long getIdValorAtributo() {
		return idValorAtributo;
	}


	public void setIdValorAtributo(Long idValorAtributo) {
		this.idValorAtributo = idValorAtributo;
	}

	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public Atributo getAtributo() {
		return atributo;
	}


	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}
}

