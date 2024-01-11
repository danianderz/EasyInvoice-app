import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'gtn-products',
    loadChildren: () => import('./pages/gtn-products/gtn-products.module').then( m => m.GtnProductsPageModule)
  },
  {
    path: 'gtn-client',
    loadChildren: () => import('./pages/gtn-client/gtn-client.module').then( m => m.GtnClientPageModule)
  },
  {
    path: 'perfil-usuario',
    loadChildren: () => import('./pages/perfil-usuario/perfil-usuario.module').then( m => m.PerfilUsuarioPageModule)
  },  {
    path: 'gtn-establecimientos',
    loadChildren: () => import('./pages/gtn-establecimientos/gtn-establecimientos.module').then( m => m.GtnEstablecimientosPageModule)
  },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
