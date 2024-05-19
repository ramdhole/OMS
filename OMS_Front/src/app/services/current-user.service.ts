import { Injectable } from "@angular/core";
import { User } from "../class/user";
import { UserProfileService } from "./user-profile.service";

@Injectable({
    providedIn: 'root'
  })
export class CurrentUserService {

    constructor(private userService : UserProfileService){}

    currentUser : User = new User();

    // Method to store the user object in localStorage
    setUser(email : string){
        let response = this.userService.getUserByEmail(email);
        response.subscribe((data:any)=>{
            localStorage.setItem('currentUser', JSON.stringify(data));
        });
    }

    // Method to retrieve the user object from localStorage
    getUser(){
        const userString = localStorage.getItem('currentUser');
        if (userString) {
          return JSON.parse(userString);
        }
        return null;
    }

  // Method to clear the user object from localStorage
  clearLocalUser(): void {
    localStorage.removeItem('currentUser');
  }
}

