import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../class/user";

@Injectable({
    providedIn: 'root'
})

export class UserProfileService {

    private baseUrl = "http://localhost:1000/customer/getByEmail/";
    
    constructor(private httpClient:HttpClient) { }
    
    getUserByEmail(email : string):Observable<Object>{
        
        return this.httpClient.get<User>(`${this.baseUrl}${email}` );
    }
}
