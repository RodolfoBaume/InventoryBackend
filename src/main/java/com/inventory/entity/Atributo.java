package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="atributos")
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atributoId;
    
    @Column(nullable = false)
    private String atributo;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    
	public Atributo() {
		super();
	}

	public Atributo(Long atributoId, String atributo, Grupo grupo) {
		super();
		this.atributoId = atributoId;
		this.atributo = atributo;
		this.grupo = grupo;
	}

	public Long getAtributoId() {
		return atributoId;
	}

	public void setAtributoId(Long atributoId) {
		this.atributoId = atributoId;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}

