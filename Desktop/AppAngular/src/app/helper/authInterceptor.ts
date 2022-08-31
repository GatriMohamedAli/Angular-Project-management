import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { JwtHandleService } from "../services/jwt-handle.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private jwtHandler:JwtHandleService){

    }
    header=new HttpHeaders()
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token=this.jwtHandler.getToken();
        let authRequest=req;
        if (token!=null){
            authRequest=authRequest.clone({headers: authRequest.headers.append("Authorization", `Bearer ${token}` )})
        }
        return next.handle(authRequest);
    }
}