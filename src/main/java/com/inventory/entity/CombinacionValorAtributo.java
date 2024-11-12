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
@Table(name = "combinacion_valor_atributos")
public class CombinacionValorAtributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combinacion_valor_atributo")
    private Long idCombinacionValorAtributo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_combinacion_atributos", nullable = false)
    private CombinacionAtributos combinacionAtributos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "valor_atributo_id", nullable = false)
    private ValorAtributo valorAtributo;

	public CombinacionValorAtributo() {
		super();
	}

	public CombinacionValorAtributo(Long idCombinacionValorAtributo, CombinacionAtributos combinacionAtributos,
			ValorAtributo valorAtributo) {
		super();
		this.idCombinacionValorAtributo = idCombinacionValorAtributo;
		this.combinacionAtributos = combinacionAtributos;
		this.valorAtributo = valorAtributo;
	}

	public Long getIdCombinacionValorAtributo() {
		return idCombinacionValorAtributo;
	}

	public void setIdCombinacionValorAtributo(Long idCombinacionValorAtributo) {
		this.idCombinacionValorAtributo = idCombinacionValorAtributo;
	}

	public CombinacionAtributos getCombinacionAtributos() {
		return combinacionAtributos;
	}

	public void setCombinacionAtributos(CombinacionAtributos combinacionAtributos) {
		this.combinacionAtributos = combinacionAtributos;
	}

	public ValorAtributo getValorAtributo() {
		return valorAtributo;
	}

	public void setValorAtributo(ValorAtributo valorAtributo) {
		this.valorAtributo = valorAtributo;
	}
        
}

