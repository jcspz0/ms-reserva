package com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroByNroDocAndTipoDoc;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocQuery;
import org.junit.jupiter.api.Test;

class GetPasajeroByNroDocAndTipoDocQueryTest {

	@Test
	void GetPasajeroByNroDocAndTipoDocQuery() {
		int nroDoc = 1;
		int tipoDoc = 1;

		GetPasajeroByNroDocAndTipoDocQuery getPasajeroByNroDocAndTipoDocQuery = new GetPasajeroByNroDocAndTipoDocQuery();

		getPasajeroByNroDocAndTipoDocQuery.setNroDoc(nroDoc);
		getPasajeroByNroDocAndTipoDocQuery.setTipoDoc(tipoDoc);

		getPasajeroByNroDocAndTipoDocQuery =
			new GetPasajeroByNroDocAndTipoDocQuery(nroDoc, tipoDoc);

		assertEquals(tipoDoc, getPasajeroByNroDocAndTipoDocQuery.getTipoDoc());
		assertEquals(nroDoc, getPasajeroByNroDocAndTipoDocQuery.getNroDoc());
	}
}
