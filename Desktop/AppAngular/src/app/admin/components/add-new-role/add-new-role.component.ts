import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-new-role',
  templateUrl: './add-new-role.component.html',
  styleUrls: ['./add-new-role.component.css']
})
export class AddNewRoleComponent implements OnInit {

  constructor(private formbuilder:FormBuilder, private adminService:AdminService, private router:Router) { }

  ngOnInit(): void {
  }
  addNewRoleForm=this.formbuilder.group({
    Rolename: [],
  })
  Submitted(){
    const rolename="ROLE_"+this.addNewRoleForm.value.Rolename.toUpperCase()
    this.adminService.addNewRole(rolename).subscribe({
      next: data =>{
        console.log(data)
        this.router.navigate(["admin/users"])
      },
      error: err=>console.log(err)
    })
  }
}

