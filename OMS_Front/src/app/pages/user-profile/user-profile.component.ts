import { Component, OnInit } from '@angular/core';
import { User } from '../../class/user';
import { UserProfileService } from '../../services/user-profile.service';
import { AppComponent } from '../../app.component';
import { CurrentUserService } from '../../services/current-user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent{

  user: User = new User();

  constructor(private userService: UserProfileService, private currentUser : CurrentUserService) { 
    this.user = this.currentUser.getUser();

  }

}
