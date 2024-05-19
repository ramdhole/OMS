import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OMSServiceService } from '../../services/omsservice.service';
import { CurrencyPipe } from '@angular/common';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';
import { Order } from '../../class/order';
import { Mobile } from '../../class/mobile';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit{
  
  ngOnInit(): void {
    this.setId();
    this.confirmedOrder();
    this.setMobile();
  }

  setMobile() {
    this.service.getMobileById(this.mobId).subscribe((data:any)=>{
      this.mobile = data;
    });

  }

  setId(){
    this.route.params.subscribe((data : any) =>{
      this.mobId = data['mobId'];
      this.user = this.current.getUser();
    });
  }

  confirmedOrder(){
    this.service.addMobToOrder(this.mobId, this.user.customerId).subscribe((data:any)=>{
      this.order = data;
    });
  }

  constructor(private route : ActivatedRoute, private router : Router, private service : OMSServiceService, private current : CurrentUserService){}

  mobId !: number;

  user : User = new User();
  mobile : Mobile = new Mobile();
  order : Order = new Order();
}
