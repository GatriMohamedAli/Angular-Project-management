import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  listUser:any=[]

  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    this.adminService.getAllUsers().subscribe({
      next: data => {
        this.listUser=data
        console.log(this.listUser)
      },
      error: err => console.log(err)
    })
    
  }

}
