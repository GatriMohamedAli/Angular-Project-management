import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdminService } from 'src/app/services/admin.service';



@Component({
  selector: 'app-revoke-role',
  templateUrl: './revoke-role.component.html',
  styleUrls: ['./revoke-role.component.css']
})
export class RevokeRoleComponent implements OnInit {

  constructor(private adminService:AdminService, private formBuilder:FormBuilder) { }

  revokeForm=this.formBuilder.group({
    username:"Pick a user",
    roleName:""
  })

  listUser:any=[]
  listRoles:any=[]

  ngOnInit(): void {
    this.adminService.getAllUsers().subscribe({
      next: data => {
        this.listUser=data
        console.log(this.listUser)
      },
      error: err => console.log(err)
    })

    this.OnChanges()
  }
  revoke(){
    const {username,roleName} = this.revokeForm.value;
    this.adminService.revokeRole(username,roleName).subscribe({
      next: data=>{
        console.log(data)
        window.location.reload()
      },
      error: err => console.log(err)
    })
  
  }
  OnChanges(){
    this.revokeForm.get("username")?.valueChanges.subscribe((selected)=>
    this.adminService.getUserRoles(selected).subscribe({
      next: data=>{
        this.listRoles=data
        console.log(this.listRoles)
      },
      error:err=>console.log(err)
    })
    )
    
  }

}
