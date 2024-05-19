import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {
  private baseUrl = "http://localhost:1000/login/signIn";

  constructor(private httpClient:HttpClient) { }
    
  userLoginService(user:User):Observable<Object>{

    return this.httpClient.post(`${this.baseUrl}`, user);
  }
}
