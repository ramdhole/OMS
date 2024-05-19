import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  constructor(private router : Router){

  }

  static isLoggedIn :  boolean = localStorage.getItem('currentUser') !== null;
  static isAdminLoggedIn :  boolean = false;
  
  isAdmin(){
    return AppComponent.isAdminLoggedIn;
  }

  title = 'OMS_Login';
}
