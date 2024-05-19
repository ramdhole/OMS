import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { User } from "../class/user";

@Injectable({
  providedIn: 'root'
})

export class UserRegisterService {
  private baseUrl = "http://localhost:1000/customer/register";

    constructor(private httpClient:HttpClient) { }
    
    userRegisterService(user : User):Observable<Object>{
      return this.httpClient.post(`${this.baseUrl}`, user);
    }
}
