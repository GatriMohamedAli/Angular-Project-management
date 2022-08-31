import { Component, OnInit } from '@angular/core';
import { JwtHandleService } from '../services/jwt-handle.service';
import jwt_decode from "jwt-decode";
import { DynamicScriptLoaderService } from '../services/dynamic-script-loader.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  username=""
  imageUrl="http://localhost:8085/api/v1/file/"
  constructor(private jwtHandler:JwtHandleService, private dynamicScriptLoader:DynamicScriptLoaderService, private router:Router) { }

  ngOnInit(): void {
    this.loadScripts();
    this.fillInfo();
  }
  private loadScripts() {
    // You can load multiple scripts by just providing the key as argument into load method of the service
    this.dynamicScriptLoader.load('bootstrapjs').then(data => {
      // Script Loaded Successfully
    }).catch(error => console.log(error));
  }

  private fillInfo(){
    console.log(this.jwtHandler.getUser())
    this.username=this.jwtHandler.getUser().username;
    this.imageUrl+=this.jwtHandler.getUser().Id;
  }

  logOut(){
    this.jwtHandler.deleteToken();
    this.jwtHandler.deleteUser();
    this.router.navigate(['/auth/login']);
  }

}
