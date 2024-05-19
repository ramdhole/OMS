import { Component, OnInit } from '@angular/core';
import { Mobile } from '../../../class/mobile';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { OMSServiceService } from '../../../services/omsservice.service';

@Component({
  selector: 'app-all-mobiles',
  templateUrl: './all-mobiles.component.html',
  styleUrl: './all-mobiles.component.css'
})
export class AllMobilesComponent implements OnInit{
  
  delete(id: number) {
    this.service.deleteMobile(id).subscribe((data:any)=>{
      alert("Mobile Deleted !!")
    },(error)=>
    alert("Failed to Delete Mobile !!"));
    this.getAllMobiles();
  }

  mobiles: Mobile[] = [];
  constructor(private http: HttpClient, private router: Router, private service: OMSServiceService) { }


  ngOnInit() {
    this.getAllMobiles();
  }
  
  getAllMobiles(){
    let response = this.http.get("http://localhost:1000/mobile/getAll");
    response.subscribe((data: any) => {
      this.mobiles = data;
    },
    (error) => {
      console.log(error);
    });
  }
  
}