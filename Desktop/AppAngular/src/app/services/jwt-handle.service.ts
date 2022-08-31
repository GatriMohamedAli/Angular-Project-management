import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";


@Injectable({
  providedIn: 'any'
})
export class JwtHandleService {

  constructor() { }

  storeToken(token:string){
    localStorage.removeItem("token");
    localStorage.setItem("token", token);
  }
  getToken() :string|null {
    return localStorage.getItem("token");
  }
  saveUser(){
    let token=this.getToken();
    if (token != null){
      let decode:any=jwt_decode(token);
      let user={
        username:"",
        Id:"",
        roles:[""]
      }
      user.roles=decode.roles;
      user.username=decode.sub;
      user.Id=decode.Id
      localStorage.setItem("user",JSON.stringify(user));
    }
  }
  getUser(){
    let retrivedUser=localStorage.getItem("user")
    return JSON.parse(retrivedUser||"");
  }
  deleteToken(){ localStorage.removeItem("token")}
  deleteUser(){ localStorage.removeItem("user")}
}
