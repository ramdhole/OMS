import { Component, OnInit } from '@angular/core';
import { Mobile } from '../../class/mobile';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { OMSServiceService } from '../../services/omsservice.service';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})

export class LandingPageComponent implements OnInit {
  private user: User = new User()

  addToOrder(mobId: number) {

    if (AppComponent.isLoggedIn) {
      this.router.navigate(['/addToOrder', mobId]);
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }
  }

  addToCart(mobId: number) {

    if (AppComponent.isLoggedIn) {
      let response = this.service.addMobToCart(mobId, this.user.customerId);
      response.subscribe((data: any) => {
        this.router.navigate(['/cart']);
      });
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }

  }

  seeDetails(mobId: number) {
    this.router.navigate(['/mobDetails', mobId]);
  }

  mobiles: Mobile[] = [];

  constructor(private http: HttpClient, private router: Router, private service: OMSServiceService, private current: CurrentUserService) { }


  ngOnInit() {
    this.user = this.current.getUser();

    let response = this.http.get("http://localhost:1000/mobile/getAll");
    response.subscribe((data: any) => {
      this.mobiles = data;
    },
      (error) => {
        console.log(error);
      });

  }


}
