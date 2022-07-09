import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { filter, tap } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Pasajero } from './pasajero';
import { cloneDeep } from "lodash";
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class PasajeroService {

  pasajero: Pasajero;

  constructor(
    private httpget: HttpClient,
    private httppost: HttpClient,
    private http: HttpClient
  ) { 
    this.pasajero = new Pasajero();
  }

  getPasajeroByNroDocTipoDocBackEnd(nrodoc: number, tipodoc: number){
    const url = environment.apiHost+':'+environment.apiPort+'/pasajero/buscarpasajero';
    let queryParams = new HttpParams().append("nrodoc", nrodoc).append("tipodoc", tipodoc);
    
    //return 
    let response = cloneDeep(this.httpget.get<Pasajero>(
      url,
      {params:queryParams},
      //{headers: {'Access-Control-Allow-Origin':'*'}}
      ));
      /*.subscribe(
        res => {
          this.pasajero = cloneDeep(res);
        }
      );*/
      //return JSON.parse(JSON.stringify(response));
      return cloneDeep(response);
  }

  crearPasajeroBackEnd(pasajeroACrear: Pasajero){
    const url = environment.apiHost+':'+environment.apiPort+'/pasajero/crear';
    console.log("crear bk");
    console.log(pasajeroACrear);
    //let response = cloneDeep(this.httppost.post<Pasajero>(
    this.httppost.post<Pasajero>(
      url,
      pasajeroACrear
      )
      .subscribe(
        res => {
          console.log(res);
          alert("Pasajero creado");
        }
      
      //).pipe()
    );
      //return JSON.parse(JSON.stringify(response));
      //console.log(response);
      //return cloneDeep(response);
  }

  getPasajeroFile(){
    return this.http.get<Pasajero[]>('/assets/pasajero.json');
  }

  setPasajero(pasajero: Pasajero){
    this.pasajero = cloneDeep(pasajero);
  }

  getPasajero(){
    return cloneDeep(this.pasajero);
  }

  clear(){
    this.pasajero = cloneDeep(new Pasajero());
  }


}
