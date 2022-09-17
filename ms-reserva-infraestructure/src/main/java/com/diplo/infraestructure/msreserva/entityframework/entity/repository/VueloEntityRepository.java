package com.diplo.infraestructure.msreserva.entityframework.entity.repository;

import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloEntityRepository
	extends CrudRepository<VueloEntity, String> {
	@Query(
		value = "SELECT * FROM vuelo WHERE origen = :origen and destino = :destino",
		nativeQuery = true
	)
	List<VueloEntity> GetVuelosByDestino(
		@Param("origen") String origen,
		@Param("destino") String destino
	);
}
