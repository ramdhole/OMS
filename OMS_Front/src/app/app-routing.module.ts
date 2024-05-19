import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegisterComponent } from './pages/user-register/user-register.component';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { CartComponent } from './pages/cart/cart.component';
import { OrderComponent } from './pages/order/order.component';
import { MoblieComponent } from './pages/moblie/moblie.component';
import { AddOrderComponent } from './pages/add-order/add-order.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { AdminDashComponent } from './pages/admin-dash/admin-dash.component';
import { AllOrdersComponent } from './pages/Admin_Pages/all-orders/all-orders.component';
import { AllCustomersComponent } from './pages/Admin_Pages/all-customers/all-customers.component';
import { AddCategoeryComponent } from './pages/Admin_Pages/add-categoery/add-categoery.component';
import { AddMobileComponent } from './pages/Admin_Pages/add-mobile/add-mobile.component';
import { AllMobilesComponent } from './pages/Admin_Pages/all-mobiles/all-mobiles.component';
import { AdminLoginComponent } from './pages/Admin_Pages/admin-login/admin-login.component';
import { PlaceOrderComponent } from './pages/place-order/place-order.component';
import { CartToOrderComponent } from './pages/cart-to-order/cart-to-order.component';

const routes: Routes = [
  {path: 'login', component : UserLoginComponent},
  {path: 'register', component : UserRegisterComponent},
  {path: 'landing', component : LandingPageComponent},
  {path: 'userProfile', component : UserProfileComponent},
  {path: 'order', component : OrderComponent},
  {path: 'placeOrder/:mobId', component : PlaceOrderComponent},
  {path: 'addToOrder/:mobId', component : AddOrderComponent},
  {path: 'cartToOrder/:cartId', component : CartToOrderComponent},
  {path: 'mobDetails/:mobId', component : MoblieComponent},
  {path: 'admin/dash', component : AdminDashComponent},
  {path: 'allOrder', component : AllOrdersComponent},
  {path: 'allCust', component : AllCustomersComponent},
  {path: 'addCateg', component : AddCategoeryComponent},
  {path: 'addMob', component : AddMobileComponent},
  {path: 'allMobs', component : AllMobilesComponent},
  {path: 'admin/login', component : AdminLoginComponent},
  {path: 'search/:searchText', component : SearchPageComponent},
  {path: 'cart', component : CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
      MdbCarouselModule
      ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
