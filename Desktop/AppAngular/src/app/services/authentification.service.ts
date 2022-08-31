import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'any'
})
export class AuthentificationService {

  AUTH_API="http://localhost:8085/api/v1/"

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*', })
  };
  constructor(private httpClient:HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.httpClient.post(this.AUTH_API + 'login', {
      username,
      password
    }, this.httpOptions);
  }

  register(username:string,password:string,name:string):Observable<any> {
    return this.httpClient.post(this.AUTH_API+'signup',{
      username,
      password,
      name
    }, this.httpOptions)
  }
  
}
