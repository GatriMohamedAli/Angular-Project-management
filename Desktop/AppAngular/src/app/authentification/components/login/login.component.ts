import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { JwtHandleService } from 'src/app/services/jwt-handle.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private authService:AuthentificationService, private router:Router, private jwtHandler:JwtHandleService) { }

  ngOnInit(): void {
  }

  onSubmit():void{
    const {username,password} = this.form
    console.log(username)
    this.authService.login(username, password).subscribe(response=>{
      console.log(response.Token)
      localStorage.removeItem("token");
      this.jwtHandler.storeToken(response.Token);
      this.jwtHandler.saveUser();
      this.router.navigate(['admin']);
    }
      );
  }

}
