import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GtnEstablecimientosPage } from './gtn-establecimientos.page';

const routes: Routes = [
  {
    path: '',
    component: GtnEstablecimientosPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GtnEstablecimientosPageRoutingModule {}
