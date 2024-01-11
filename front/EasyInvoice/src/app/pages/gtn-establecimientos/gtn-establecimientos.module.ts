import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GtnEstablecimientosPageRoutingModule } from './gtn-establecimientos-routing.module';

import { GtnEstablecimientosPage } from './gtn-establecimientos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GtnEstablecimientosPageRoutingModule
  ],
  declarations: [GtnEstablecimientosPage]
})
export class GtnEstablecimientosPageModule {}
