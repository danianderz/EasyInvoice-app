import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GtnClientPageRoutingModule } from './gtn-client-routing.module';

import { GtnClientPage } from './gtn-client.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GtnClientPageRoutingModule
  ],
  declarations: [GtnClientPage]
})
export class GtnClientPageModule {}
