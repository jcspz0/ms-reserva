import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from './app.component';
import { VueloComponent } from './component/vuelo/vuelo.component'; 
import { PasajeroComponent } from './component/pasajero/pasajero.component'; 
import { ReservaComponent } from './component/reserva/reserva.component';

const routes: Routes = [
    //{path:'', component: AppComponent},
    {path:'vuelo', component: VueloComponent},
    {path:'pasajero', component: PasajeroComponent},
    {path:'reserva', component: ReservaComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
