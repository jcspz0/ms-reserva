package com.diplo.msreserva.valueobjects;

import com.diplo.sharekernel.core.ValueObject;

public final class DocumentoIdentidad extends ValueObject {
	
	private final int NroDoc;
	private final int TipoDoc;
	private final String NombreTipoDoc;
	
	public DocumentoIdentidad(int nroDoc, int tipoDoc) throws Exception {
		super();
		NroDoc = nroDoc;
		switch (tipoDoc) {
		case 1:
			NombreTipoDoc = "Carnet de identidad";
			break;
		case 2:
			NombreTipoDoc = "Pasaporte";
			break;
		default:
			throw new Exception("Tipo de documento no valido");
		}
		TipoDoc = tipoDoc;
	}
	
	
	
	public int getNroDoc() {
		return NroDoc;
	}



	public int getTipoDoc() {
		return TipoDoc;
	}



	public String getDocumentoIdentidad() {
		return "Numero de documento: " + NroDoc + " , Tipo de documento: " + NombreTipoDoc; 
	}

}
