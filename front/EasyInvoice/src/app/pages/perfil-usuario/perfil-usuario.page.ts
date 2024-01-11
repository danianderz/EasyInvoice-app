import { Component, OnInit } from '@angular/core';
import { DataSharingService } from '../../services/data-sharing.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ModalController, AlertController, NavController } from '@ionic/angular';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.page.html',
  styleUrls: ['./perfil-usuario.page.scss'],
})
export class PerfilUsuarioPage implements OnInit {

  private receivedData: any;
  num_ident!: string;
  names!: string;
  phone!: string;
  email!: string;
  direccion!: string;
  tip_usuario!: any;
  estab_per!: string;

  passwordCurrent!: string;
  passwordNew!: string;
  confPassword!: string;

  showBackdrop: boolean = false;

  wrongPasswCurrent = new FormControl('', Validators.required);
  isEmptyInputText = new FormControl('', Validators.required);
  wrongPassword = new FormControl('', Validators.required);

  constructor(private dataSharingService: DataSharingService,
    private http: HttpClient, private modalController: ModalController,
    private alertController: AlertController,
    private navCtrl: NavController) { 

    }

  ngOnInit() {
    this.initDataUser();
  }

  initDataUser() {
    this.receivedData = this.dataSharingService.getJsonData();
    this.getEstabForId(this.receivedData.id_establ_per)
    this.num_ident = this.receivedData.num_ident;
    this.names = this.receivedData.nomb_usuario + " " + this.receivedData.apell_usuario;
    this.phone = this.receivedData.telef_usuario;
    this.email = this.receivedData.email_usuario;
    this.direccion = this.receivedData.direc_usuario;
    this.tip_usuario = this.receivedData.tip_usuario;
  }

  getEstabForId(id_estab: string) {
    const url = 'http://localhost:8080/establishment/' + id_estab;
    this.http.get<any[]>(url).subscribe(
      (response: any) => {
        if (response !== null) {
          this.estab_per = response.nombre;
        }
      },
      (error) => {
        console.error('Error al recuperar promoción:', error);
      }
    );
  }

  isEmptyInput(
    passwordCurrent: string,
    passwordNew: string,
    confpassword: string
  ) {
    if (!passwordCurrent || !passwordNew || !confpassword ||
      !this.isEmptyInputText.invalid ||
      /^\s+|\s+$/g.test(passwordCurrent) ||
      /^\s+|\s+$/g.test(passwordNew) ||
      /^\s+|\s+$/g.test(confpassword)) {

      this.clearMsjDanger();

      this.isEmptyInputText.markAsTouched();
      return false;
    } else {
      return true;
    }
  }

  changePassword() {
    const passwordCurrent = this.passwordCurrent;
    const passwordNew = this.passwordNew;
    const confPassword = this.confPassword;

    if (this.isEmptyInput(passwordCurrent, passwordNew, confPassword)) {
      if (passwordCurrent === this.receivedData.contrasenia) {
        if (passwordNew === confPassword) {
          const dataUser = JSON.stringify({
            id_tip_dni: this.receivedData.id_tip_dni,
            id_establ_per: this.receivedData.id_establ_per,
            num_ident: this.receivedData.num_ident,
            nomb_usuario: this.receivedData.nomb_usuario,
            apell_usuario: this.receivedData.apell_usuario,
            email_usuario: this.receivedData.email_usuario,
            telef_usuario: this.receivedData.telef_usuario,
            direc_usuario: this.receivedData.direc_usuario,
            estado_usuario: this.receivedData.estado_usuario,
            tip_usuario: this.receivedData.tip_usuario,
            contrasenia: passwordNew,
          });

          this.consumeServiceChangePass(dataUser);

        } else {

          this.clearMsjDanger();

          this.wrongPassword.markAsTouched();
          this.clearInput();
        }
      } else {

        this.clearMsjDanger();
        this.wrongPasswCurrent.markAsTouched();
        this.clearInput();
      }
    }
  }

  consumeServiceChangePass(dataUser: string) {
    const url = 'http://localhost:8080/user/modif-user/' + this.receivedData.id_usuario;
    const headers = {
      'Content-Type': 'application/json',
    };

    this.http.put(url, dataUser, { headers, observe: 'response' }).subscribe(
      (response: HttpResponse<any>) => {
        if (response && response.status == 200 && response.body) {
          try {
            this.closeModal();
            this.presentAlert();
            this.clearInput();
            this.clearMsjDanger();
          } catch (error) {
            console.error('Error al procesar la respuesta:', error);
          }
        } else {
          console.error('Respuesta HTTP nula o vacía recibida');
        }
      },
      (error) => {
        console.error(error);
      }
    );
  }

  async closeModal() {
    await this.modalController.dismiss({
      dismissed: true
    });
  }

  async presentAlert() {
    const alert = await this.alertController.create({
      header: '¿Desea cerrar sesión?',
      buttons: [
        {
          text: 'No',
          cssClass: 'alert-button-cancel',
        },
        {
          text: 'Sí',
          cssClass: 'alert-button-confirm',
          handler: () => {
            // Acción a realizar cuando se selecciona "Sí"
            this.cerrarSesion();
          }
        },
      ]
    });

    await alert.present();
  }

  cerrarSesion() {
    this.navCtrl.navigateForward('/login');
  }

  clearInput() {
    this.passwordCurrent = "";
    this.passwordNew = "";
    this.confPassword = "";
  }

  clearMsjDanger() {
    this.wrongPasswCurrent.markAsUntouched();
    this.isEmptyInputText.markAsUntouched();
    this.wrongPassword.markAsUntouched();
  }


  editNames() { }

  editUsername() { }

  editPhone() { }

  editEmail() { }

}
