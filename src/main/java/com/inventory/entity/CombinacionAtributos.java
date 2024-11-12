package com.inventory.entity;

import java.util.ArrayList;
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
@Table(name = "combinaciones_atributos")
public class CombinacionAtributos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combinacion_atributos")
    private Long idCombinacionAtributos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @OneToMany(mappedBy = "combinacionAtributos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CombinacionValorAtributo> valoresAtributos = new ArrayList<>();
}
