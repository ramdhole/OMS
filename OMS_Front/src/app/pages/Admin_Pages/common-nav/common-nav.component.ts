import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OMSServiceService } from '../../../services/omsservice.service';
import { AppComponent } from '../../../app.component';

@Component({
  selector: 'app-common-nav',
  templateUrl: './common-nav.component.html',
  styleUrl: './common-nav.component.css'
})
export class CommonNavComponent {
  dashBoard() {
    this.router.navigate(['/admin/dash']);
  }

  onLogout() {
    // this.service.adminLogOut();
    this.router.navigate(['/landing']);
    AppComponent.isAdminLoggedIn = false;

  }

  constructor(private router : Router, private service : OMSServiceService){}
}
