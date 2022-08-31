import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {

  constructor(private formBuilder:FormBuilder, private adminService: AdminService, private router:Router) { }
  roleNames:any=[]
  userNames:any=[]

  ngOnInit(): void {
    console.log(this.adminService.getUsernames().subscribe({
      next: data =>{
        console.log(data)
        this.userNames=data;
      },
      error: err=>console.log(err)
    }));
    console.log(this.adminService.getRoleNames().subscribe({
      next: data =>{
        console.log(data)
        this.roleNames=data
      },
      error: err=>console.log(err)
    }));
  }

  addRoleForm = this.formBuilder.group({
    Role: [],
    UserName: [],
  })
  Submitted(){
    console.log(this.addRoleForm.value.Role)
  }
  grant(){
    const {UserName, Role} = this.addRoleForm.value
    console.log(UserName);
     this.adminService.grantRoleToUser(UserName,Role).subscribe({
      next: data =>{
        console.log(data)
        this.router.navigate(["admin/users"])
      },
      error: err=>console.log(err)
    }) 
    
  }
}
 