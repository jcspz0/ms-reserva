export interface IVuelo {
    vueloId: string;
    nroVuelo: number;
    destino: string;
    cantidadAsientoDisponible: number;
  }

  export class  Vuelo implements IVuelo{
    vueloId: string = "";
    nroVuelo: number = 0;
    destino: string = "";
    cantidadAsientoDisponible: number = 0;
  }
  
