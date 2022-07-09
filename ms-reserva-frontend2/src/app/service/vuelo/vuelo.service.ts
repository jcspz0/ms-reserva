import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Vuelo } from './vuelo'; 
import { filter, tap } from 'rxjs';
import { map } from 'rxjs/operators';
import { cloneDeep } from "lodash";
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class VueloService {

  vuelo: Vuelo;

  constructor(
    private http: HttpClient
  ) { 
    this.vuelo = new Vuelo();
  }

    setVuelo(vuelo: Vuelo){
      this.vuelo = cloneDeep(vuelo);
    }

    getVuelo(){
      return cloneDeep(this.vuelo);
    }

    getVuelos(){
      return this.http.get<Vuelo[]>('/assets/vuelos.json');
    }

    getVuelosByDestino(destinoEsperado: string){
      return this.http.get<Vuelo[]>('/assets/vuelos.json').pipe(
        map( vuelos => vuelos.filter(vuelo => vuelo.destino == destinoEsperado) )
      )
    }

    getVuelosByDestinoBackEnd(destinoEsperado: string){
      //const url = 'http://localhost:8080/vuelo/buscarvuelos';
      const url = environment.apiHost+':'+environment.apiPort+'/vuelo/buscarvuelos';
      let queryParams = new HttpParams().append("destino", destinoEsperado);
      let response = this.http.get<Vuelo[]>(
        url,
        {params:queryParams},
        //{headers: {'Access-Control-Allow-Origin':'*'}}
        );
        
        //return JSON.parse(JSON.stringify(response));
        return cloneDeep(response);
        //return Object.assign({}, response);

    }

    clear(){
      this.vuelo = new Vuelo();
    }

}
