import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { Reserva } from '../../service/reserva/reserva';
import { PasajeroService } from '../../service/pasajero/pasajero.service';
import { VueloService } from '../../service/vuelo/vuelo.service'; 
import { Vuelo } from 'src/app/service/vuelo/vuelo';
import { Pasajero } from 'src/app/service/pasajero/pasajero';
import { ReservaService } from 'src/app/service/reserva/reserva.service';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent implements OnInit {

  reservaACrear: Reserva;
  vuelo: Vuelo;
  pasajero: Pasajero;

  inputFormCrear = this.formBuilder.group({
    inputCrearNroReserva: '',
    inputCrearPrecio: '',
    inputCrearCantidadPasajero:''
  });

  constructor(
    private reservaService: ReservaService,
    private vueloService: VueloService,
    private pasajeroService: PasajeroService,
    private formBuilder: FormBuilder
  ) {
    this.reservaACrear = new Reserva();
    this.vuelo = this.vueloService.getVuelo();
    this.pasajero = this.pasajeroService.getPasajero();
    console.log("cargando reserva");
    console.log(this.vuelo.vueloId);
    console.log(this.pasajero.pasajeroId);
   }

  ngOnInit(): void {
  }

  onCrearReserva(){
    this.reservaACrear.nroReserva = this.inputFormCrear.value.inputCrearNroReserva;
    this.reservaACrear.precio = this.inputFormCrear.value.inputCrearPrecio;
    this.reservaACrear.cantidadPasajero = this.inputFormCrear.value.inputCrearCantidadPasajero;
    this.vuelo = this.vueloService.getVuelo();
    this.pasajero = this.pasajeroService.getPasajero();
    console.log(this.vuelo.vueloId);
    console.log(this.pasajero.pasajeroId);
    if(this.vuelo.vueloId && this.pasajero.pasajeroId){
      this.reservaACrear.vueloId = this.vuelo.vueloId;
      this.reservaACrear.pasajeroId = this.pasajero.pasajeroId;
      if(this.reservaService.crearReservaBackEnd(this.reservaACrear)){
        this.inputFormCrear.reset();
        this.vueloService.clear();
        this.pasajeroService.clear();
      }
    }else{
      alert("antes de crear una reserva se debe de escoger un vuelo y un pasajero");
    }
    
  }

}
