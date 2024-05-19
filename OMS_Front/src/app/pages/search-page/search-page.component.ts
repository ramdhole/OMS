import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mobile } from '../../class/mobile';
import { OMSServiceService } from '../../services/omsservice.service';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../class/user';
import { EMPTY, catchError, throwError } from 'rxjs';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrl: './search-page.component.css'
})
export class SearchPageComponent implements OnInit {
  mobDetails(mobId: number) {
    this.router.navigate(['mobDetails', mobId]);
  }

  addToOrder(mobId: number) {
    
    if (AppComponent.isLoggedIn) {
      this.router.navigate(['/addToOrder', mobId])
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }
  }

  user: User = new User();

  addToCart(mobId: number) {
    if (AppComponent.isLoggedIn) {
      this.service.addMobToCart(mobId, this.user.customerId).subscribe();
      this.router.navigate(['/cart']);
    } else {
      alert("Please Login !!")
      this.router.navigate(['/login']);
    }


    // let response = this.service.addMobToCart(mobId, this.user.customerId);
    // response.subscribe((data: any) => {
    //   if (data != null) {
    //     this.router.navigate(['/cart'])
    //   }
    // })
  }

  constructor(private route: ActivatedRoute, private service: OMSServiceService, private router: Router, private currentUser: CurrentUserService) { }

  ngOnInit(): void {
    this.user = this.currentUser.getUser();
    this.setText();
    this.setMobiles();
  }

  setText() {
    this.route.params.subscribe(params => {
      this.searchText = params['searchText'];
    });
  }

  setMobiles() {
    this.service.getMobByText(this.searchText)
        .pipe(
            catchError((error)=>{
              this.router.navigate(['/landing']);
              alert('No mobiles found !!');
                return EMPTY;
            })
        )
        .subscribe((data: any) => {
            if (data && data.length > 0) {
                this.mobiles = data;
            }
        });
}

  searchText !: string;
  mobiles: Mobile[] = [];

}
