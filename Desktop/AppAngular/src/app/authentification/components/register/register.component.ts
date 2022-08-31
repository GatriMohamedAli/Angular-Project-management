import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    username: null,
    password: null,
    email:null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = null;
  constructor(private authService:AuthentificationService, private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit():void{
    const {username,password,name} = this.form
    console.log(username)
    /* this.authService.register(username, password,name).subscribe(response=>{
      console.log("response : ",response)
      this.router.navigate(['login']);
    }
      ); */
      this.authService.register(username,password,name).subscribe({
        next:data => {
        console.log(data)
        this.router.navigate(['auth/login'])
      },
        error: err =>this.errorMessage=err.error
      })
  }
}
