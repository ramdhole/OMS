import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OMSServiceService } from '../../../services/omsservice.service';
import { Mobile } from '../../../class/mobile';
import { Category } from '../../../class/category';

@Component({
  selector: 'app-add-mobile',
  templateUrl: './add-mobile.component.html',
  styleUrl: './add-mobile.component.css'
})
export class AddMobileComponent implements OnInit {
  ngOnInit(): void {
  }

  categoryObjectList: any;
  mobile: Mobile = new Mobile();
  RamValue = [2, 4, 8, 16];
  constructor(private service: OMSServiceService) { }
  category: Category = new Category();


  onAddMobile() {
    this.mobile.category = this.category;
    this.service.addMobile(this.mobile).subscribe(res => {
      alert('Mobile successfully added!');
    }, (error) => {
      alert('Error occured while adding mobile');
    })
  }

}
