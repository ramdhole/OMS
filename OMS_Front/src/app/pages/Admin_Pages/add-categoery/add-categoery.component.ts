import { Component } from '@angular/core';
import { OMSServiceService } from '../../../services/omsservice.service';
import { Category } from '../../../class/category';

@Component({
  selector: 'app-add-categoery',
  templateUrl: './add-categoery.component.html',
  styleUrl: './add-categoery.component.css'
})
export class AddCategoeryComponent {

  categ : Category = new Category();
  constructor(private service : OMSServiceService){}

  addCategoery(){
    this.service.addCategory(this.categ);
  }
}
