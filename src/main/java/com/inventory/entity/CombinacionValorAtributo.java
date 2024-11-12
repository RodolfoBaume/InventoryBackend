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
}

