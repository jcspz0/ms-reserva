package com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetPasajeroByNroDocAndTipoDocQueryTest {

	@Test
	void GetPasajeroByNroDocAndTipoDocQuery() {
		
		int nroDoc=1;
		int tipoDoc=1;
		
		GetPasajeroByNroDocAndTipoDocQuery getPasajeroByNroDocAndTipoDocQuery = new GetPasajeroByNroDocAndTipoDocQuery();
		
		getPasajeroByNroDocAndTipoDocQuery.setNroDoc(nroDoc);
		getPasajeroByNroDocAndTipoDocQuery.setTipoDoc(tipoDoc);
		
		getPasajeroByNroDocAndTipoDocQuery = new GetPasajeroByNroDocAndTipoDocQuery(nroDoc, tipoDoc);
		
		assertEquals(tipoDoc, getPasajeroByNroDocAndTipoDocQuery.getTipoDoc());
		assertEquals(nroDoc, getPasajeroByNroDocAndTipoDocQuery.getNroDoc());
	}

}
