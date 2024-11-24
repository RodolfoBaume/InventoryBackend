package com.inventory.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="atributos")
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtributo;
    
    @Column(nullable = false)
    private String atributo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    
    @OneToMany(mappedBy = "atributo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ValorAtributo> valores;
    
	public Atributo() {
		super();
	}

	public Atributo(Long idAtributo, String atributo, Grupo grupo, List<ValorAtributo> valores) {
		super();
		this.idAtributo = idAtributo;
		this.atributo = atributo;
		this.grupo = grupo;
		this.valores = valores;
	}



	public Long getIdAtributo() {
		return idAtributo;
	}

	public void setIdAtributo(Long idAtributo) {
		this.idAtributo = idAtributo;
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

	public List<ValorAtributo> getValores() {
		return valores;
	}

	public void setValores(List<ValorAtributo> valores) {
		this.valores = valores;
	}

}

