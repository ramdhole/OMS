import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserRegisterComponent } from './pages/user-register/user-register.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { HeaderComponent } from './pages/header/header.component';
import { CartComponent } from './pages/cart/cart.component';
import { OrderComponent } from './pages/order/order.component';
import { MoblieComponent } from './pages/moblie/moblie.component';
import { AddOrderComponent } from './pages/add-order/add-order.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { AdminDashComponent } from './pages/admin-dash/admin-dash.component';
import { AllOrdersComponent } from './pages/Admin_Pages/all-orders/all-orders.component';
import { AllCustomersComponent } from './pages/Admin_Pages/all-customers/all-customers.component';
import { AllMobilesComponent } from './pages/Admin_Pages/all-mobiles/all-mobiles.component';
import { AddCategoeryComponent } from './pages/Admin_Pages/add-categoery/add-categoery.component';
import { AddMobileComponent } from './pages/Admin_Pages/add-mobile/add-mobile.component';
import { CommonNavComponent } from './pages/Admin_Pages/common-nav/common-nav.component';
import { AdminLoginComponent } from './pages/Admin_Pages/admin-login/admin-login.component';
import { PlaceOrderComponent } from './pages/place-order/place-order.component';
import { CartToOrderComponent } from './pages/cart-to-order/cart-to-order.component';
import { OrderFCartComponent } from './pages/order-fcart/order-fcart.component';


@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserRegisterComponent,
    UserProfileComponent,
    LandingPageComponent,
    HeaderComponent,
    CartComponent,
    OrderComponent,
    MoblieComponent,
    AddOrderComponent,
    SearchPageComponent,
    AdminDashComponent,
    AllOrdersComponent,
    AllCustomersComponent,
    AllMobilesComponent,
    AddCategoeryComponent,
    AddMobileComponent,
    CommonNavComponent,
    AdminLoginComponent,
    PlaceOrderComponent,
    CartToOrderComponent,
    OrderFCartComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MdbCarouselModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
