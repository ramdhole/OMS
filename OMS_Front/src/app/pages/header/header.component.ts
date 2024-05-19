import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  user: User = this.currentUser.getUser();;
  searchText!: string;

  adminIn : boolean = AppComponent.isAdminLoggedIn;

  search() {
    this.router.navigate(['/search', this.searchText]);
  }

  ngOnInit(): void {
    this.landingPage();
  }

  constructor(private router: Router, private currentUser: CurrentUserService) { }

  landingPage() {
    this.router.navigate(['/landing']);
  }

  home() {
    this.router.navigate(['/']);
    this.landingPage();
  }

  login() {
    this.router.navigate(['/login']);
  }

  cart() {
    if (AppComponent.isLoggedIn) {
      this.router.navigate(['/cart']);
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }
  }

  order() {
    if (AppComponent.isLoggedIn) {
      this.router.navigate(['/order']);
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }
  }

  userProfile() {
    this.router.navigate(['/userProfile']);
  }

  logout() {
    this.currentUser.clearLocalUser();
    this.router.navigate(['/login']);
    AppComponent.isLoggedIn = false;
  }

  isLoggedIn() {
    return AppComponent.isLoggedIn;
  }
  
}

