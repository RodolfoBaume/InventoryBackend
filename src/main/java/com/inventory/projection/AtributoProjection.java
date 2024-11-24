package com.inventory.projection;

import java.util.List;

public interface AtributoProjection {
	Long getIdAtributo();
    String getAtributo();
    List<ValorAtributoProjection> getValores();
}
