import { Component, OnInit } from '@angular/core';
import { Order } from '../../../class/order';
import { OMSServiceService } from '../../../services/omsservice.service';
import { User } from '../../../class/user';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrl: './all-orders.component.css'
})
export class AllOrdersComponent implements OnInit{

  orders : Order[] =[];

  constructor(private service : OMSServiceService ){}

  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders() {
    let response = this.service.getAllOrders();
    response.subscribe((data: any) => {
      this.orders = data;
    },
    (error) => {
      console.log(error);
    });
  }

  user : User = new User();
  getCustomer(id : number): User{
    this.service.getUserById(id).subscribe((data : any)=>{
      this.user = data;
    });

    return this.user;
  }
}
