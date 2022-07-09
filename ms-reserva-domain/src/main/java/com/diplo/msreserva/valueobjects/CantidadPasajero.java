package com.diplo.msreserva.valueobjects;

import com.diplo.sharekernel.core.ValueObject;

public class CantidadPasajero extends ValueObject{
	
	private final int Cantidad;

	public int getCantidad() {
		return Cantidad;
	}

	public CantidadPasajero(int cantidad) throws Exception {
		super();
		if(cantidad < 0) {
			throw new Exception("La cantidad no puede ser negativa");
		}
		Cantidad = cantidad;
	}
	
	public String getCantidadPasajero() {
		return Cantidad+"";
	}
	
	

}
