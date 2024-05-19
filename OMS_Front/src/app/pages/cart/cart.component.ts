import { Component, OnInit } from '@angular/core';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';
import { Cart } from '../../class/cart';
import { OMSServiceService } from '../../services/omsservice.service';
import { Mobile } from '../../class/mobile';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})

export class CartComponent implements OnInit {

  delete(mobId: number) {
    let response = this.service.deleteMobFromCart(mobId, this.cart.cartId);
    response.subscribe((data: any) => {
      if (data !== null) {
        alert("Mobile deleted from cart !!")
        this.getAllMobile();
      }
    });
  }

  mobiles: Mobile[] = [];
  user: User = new User();

  cart: Cart = new Cart();

  constructor(private currentUser: CurrentUserService, private service: OMSServiceService, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.user = this.currentUser.getUser();
    let response = this.service.getCart(this.user.cartId);
    response.subscribe((data: Cart) => {
      this.cart = data;
    });
    this.getAllMobile();
  }

  placeOrder() {
    this.router.navigate(['/cartToOrder', this.cart.cartId]);
    
    this.cart = new Cart();
  }

  getAllMobile() {
    let response = this.http.get("http://localhost:1000/cart/getAllMobiles/" + this.user.customerId);
    response.subscribe((data: any) => {
      this.mobiles = data;
    },
      (error) => {
        alert("No Mobile added yet !!");
        this.router.navigate(['/landing']);
        // console.log(error);
      });
  }

}
