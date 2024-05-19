import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from '../class/cart';
import { Order } from '../class/order';
import { User } from '../class/user';
import { CurrentUserService } from './current-user.service';
import { Mobile } from '../class/mobile';
import { Category } from '../class/category';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class OMSServiceService {

  getAllMobsInOrderfCart(custId: number) {
    return this.httpClient.get(`http://localhost:1000/cart/getAllMobiles/${custId}`);
  }

  clearCart(cartId: number) {
    return this.httpClient.delete(`http://localhost:1000/cart/deleteCart/${cartId}`);
  }
  
  getAllMobsInOrder(id : number) {
    return this.httpClient.get<Cart>(`http://localhost:1000/order/getAllMobile/order/custId/${id}`);
  }

  getCartById(cartId: number) {
    return this.httpClient.get<Cart>(`http://localhost:1000/cart/getCartById/${cartId}`);
  }

  getUserById(id:number){
    return this.httpClient.get<Mobile>(`http://localhost:1000/customer/getById/${id}`);
  }

  getMobileById(id: number) {
    return this.httpClient.get<Mobile>(`http://localhost:1000/mobile/get/${id}`);
  }

  getAllOrders() {
    return this.httpClient.get(`http://localhost:1000/order/getAll`);
  }

  getAllCust() {
    return this.httpClient.get(`http://localhost:1000/customer/getAll`);
  }

  deleteMobile(id: number) {
    return this.httpClient.delete<Mobile>(`http://localhost:1000/mobile/delete/${id}`);
  }

  addCategory(categ: Category) {
    return this.httpClient.post<Category>(`http://localhost:1000/category/add`, categ);
  }

  addMobile(mob: Mobile) {
    return this.httpClient.post<Mobile>(`http://localhost:1000/mobile/add`, mob);
  }

  adminLogOut() {
    throw new Error('Method not implemented.');
  }

  getMobByText(searchText: string) {
    return this.httpClient.get<Mobile[]>(`http://localhost:1000/mobile/searchMobile/${searchText}`)
        .pipe(
            catchError((error) => {
                return throwError(error);
            })
        );
  }

  placeOrderFromCart(cartId: number) {
    return this.httpClient.get<Order>(`http://localhost:1000/order/placedOrderFromCart/${cartId}`);
  }

  addMobToCart(mobId: number,  userId : number) {
    return this.httpClient.get<Cart>(`http://localhost:1000/cart/addMobile/${userId}/${mobId}`);
  }

  deleteMobFromCart(mobId: number, cartId: number) {
    return this.httpClient.delete<Cart>(`http://localhost:1000/cart/removeMobile/${mobId}/${cartId}`);
  }

  addMobToOrder(mobId: number, userId:number) {
    return this.httpClient.get<Order>(`http://localhost:1000/order/addOrder/${userId}/${mobId}`);
  }

  user: User = new User();

  constructor(private httpClient: HttpClient, private currentUser: CurrentUserService, private router : Router) {
    this.user = this.currentUser.getUser();
  }

  setUser(){
    this.user = this.currentUser.getUser();
  }

  getCart(cartId: number) {
    return this.httpClient.get<Cart>(`http://localhost:1000/cart/getByCartId/${cartId}`);
  }

  getOrders(id : number) {
    return this.httpClient.get<Order>(`http://localhost:1000/order/getByCustomer/${id}`);
  }

  adminLogin(userName: string, password: string) {
    if (userName == 'admin@123' && password == '12345') {
      alert("Login Succesfull !!")
      return true;
      // this.httpClient.get(`http://localhost:1000/login/admin/data?userName=${userName}&password=${password}`);
    } else{

      alert("Login Failed !!")
      return false;
    }

  }
}
