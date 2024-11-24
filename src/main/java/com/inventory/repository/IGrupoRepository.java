package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Atributo;
import com.inventory.entity.Grupo;
import com.inventory.projection.GrupoProjection;

@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Long> {
	
	/*
	@Query("SELECT g.idGrupo AS idGrupo, g.nombreGrupo AS nombreGrupo, g.status AS status, "
			+ "a.idAtributo AS idAtributo, a.atributo AS atributo, "
			+ "v.idValorAtributo AS idValorAtributo, v.valor AS valor " + "FROM Grupo g " + "JOIN g.atributos a "
			+ "JOIN a.valores v " + "WHERE g.idGrupo = :idGrupo")
	List<GrupoPlanoProjection> findGrupoWithAtributosAndValoresFlat(Long idGrupo);
	*/
	
	/*
	@EntityGraph(attributePaths = {"grupo", "valores"})
    List<Atributo> findByGrupo_IdGrupo(Long grupoId);
	*/
	
	@Query("SELECT g FROM Grupo g " +
	           "LEFT JOIN FETCH g.atributos a " +
	           "LEFT JOIN FETCH a.valores v " +
	           "WHERE g.idGrupo = :idGrupo")
	GrupoProjection findGrupoWithDetailsById(Long idGrupo);
	
	// Obtener solo los datos básicos del grupo
    @Query("SELECT g FROM Grupo g WHERE g.idGrupo = :idGrupo")
    Grupo findGrupoById(Long idGrupo);

    // Cargar los atributos de un grupo
    @Query("SELECT a FROM Atributo a JOIN FETCH a.valores WHERE a.grupo.idGrupo = :idGrupo")
    List<Atributo> findAtributosByGrupoId(Long idGrupo);
    
}
