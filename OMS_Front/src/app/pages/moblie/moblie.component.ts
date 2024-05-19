import { Component, OnInit } from '@angular/core';
import { Mobile } from '../../class/mobile';
import { ActivatedRoute, Router } from '@angular/router';
import { OMSServiceService } from '../../services/omsservice.service';

@Component({
  selector: 'app-moblie',
  templateUrl: './moblie.component.html',
  styleUrl: './moblie.component.css'
})
export class MoblieComponent implements OnInit{
  mobId !: number;
  constructor(private router : Router, private route : ActivatedRoute, private service : OMSServiceService){}

  ngOnInit(){
    this.setId();
    this.setMobile(this.mobId);
  }

  setId(){
    this.route.params.subscribe(params => {
      this.mobId = params['mobId']; 
    });
  }

  setMobile(id : number){
    this.service.getMobileById(id).subscribe((data : any)=>{
      this.mobile = data;
    });
  }

  mobile : Mobile = new Mobile();

}
