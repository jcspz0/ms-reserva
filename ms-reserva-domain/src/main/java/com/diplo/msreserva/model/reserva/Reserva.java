package com.diplo.msreserva.model.reserva;

import java.time.LocalDateTime;
import java.util.UUID;

import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.HoraReserva;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.sharekernel.core.AggregateRoot;

public class Reserva extends AggregateRoot<UUID>{

	private NumeroReserva NroReserva;
	private HoraReserva Hora;
	private UUID PasajeroId;
	private UUID VueloId;
	private Monto Precio;
	private CantidadPasajero CantidadPasajero;
	private String Estado;
	
	
	public Reserva(NumeroReserva nroReserva, UUID pasajero, 
			UUID vueloId, Monto precio, CantidadPasajero cantidadPasajero) {
		super();
		Id = UUID.randomUUID();
		this.NroReserva = nroReserva;
		Hora = new HoraReserva(LocalDateTime.now());
		this.PasajeroId = pasajero;
		VueloId = vueloId;
		this.Precio = precio;
		this.CantidadPasajero = cantidadPasajero;
		this.Estado = "VALIDO";
	}
	
	public Reserva(UUID reservaId, NumeroReserva nroReserva, UUID pasajero, 
			UUID vueloId, Monto precio, CantidadPasajero cantidadPasajero) {
		super();
		Id = reservaId;
		this.NroReserva = nroReserva;
		Hora = new HoraReserva(LocalDateTime.now());
		this.PasajeroId = pasajero;
		VueloId = vueloId;
		this.Precio = precio;
		this.CantidadPasajero = cantidadPasajero;
		this.Estado = "VALIDO";
	}
	
	public Reserva(UUID reservaId, NumeroReserva nroReserva, UUID pasajero, 
			UUID vueloId, Monto precio, CantidadPasajero cantidadPasajero, String estado) {
		super();
		Id = reservaId;
		this.NroReserva = nroReserva;
		Hora = new HoraReserva(LocalDateTime.now());
		this.PasajeroId = pasajero;
		VueloId = vueloId;
		this.Precio = precio;
		this.CantidadPasajero = cantidadPasajero;
		this.Estado = estado;
	}
	
	public Reserva(String nroReserva, UUID pasajero, UUID vueloId, 
			double precio, int cantidadPasajero) throws Exception {
		super();
		Id = UUID.randomUUID();
		this.NroReserva = new NumeroReserva(nroReserva);
		Hora = new HoraReserva(LocalDateTime.now());
		this.PasajeroId = pasajero;
		VueloId = vueloId;
		this.Precio = new Monto(precio);
			this.CantidadPasajero = new CantidadPasajero(cantidadPasajero);
			// TODO Auto-generated catch block
			System.out.println("Se tuvo un error al cargar la cantidad de pasajero");
		this.Estado = "VALIDO";
	}
	
	public Reserva(String reservaId, String nroReserva, String pasajeroId, String vueloId, double precio, int cantidadPasajero) {
		super();
		Id = UUID.fromString(reservaId);
		this.NroReserva = new NumeroReserva(nroReserva);
		Hora = new HoraReserva(LocalDateTime.now());
		this.PasajeroId = UUID.fromString(pasajeroId);
		VueloId = UUID.fromString(vueloId);
		this.Precio = new Monto(precio);
		try {
			this.CantidadPasajero = new CantidadPasajero(cantidadPasajero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Estado = "VALIDO";
	}

	public boolean RealizarReserva(Vuelo vuelo) {
		if(vuelo.ValidarDisponibilidad(this.getCantidadPasajero().getCantidad())) {
			AddDomainEvent(new ReservaCreada(this.Id, this.NroReserva, this.VueloId, this.PasajeroId, this.Hora));
			return true;
		}
		return false;
		
	}
	
	public HoraReserva getHora() {
		return Hora;
	}
	
	public UUID getPasajeroId() {
		return PasajeroId;
	}
	
	public UUID getVueloId() {
		return VueloId;
	}
	
	public Monto getPrecio() {
		return Precio;
	}

	public CantidadPasajero getCantidadPasajero() {
		return CantidadPasajero;
	}

	public NumeroReserva getNroReserva() {
		return NroReserva;
	}
	
	public String getEstado() {
		return Estado;
	}

	public String verReserva() {
		return "reserva: "+this.getId()+" , pasajero: "
				+this.getPasajeroId()+" , vuelo: "+this.getVueloId() + ", cantidad de pasajeros " +
				this.getCantidadPasajero().getCantidadPasajero() + ", al precio de " + this.Precio.getMonto();
	}
	
}
