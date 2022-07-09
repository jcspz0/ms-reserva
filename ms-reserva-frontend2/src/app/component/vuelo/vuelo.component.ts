import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VueloService } from 'src/app/service/vuelo/vuelo.service'; 
import { Vuelo } from '../../service/vuelo/vuelo';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-vuelo',
  templateUrl: './vuelo.component.html',
  styleUrls: ['./vuelo.component.css']
})
export class VueloComponent implements OnInit {

  vuelos: Observable<Vuelo[]>;
  //vuelos = this.vueloService.getVuelosByDestino();
  
  destino: string;

  vueloSeleccionado: Vuelo;

  inputForm = this.formBuilder.group({
    destino: ''
  });

  constructor(
    private vueloService: VueloService,
    private formBuilder: FormBuilder
  ) {
    this.vuelos = new Observable;
    this.destino = "";
    this.vueloSeleccionado = vueloService.getVuelo();
   }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.destino = this.inputForm.value.destino;
    this.vuelos = this.vueloService.getVuelosByDestinoBackEnd(this.destino);
    console.log(this.vuelos);
    console.warn('Your order has been submitted', this.destino);
    this.inputForm.reset();
  }

  onSeleccionar(vuelo: Vuelo){
    this.vueloSeleccionado = vuelo;
    this.vueloService.setVuelo(this.vueloSeleccionado);
  }
  
}