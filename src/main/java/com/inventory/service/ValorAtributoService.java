package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.ValorAtributoDto;
import com.inventory.entity.Atributo;
import com.inventory.entity.ValorAtributo;
import com.inventory.repository.IAtributoRepository;
import com.inventory.repository.IValorAtributoRepository;

/**
 * Servicio que implementa la lógica de negocio para la entidad
 * {@link ValorAtributo}.
 */
@Service
public class ValorAtributoService implements IValorAtributoService {

	@Autowired
	private IValorAtributoRepository valorAtributoRepository;
	
	@Autowired
	private IAtributoRepository atributoRepository;

	/**
	 * Consulta todos los valores del atributo ordenados por id.
	 * 
	 * @return lista de todos los valores por atributo
	 */
	@Transactional(readOnly = true)
	public List<ValorAtributo> findAll() {
		return (List<ValorAtributo>) valorAtributoRepository.findAll(Sort.by("idValorAtributo"));
	}

	/**
	 * Consulta todos los valores por atributo con soporte de paginación.
	 * 
	 * @param pageable objeto de paginación
	 * @return página de valores atributos
	 */
	@Transactional(readOnly = true)
	public Page<ValorAtributo> findAllPage(Pageable pageable) {
		return valorAtributoRepository.findAll(pageable);
	}

	/**
	 * Busca un valor por atributo por su id.
	 * 
	 * @param idValorAtributo id del valor atributo a buscar
	 * @return el valor atributo si existe, de lo contrario null
	 */
	@Transactional(readOnly = true)
	public ValorAtributo findById(Long idValorAtributo) {
		return valorAtributoRepository.findById(idValorAtributo).orElse(null);
	}

	/**
	 * Crea un nuevo valor atributo y lo asocia con un atributo específico.
	 *
	 * @param atributoId  id del atributo al que se asociará el valor atributo
	 * @param valor atributo objeto ValorAtributo con los datos del valor atributo a crear
	 * @return el valor atributo creado si el atributo existe, de lo contrario null
	 */
	@Transactional
	public ValorAtributo createValorAtributo(Long atributoId, ValorAtributo valorAtributo) {
		Atributo atributo = atributoRepository.findById(atributoId).orElse(null);
		if (atributo != null) {
			valorAtributo.setAtributo(atributo); 
			return valorAtributoRepository.save(valorAtributo);
		}
		return null;
	}

	/**
	 * Consulta todos los valores atributos asociados a un atributo específico.
	 *
	 * @param atributoId id del atributo
	 * @return lista de valores atributos asociados al atributo
	 */
	@Transactional(readOnly = true)
	public List<ValorAtributo> getValoresAtributosByAtributo(Long atributoId) {
		return valorAtributoRepository.findByAtributo_IdAtributo(atributoId);
	}

	/**
	 * Elimina un vslot atributo por su id.
	 * 
	 * @param idValorAtributo id del valor atributo a eliminar
	 * @return el valor atributo eliminado o null si no existe
	 */
	public ValorAtributo deleteValorAtributo(Long idValorAtributo) {
		valorAtributoRepository.deleteById(idValorAtributo);
		return null;
	}

	/**
	 * Actualiza un valor atributo existente a partir de un DTO y su id.
	 * 
	 * @param idValorAtributo id del valor atributo a actualizar
	 * @param valor atributo   DTO con los nuevos datos del valor atributo
	 * @return el valor atributo actualizado
	 */
	@Transactional
	public ValorAtributo updateValorAtributo(Long idValorAtributo, ValorAtributoDto valorAtributo) {
		ValorAtributo valorAtributoEntity = valorAtributoRepository.findById(idValorAtributo)
				.orElseThrow(() -> new NoSuchElementException("ValorAtributo no encontrado con el ID: " + idValorAtributo));
		//valorAtributoEntity.setAtributo(valorAtributo.atributo());
		valorAtributoEntity.setValor(valorAtributo.valor());
		return valorAtributoRepository.save(valorAtributoEntity);
	}

}
