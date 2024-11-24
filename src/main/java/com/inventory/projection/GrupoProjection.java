package com.inventory.projection;

import java.util.List;

public interface GrupoProjection {
	Long getIdGrupo();
	String getNombreGrupo();
	Boolean getStatus();
	List<AtributoProjection> getAtributos();
}
