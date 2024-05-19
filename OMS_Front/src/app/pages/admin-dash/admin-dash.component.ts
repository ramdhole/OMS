import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OMSServiceService } from '../../services/omsservice.service';

@Component({
  selector: 'app-admin-dash',
  templateUrl: './admin-dash.component.html',
  styleUrl: './admin-dash.component.css'
})
export class AdminDashComponent {
  dashBoard() {
    this.router.navigate(['/admin/dash']);
  }

  viewOrders() {
    this.router.navigate(['/allOrder']);
  }

  constructor(private router: Router, private service: OMSServiceService) { }

  veiwCusts() {
    this.router.navigate(['/allCust']);
  }

  addCategory() {
    this.router.navigate(['/addCateg']);
  }
  addMob() {
    this.router.navigate(['/addMob']);
  }
  veiwMobs() {
    this.router.navigate(['/allMobs']);
  }
  onLogout() {
    // this.service.adminLogOut();
    this.router.navigate(['/admin/login']);
  }

}
