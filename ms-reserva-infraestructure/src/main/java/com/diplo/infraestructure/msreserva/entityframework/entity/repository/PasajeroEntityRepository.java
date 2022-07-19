package com.diplo.infraestructure.msreserva.entityframework.entity.repository;

import com.diplo.infraestructure.msreserva.entityframework.entity.PasajeroEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroEntityRepository
	extends CrudRepository<PasajeroEntity, UUID> {
	@Query(
		value = "SELECT * FROM pasajero WHERE nrodoc = :nrodoc and tipodoc = :tipodoc limit 1",
		nativeQuery = true
	)
	Future<PasajeroEntity> GetPasajeroByNroDocAndTipoDoc(
		@Param("nrodoc") Integer nrodoc,
		@Param("tipodoc") Integer tipodoc
	);
}
