import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../class/user';
import { UserRegisterService } from '../../services/user-register.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export class UserRegisterComponent {
  user:User= new User();

  constructor(private userRegisterService :  UserRegisterService, private router:Router){}
  userRegister(){

  
  this.userRegisterService.userRegisterService(this.user).subscribe(data=>{
      alert("Registration Succesfull!");
      this.login();
    }, error => alert("Registration Fails!"));
  }

  login(){
    this.router.navigate(["/login"]);
  }

}
