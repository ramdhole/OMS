import { Component, OnInit } from '@angular/core';
import { OMSServiceService } from '../../services/omsservice.service';
import { Order } from '../../class/order';
import { Mobile } from '../../class/mobile';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})

export class OrderComponent implements OnInit {
  count: number = 0;
  orders: Order[] = [];
  user: User = new User()
  mobiles: Mobile[] = [];

  constructor(private service: OMSServiceService, private current: CurrentUserService) { }

  ngOnInit(): void {
    this.user = this.current.getUser();
    this.setOrder();
  }

  setMobiles(id: number) {
    this.service.getAllMobsInOrder(id).subscribe((data: any) => {
      this.mobiles = data;
      if(this.mobiles === null){
        alert("No Order to show !!");
      }
    });

  }

  setOrder() {
    this.service.getOrders(this.user.customerId).subscribe((data: any) => {
      this.orders = data;
        this.setMobiles(this.user.customerId);
    },
      (error) => {
        console.log(error);
      });
  }

}
