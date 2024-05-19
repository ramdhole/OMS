import { Component } from '@angular/core';
import { OMSServiceService } from '../../../services/omsservice.service';
import { Router } from '@angular/router';
import { AppComponent } from '../../../app.component';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent {
password!: string;
login() {
  if(this.service.adminLogin(this.userName, this.password) == true){
    AppComponent.isAdminLoggedIn = true;
    this.router.navigate(["/admin/dash"]);
  }

    else
    this.router.navigate(["admin/login"]);
  
}
userName!: string;

constructor(private service : OMSServiceService, private router : Router){}

}
