package com.diplo.infraestructure.msreserva.entityframework.entity.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;


@Repository
public interface ReservaEntityRepository extends CrudRepository<ReservaEntity, String>{

	
	@Query(value = "SELECT * FROM reserva WHERE hora > :date and estado = :estado", nativeQuery = true)
	List<ReservaEntity> GetReservasByHoraAndEstado(LocalDateTime date, String estado);
}
