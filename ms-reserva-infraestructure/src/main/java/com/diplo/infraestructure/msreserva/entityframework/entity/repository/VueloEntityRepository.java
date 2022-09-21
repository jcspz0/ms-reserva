package com.diplo.infraestructure.msreserva.entityframework.entity.repository;

import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloEntityRepository
	extends CrudRepository<VueloEntity, String> {
	@Query(
		value = "SELECT * FROM vuelo WHERE origen = :myorigen and destino = :mydestino",
		nativeQuery = true
	)
	List<VueloEntity> GetVuelosByDestino(
		@Param("myorigen") String origen,
		@Param("mydestino") String destino
	);
	//@Query(value = "SELECT * FROM vuelo WHERE origen = ?1 and destino = ?2", nativeQuery = true)
	//List<VueloEntity> GetVuelosByDestino(String origen, String destino);

}
