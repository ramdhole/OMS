import { Component, OnInit } from '@angular/core';
import { User } from '../../../class/user';
import { HttpClient } from '@angular/common/http';
import { OMSServiceService } from '../../../services/omsservice.service';

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrl: './all-customers.component.css'
})
export class AllCustomersComponent implements OnInit{

  customers : User[] = [];

  constructor(private service : OMSServiceService ){}

  ngOnInit(): void {
    this.getAllCust();
  }

  getAllCust() {
    let response = this.service.getAllCust();
    response.subscribe((data: any) => {
      this.customers = data;
    },
    (error) => {
      console.log(error);
    });
  }

}
