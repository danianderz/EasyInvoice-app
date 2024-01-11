import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ToastController, AlertController, IonModal } from '@ionic/angular';
import { DataSharingService } from '../../services/data-sharing.service';

@Component({
  selector: 'app-gtn-establecimientos',
  templateUrl: './gtn-establecimientos.page.html',
  styleUrls: ['./gtn-establecimientos.page.scss'],
})
export class GtnEstablecimientosPage implements OnInit {

  showBackdrop: boolean = false;

  selectedOptionEstab: any;
  optionsTipDni: any[] = ["Cédula", "RUC", "Pasaporte"];
  optionsTipUser: any[] = ["Administrador", "Estándar"];
  optionsEstab!: any[];

  selectedItem!: any;

  private items: any[] = [];
  private listWithOutFilter: any[] = [];
  listFilterUserEstab!: any[];

  private receivedData: any;
  private id_user!: string;

  @ViewChild('modalForm') modalForm!: IonModal;
  @ViewChild('modalDtVend') modalDtVend!: IonModal;
  @ViewChild('modalGtnEstab') modalGtnEstab!: IonModal;

  btnNameGtnEstab: string = "Registrar";
  isDisabledBtnElim: boolean = true;

  selectedOptEstabGtn: any;
  nombre!: string;
  telefono!: string;
  direccion!: string;
  email!: string;

  isDisabledInpTienda: boolean = true;
  selectedOptTiendaGtn: any;
  optionsTienda!: any[];

  titleModalForm!: string;
  nameBtnModalForm!: string;
  isDisabledInpNumIdent!: boolean;
  isDisabledInpTipDni!: boolean;
  selectedTipDni: any;
  selectedEstab: any;
  selectedTipUser: any;

  id_usuario!: string;
  id_tip_dni!: string;
  id_establ_per!: string;
  num_ident!: string;
  nomb_usuario!: string;
  apell_usuario!: string;
  email_usuario!: string;
  telef_usuario!: string;
  direc_usuario!: string;
  estado_usuario!: string;
  tip_usuario!: string;
  contrasenia!: string;

  constructor(private alertController: AlertController, private http: HttpClient,
    private toastController: ToastController, private dataSharingService: DataSharingService,) {
    this.recoverEstab();
  }

  ngOnInit() {
    this.initDataUser();
  }

  async alertInputEmpty() {
    const toast = await this.toastController.create({
      message: 'Todos los campos son obligatorios',
      duration: 3000, // Duración en milisegundos
      position: 'bottom', // Posición del mensaje ('top', 'middle', o 'bottom')
      color: 'danger' // Color del mensaje de error
    });
    toast.present();
  }

  async alertDelete() {
    const toast = await this.toastController.create({
      message: 'Se eliminó correctamente',
      duration: 3000, // Duración en milisegundos
      position: 'bottom', // Posición del mensaje ('top', 'middle', o 'bottom')
    });
    toast.present();
  }

  async alertInsertCorrectly() {
    const toast = await this.toastController.create({
      message: 'Se registrado correctamente',
      duration: 3000, // Duración en milisegundos
      position: 'bottom' // Posición del mensaje ('top', 'middle', o 'bottom')
    });
    toast.present();
  }

  async alertNumIdent() {
    const toast = await this.toastController.create({
      message: 'El número de indentificación ingresado ya existe',
      duration: 3000, // Duración en milisegundos
      position: 'bottom', // Posición del mensaje ('top', 'middle', o 'bottom')
      color: 'danger' // Color del mensaje de error
    });
    toast.present();
  }

  async presentAlert(action: () => void, msj: any) {
    const alert = await this.alertController.create({
      header: '¿Desea eliminar ' + msj + '?',
      buttons: [
        {
          text: 'No',
          cssClass: 'alert-button-cancel',
        },
        {
          text: 'Sí',
          cssClass: 'alert-button-confirm',
          handler: () => {
            action();
          }
        },
      ]
    });
    await alert.present();
  }

  initDataUser() {
    this.receivedData = this.dataSharingService.getJsonData();
    this.id_user = this.receivedData.id_usuario;
    this.loadUsers();
  }

  clearSelectionEstab() {
    this.listFilterUserEstab = [];
    this.selectedOptionEstab = null;
  }

  clearSelectionTipDni() {
    this.selectedTipDni = null;
  }

  clearSelectionTipUser() {
    this.selectedTipDni = null;
  }

  clearSelectionEstabGtn() {
    this.selectedOptTiendaGtn = null
    this.selectedOptEstabGtn = null;
    this.btnNameGtnEstab = "Registrar"
    this.isDisabledBtnElim = true;
    this.clearInputsGtnEstab();
  }

  clearSelectionTiendaGtn() {
    this.selectedOptTiendaGtn = null;
  }

  openModalForm() {
    this.modalForm.present();
  }

  closeModalForm() {
    this.clearInputs()
    this.modalForm.dismiss()
  }

  closeModalGtnEstab() {
    this.modalGtnEstab.dismiss();
    this.clearSelectionEstabGtn();
  }

  openModalGtnEstab() {
    this.modalGtnEstab.present();
    this.getTiendas();
  }

  openModalDtVend(item: any) {
    this.modalDtVend.present();
    this.selectedItem = item;
  }

  clearInputs() {
    this.num_ident = "";
    this.nomb_usuario = "";
    this.apell_usuario = "";
    this.email_usuario = "";
    this.telef_usuario = "";
    this.direc_usuario = "";
    this.contrasenia = "";
    this.clearSelectionTipDni();
    this.clearSelectionTipUser();
    this.clearSelectionEstabGtn();
  }

  clearInputsGtnEstab() {
    this.nombre = "";
    this.telefono = "";
    this.direccion = "";
    this.email = "";
  }

  selectEstabChange() {
    if (this.selectedOptionEstab) {
      this.getUserForIdEstab(this.selectedOptionEstab);
    }
  }

  selectEstabGtnChange() {
    if (this.selectedOptEstabGtn) {
      this.recoverEstabForID(this.selectedOptEstabGtn);
    }
  }

  goBack() {
    this.closeModalForm();
  }

  onSearchChange(event: any) {
    const searchTerm = event.target.value;
    if (searchTerm.trim() !== '') {
      this.listFilterUserEstab = this.listFilterUserEstab.filter(item =>
        item.num_ident.toLowerCase().includes(searchTerm.toLowerCase())
      );
    } else {
      this.listFilterUserEstab = this.listWithOutFilter;
    }
  }

  addVendedores() {
    this.titleModalForm = "Registro de Usuario";
    this.nameBtnModalForm = "Registrar";
    this.isDisabledInpNumIdent = false;
    this.openModalForm();
  }

  setTitleFormProd() {
    this.titleModalForm = "Modificar Usuario";
    this.nameBtnModalForm = "Modificar";
    this.isDisabledInpTipDni = true;
    this.isDisabledInpNumIdent = true;
    this.openModalForm();
  }

  recoverEstab() {
    const url = "http://localhost:8080/establishment/get-establishment";
    this.http.get<any[]>(url).subscribe(
      (response) => {
        if (response !== null) {
          this.optionsEstab = response.filter(estab => estab.est_establecimiento !== 0);
        }
      },
      (error) => {
        console.error('Error al recuperar ciudades:', error);
      }
    );
  }

  recoverEstabForID(id_estab: string) {
    const url = 'http://localhost:8080/establishment/' + id_estab;
    this.http.get<any[]>(url).subscribe(
      (response: any) => {
        if (response !== null) {
          this.selectedOptTiendaGtn = response.id_tienda_per;
          this.nombre = response.nombre;
          this.telefono = response.telefono;
          this.direccion = response.direccion;
          this.email = response.email;
          this.btnNameGtnEstab = "Modificar"
          this.isDisabledBtnElim = false;

        }
      },
      (error) => {
        console.error('Error al recuperar promoción:', error);
      }
    );
  }

  getTiendas() {
    const url = 'http://localhost:8080/store/get-stores';
    this.http.get<any[]>(url).subscribe(
      (response: any[]) => {
        if (response !== null) {
          this.optionsTienda = response;
          if (this.optionsTienda.length > 1) {
            this.isDisabledInpTienda = false;
          } else {
            this.selectedOptTiendaGtn = response[0].id_tienda;
          }
        }
      },
      (error) => {
        console.error('Error al recuperar promoción:', error);
      }
    );
  }

  loadUsers() {
    const url = 'http://localhost:8080/user/get-user';
    this.http.get<any[]>(url).subscribe(
      (response) => {
        if (response !== null) {
          this.items = response.filter(user => user.estado_usuario !== 0 && user.id_usuario !== this.id_user);
          console.log(this.items);
        }
      },
      (error) => {
        console.error('Error al recuperar ciudades:', error);
      }
    );
  }

  insertUser() {
    if (this.isEmptyInput(this.num_ident, this.nomb_usuario, this.apell_usuario, this.email_usuario,
      this.telef_usuario, this.direc_usuario, this.contrasenia, this.selectedTipDni, this.selectedEstab, this.selectedTipUser)) {

      if (this.validateNumIdent(this.num_ident)) {
        const url = 'http://localhost:8080/user/save-user';

        const id_tip_dni = this.getIdTipDni(this.selectedTipDni);
        const id_establ_per = this.selectedEstab;
        const tip_usuario = this.getIdTipUser(this.selectedTipUser);

        const userData = JSON.stringify({
          id_tip_dni: id_tip_dni,
          id_establ_per: id_establ_per,
          num_ident: this.num_ident,
          nomb_usuario: this.nomb_usuario,
          apell_usuario: this.apell_usuario,
          email_usuario: this.email_usuario,
          telef_usuario: this.telef_usuario,
          direc_usuario: this.direc_usuario,
          estado_usuario: 1,
          tip_usuario: tip_usuario,
          contrasenia: this.contrasenia
        });

        const headers = {
          'Content-Type': 'application/json'
        };

        this.http.post(url, userData, { headers }).subscribe(
          (response) => {
            if (response != null) {
              this.alertInsertCorrectly;
              this.closeModalForm();
              this.loadUsers();
            }
          },
          (error) => {
            console.error(error);
          }
        );
      } else {
        this.alertNumIdent();
        this.num_ident = "";
      }
    }
  }

  updateUser(id_user: string) {
    if (this.isEmptyInput(this.num_ident, this.nomb_usuario, this.apell_usuario, this.email_usuario,
      this.telef_usuario, this.direc_usuario, this.contrasenia, this.selectedTipDni, this.selectedEstab, this.selectedTipUser)) {

      const url = 'http://localhost:8080/user/modif-user/' + id_user;

      const id_tip_dni = this.getIdTipDni(this.selectedTipDni);
      const id_establ_per = this.selectedEstab;
      const tip_usuario = this.getIdTipUser(this.selectedTipUser);

      const userData = JSON.stringify({
        id_tip_dni: id_tip_dni,
        id_establ_per: id_establ_per,
        num_ident: this.num_ident,
        nomb_usuario: this.nomb_usuario,
        apell_usuario: this.apell_usuario,
        email_usuario: this.email_usuario,
        telef_usuario: this.telef_usuario,
        direc_usuario: this.direc_usuario,
        estado_usuario: 1,
        tip_usuario: tip_usuario,
        contrasenia: this.contrasenia
      });

      const headers = {
        'Content-Type': 'application/json',
      };

      this.http.put(url, userData, { headers }).subscribe(
        (response: any) => {
          this.alertInsertCorrectly();
          this.closeModalForm();
          this.loadUsers();
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }

  deleteUser(item: any) {
    const url = 'http://localhost:8080/user/modif-user/' + item.id_usuario;

    const userData = JSON.stringify({
      id_tip_dni: item.id_tip_dni,
      id_establ_per: item.id_establ_per,
      num_ident: item.num_ident,
      nomb_usuario: item.nomb_usuario,
      apell_usuario: item.apell_usuario,
      email_usuario: item.email_usuario,
      telef_usuario: item.telef_usuario,
      direc_usuario: item.direc_usuario,
      estado_usuario: 0,
      tip_usuario: item.tip_usuario,
      contrasenia: item.contrasenia
    });

    const headers = {
      'Content-Type': 'application/json',
    };

    this.http.put(url, userData, { headers }).subscribe(
      (response: any) => {
        this.alertDelete();
        this.clearSelectionEstab();
        this.loadUsers();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  insertEstab() {
    if (this.isEmptyInputEstab(this.nombre, this.telefono, this.direccion,
      this.email, this.selectedOptTiendaGtn)) {

      const url = 'http://localhost:8080/establishment/save-establishment';

      const id_tienda_per = this.selectedOptTiendaGtn

      const estabData = JSON.stringify({
        id_tienda_per: id_tienda_per,
        nombre: this.nombre,
        telefono: this.telefono,
        direccion: this.direccion,
        email: this.email,
        est_establecimiento: 1,
      });

      const headers = {
        'Content-Type': 'application/json'
      };

      this.http.post(url, estabData, { headers }).subscribe(
        (response) => {
          if (response != null) {
            this.alertInsertCorrectly;
            this.closeModalGtnEstab();
            this.recoverEstab();
          }
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }

  updateEstab(id_estab: string) {
    const url = 'http://localhost:8080/establishment/modif-establishment/' + id_estab;
    const id_tienda_per = this.selectedOptTiendaGtn

    const estabData = JSON.stringify({
      id_tienda_per: id_tienda_per,
      nombre: this.nombre,
      telefono: this.telefono,
      direccion: this.direccion,
      email: this.email,
      est_establecimiento: 1,
    });

    const headers = {
      'Content-Type': 'application/json',
    };

    this.http.put(url, estabData, { headers }).subscribe(
      (response: any) => {
        this.alertInsertCorrectly();
        this.closeModalGtnEstab();
        this.recoverEstab();
      },
      (error) => {
        console.error(error);
      }
    );
  }

  deleteEstab(id_estab: string) {
    const url = 'http://localhost:8080/establishment/modif-establishment/' + id_estab;
    const id_tienda_per = this.selectedOptTiendaGtn

    const estabData = JSON.stringify({
      id_tienda_per: id_tienda_per,
      nombre: this.nombre,
      telefono: this.telefono,
      direccion: this.direccion,
      email: this.email,
      est_establecimiento: 0,
    });

    const headers = {
      'Content-Type': 'application/json',
    };

    this.http.put(url, estabData, { headers }).subscribe(
      (response: any) => {
        this.alertDelete();
        this.closeModalGtnEstab();
        this.recoverEstab();
      },
      (error) => {
        console.error(error);
      }
    );
  }


  isEmptyInput(num_ident: string, nomb_usuario: string, apell_usuario: string, email_usuario: string, telef_usuario: string,
    direc_usuario: string, contrasenia: string, selectedTipDni: string, selectedEstab: string, selectedTipUser: string) {

    if (!num_ident || !nomb_usuario || !apell_usuario || !email_usuario || !telef_usuario
      || !direc_usuario || !contrasenia || /^\s+|\s+$/g.test(num_ident) || /^\s+|\s+$/g.test(nomb_usuario)
      || /^\s+|\s+$/g.test(apell_usuario) || /^\s+|\s+$/g.test(email_usuario) || /^\s+|\s+$/g.test(telef_usuario)
      || /^\s+|\s+$/g.test(direc_usuario) || /^\s+|\s+$/g.test(contrasenia)
      || selectedTipDni === undefined || selectedTipUser === undefined || selectedEstab === undefined) {
      this.alertInputEmpty();
      return false;
    } else {
      return true;
    }
  }

  isEmptyInputEstab(nombre: string, telefono: string, direccion: string,
    email: string, selectedEstab: string) {

    if (!nombre || !telefono || !direccion || !email || /^\s+|\s+$/g.test(nombre)
      || /^\s+|\s+$/g.test(telefono) || /^\s+|\s+$/g.test(direccion)
      || /^\s+|\s+$/g.test(email) || selectedEstab === undefined) {
      this.alertInputEmpty();
      return false;
    } else {
      return true;
    }
  }

  validateNumIdent(num_ident: string) {
    for (const item of this.items) {
      if (item.num_ident === num_ident) {
        return false;
      }
    }
    return true;
  }

  getIdTipDni(selectedTipDni: string) {
    if (selectedTipDni === "Cédula") {
      return 1;
    } else if (selectedTipDni === "RUC") {
      return 2;
    } else {
      return 3;
    }
  }

  getIdTipUser(selectedTipUser: string) {
    if (selectedTipUser === "Administrador") {
      return 1;
    } else {
      return 0;
    }
  }

  getNomTipDni(id: string) {
    if (id == "1") {
      return "Cédula";
    } else if (id == "2") {
      return "RUC";
    } else {
      return "Pasaporte";
    }
  }

  getNomTipUser(id: string) {
    if (id == "1") {
      return "Administrador";
    } else {
      return "Estándar";
    }
  }

  loadDataUser(item: any) {
    this.id_usuario = item.id_usuario;
    this.selectedTipDni = this.getNomTipDni(item.id_tip_dni);
    this.selectedEstab = item.id_establ_per
    this.num_ident = item.num_ident
    this.nomb_usuario = item.nomb_usuario;
    this.apell_usuario = item.apell_usuario;
    this.email_usuario = item.email_usuario;
    this.telef_usuario = item.telef_usuario;
    this.direc_usuario = item.direc_usuario;
    this.selectedTipUser = this.getNomTipUser(item.tip_usuario);
    this.contrasenia = item.contrasenia;
  }

  getUserForIdEstab(id_estab: string) {
    this.listFilterUserEstab = this.items.filter(user => user.id_establ_per === id_estab);
    this.listWithOutFilter = this.listFilterUserEstab;

  }

  handleBtnFormVend() {
    if (this.nameBtnModalForm === 'Registrar') {
      this.insertUser();
    } else {
      this.updateUser(this.id_usuario);
    }
  }

  handleBtnGtnProm() {
    if (this.btnNameGtnEstab === 'Registrar') {
      this.insertEstab();
    } else {
      this.updateEstab(this.selectedOptEstabGtn);
    }
  }

  actionDeleteEstab() {
    this.presentAlert(() => {
      this.deleteEstab(this.selectedOptEstabGtn);
    }, "el establecimiento")
  }

  editItem(item: any) {
    this.loadDataUser(item);
    this.setTitleFormProd();
  }

  deleteItem(item: any) {
    this.presentAlert(() => {
      this.deleteUser(item);
    }, "el usuario")
  }
}
