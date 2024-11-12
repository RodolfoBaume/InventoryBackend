package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.entity.CombinacionAtributos;
import com.inventory.entity.CombinacionValorAtributo;
import com.inventory.entity.Grupo;
import com.inventory.entity.ValorAtributo;
import com.inventory.repository.ICombinacionAtributosRepository;
import com.inventory.repository.ICombinacionValorAtributoRepository;
import com.inventory.repository.IGrupoRepository;
import com.inventory.repository.IValorAtributoRepository;

import jakarta.transaction.Transactional;

@Service
public class CombinacionAtributosService implements ICombinacionAtributosService{

    @Autowired
    private IGrupoRepository grupoRepository;

    @Autowired
    private ICombinacionAtributosRepository combinacionAtributosRepository;

    @Autowired
    private IValorAtributoRepository valorAtributoRepository;

    @Autowired
    private ICombinacionValorAtributoRepository combinacionValorAtributoRepository;

    /**
     * Crea una nueva combinación de atributos asociada a un grupo y agrega valores de atributo seleccionados.
     *
     * @param grupoId          ID del grupo al que pertenece la combinación de atributos
     * @param valorAtributoIds Lista de IDs de los valores de atributos para asociar con la combinación
     * @return La combinación de atributos creada o null si el grupo no existe
     */
    @Transactional
    public CombinacionAtributos createCombinacion(Long grupoId, List<Long> valorAtributoIds) {
        // Buscar el grupo por su ID
        Grupo grupo = grupoRepository.findById(grupoId).orElse(null);
        
        if (grupo == null) {
            return null; // Retornar null si no se encuentra el grupo
        }

        // Crear la combinación de atributos y asociarla al grupo
        CombinacionAtributos combinacion = new CombinacionAtributos();
        combinacion.setGrupo(grupo);
        CombinacionAtributos savedCombinacion = combinacionAtributosRepository.save(combinacion);
        
        // Asociar los valores de atributo a la combinación
        for (Long valorId : valorAtributoIds) {
            ValorAtributo valor = valorAtributoRepository.findById(valorId).orElse(null);
            if (valor != null) {
                CombinacionValorAtributo combinacionValorAtributo = new CombinacionValorAtributo();
                combinacionValorAtributo.setCombinacionAtributos(savedCombinacion);
                combinacionValorAtributo.setValorAtributo(valor);
                combinacionValorAtributoRepository.save(combinacionValorAtributo);
            }
        }
        
        return savedCombinacion;
    }
}
