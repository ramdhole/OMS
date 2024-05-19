import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../class/user';
import { UserLoginService } from '../../services/user-login.service';
import { AppComponent } from '../../app.component';
import { HttpClient } from '@angular/common/http';
import { CurrentUserService } from '../../services/current-user.service';
import { HeaderComponent } from '../header/header.component';
import { OMSServiceService } from '../../services/omsservice.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent implements OnInit{

  user:User= new User();
  
  
  constructor(private userLoginService :  UserLoginService,private router:Router, private http:HttpClient, private currentUser : CurrentUserService, private service : OMSServiceService){}

  ngOnInit(): void {
  }
  
  userLogin(){
    
    this.userLoginService.userLoginService(this.user).subscribe(data=>{
      alert("Login Successfull");
      AppComponent.isLoggedIn = true;

      this.currentUser.setUser(this.user.emailId);
      // this.service.setUser();
      this.home();

    }, error => alert("Wrong Credentilas"));
  }
  
// Method for calling through the HTML web page
  register(){
    this.router.navigate(['/register']);
  }
  
  home(){
    this.router.navigate(['/landing']);
  }

  adminLogin() {
    this.router.navigate(['/admin/login']);
  }
}
