import { Component, OnInit } from '@angular/core';
import { DataSharingService } from '../../services/data-sharing.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  
  public items = [
    { name: 'Perfil', image: 'assets/icon/icon-perfil.svg', redirect:"perfil-usuario"},
    { name: 'Gestión Productos', image: 'assets/icon/icon-gtnproducts.svg', redirect:"gtn-products" },
    { name: 'Gestión Clientes', image: 'assets/icon/icon-gtnclient.svg', redirect:"gtn-client" },
    { name: 'Facturar', image: 'assets/icon/icon-facturar.svg', redirect:"#" },
    { name: 'Administrador', image: 'assets/icon/icon-gtnestablec.svg' , redirect:"gtn-establecimientos"},
    { name: 'Cerrar Sesión', image: 'assets/icon/icon-logout.svg', redirect:"login" }
  ];

  public itemsUserStand = [
    { name: 'Perfil', image: 'assets/icon/icon-perfil.svg', redirect:"perfil-usuario"},
    { name: 'Gestión Productos', image: 'assets/icon/icon-gtnproducts.svg', redirect:"gtn-products" },
    { name: 'Gestión Clientes', image: 'assets/icon/icon-gtnclient.svg', redirect:"gtn-client" },
    { name: 'Facturar', image: 'assets/icon/icon-facturar.svg', redirect:"#" },
    { name: 'Cerrar Sesión', image: 'assets/icon/icon-logout.svg', redirect:"login" }
  ];

  private receivedData: any;
  namesUser!: string;
  rolUser!: string;

  usuarioLogueado!: boolean;

  constructor(private dataSharingService: DataSharingService) {}

  ngOnInit() { }

  onMenuOpen() {
    this.receivedData = this.dataSharingService.getJsonData();
    this.namesUser = this.receivedData.nomb_usuario + ' ' + this.receivedData.apell_usuario;
    const tipUser = this.receivedData.tip_usuario;
    if (tipUser === 1) {
      this.rolUser = "Administrador"
      this.usuarioLogueado=true;
    } else {
      this.rolUser = "Estándar"
      this.usuarioLogueado=false;
    }
  }


}
