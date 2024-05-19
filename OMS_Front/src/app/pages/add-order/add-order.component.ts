import { Component, OnInit } from '@angular/core';
import { OMSServiceService } from '../../services/omsservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../class/user';
import { CurrentUserService } from '../../services/current-user.service';
import { Mobile } from '../../class/mobile';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrl: './add-order.component.css'
})
export class AddOrderComponent implements OnInit {
  
  seeDetails(id: any) {
    this.router.navigate(['/mobDetails', id]);
  }

  user: User = this.currentUser.getUser();
  mobile: Mobile = new Mobile();
  mobId!: number;

  constructor(private service: OMSServiceService, private route: ActivatedRoute, private router: Router, private currentUser: CurrentUserService) { }
  ngOnInit(): void {
    this.setId();
    this.setMobile();
  }

  setMobile() {
    console.log(this.mobId);
    this.service.getMobileById(this.mobId).subscribe((data: any) => {
      this.mobile = data;
    });

  }

  placeOrder() {
    this.router.navigate(['/placeOrder', this.mobId]);
  }

  setId() {
    this.route.params.subscribe((data: any) => {
      this.mobId = data['mobId'];
    });
  }
}
