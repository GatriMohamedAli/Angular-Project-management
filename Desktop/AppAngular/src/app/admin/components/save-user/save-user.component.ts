import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-save-user',
  templateUrl: './save-user.component.html',
  styleUrls: ['./save-user.component.css']
})
export class SaveUserComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private adminService: AdminService) { }

  ngOnInit(): void {
  }

  saveUserForm = this.formBuilder.group({
    Username: [""],
    Name: [""]
  })
  Submitted(){
    console.log("submitted");
    console.log(this.saveUserForm.value.Username)
    const {Username, name} = this.saveUserForm.value
    this.adminService.saveUser(Username,name,"dalidali").subscribe({
      next: data =>{
        console.log(data)
      },
      error: err=>console.log(err)
    })
  }
}
