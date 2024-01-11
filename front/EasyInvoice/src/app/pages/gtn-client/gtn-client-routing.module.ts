import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GtnClientPage } from './gtn-client.page';

const routes: Routes = [
  {
    path: '',
    component: GtnClientPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GtnClientPageRoutingModule {}
