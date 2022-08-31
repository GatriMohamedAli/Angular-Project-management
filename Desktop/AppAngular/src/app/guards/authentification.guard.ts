import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHandleService } from '../services/jwt-handle.service';

@Injectable({
  providedIn: 'any'
})
export class AuthentificationGuard implements CanActivate {
  constructor(private jwtHandler:JwtHandleService, private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let user=this.jwtHandler.getUser();
    if (user.roles.find((role: string)=>role=="ROLE_ADMIN")){
      return true;
    }else{
      return false;
    }
  }
  
}
