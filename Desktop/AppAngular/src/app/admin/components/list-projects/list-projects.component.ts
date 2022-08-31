import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-list-projects',
  templateUrl: './list-projects.component.html',
  styleUrls: ['./list-projects.component.css']
})
export class ListProjectsComponent implements OnInit {

  constructor(private adminService:AdminService, private router:Router) { }
  listProject:any
  ngOnInit(): void {
    this.adminService.getProjects().subscribe({
      next: data => {
        this.listProject=data
        console.log(this.listProject)
      },
      error: err => console.log(err)
    })
  }

  deleteProject(id:any){
    this.adminService.deleteProject(id).subscribe({
      next: data=>{
        console.log(data)
        window.location.reload()
      },
      error: err => console.log(err)
    })
    
  }
  

}
