import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OMSServiceService } from '../../services/omsservice.service';
import { Cart } from '../../class/cart';
import { Order } from '../../class/order';
import { Mobile } from '../../class/mobile';
import { User } from '../../class/user';
import { CurrentUserService } from '../../services/current-user.service';

@Component({
  selector: 'app-cart-to-order',
  templateUrl: './cart-to-order.component.html',
  styleUrl: './cart-to-order.component.css'
})
export class CartToOrderComponent implements OnInit {
  customer: User = this.current.getUser();

  seeDetails(id: number) {
    this.router.navigate(['/mobDetails', id]);
  }

  setMobiles() {
    console.log("Set Mobile", this.order.orderId);
    this.service.getAllMobsInOrderfCart(this.customer.customerId).subscribe((data: any) => {
      this.mobiles = data;
      this.service.clearCart(this.cartId).subscribe();
    });

  }

  ngOnInit(): void {
    this.setCarId();
  }

  setOrder() {
    this.service.placeOrderFromCart(this.cartId).subscribe((data: any) => {
      this.order = data;

      this.setMobiles();
    });
  }

  order !: Order;
  cartId !: number;

  constructor(private route: ActivatedRoute, private router: Router, private service: OMSServiceService, private current : CurrentUserService) { }

  setCarId() {
    this.route.params.subscribe(params => {
      this.cartId = params['cartId'];
      this.setOrder();
    });
  }

  mobiles: Mobile[] = [];
}


