import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-new-project',
  templateUrl: './new-project.component.html',
  styleUrls: ['./new-project.component.css']
})
export class NewProjectComponent implements OnInit {

  constructor(private adminService:AdminService, private router:Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }
  newProjectForm=this.formBuilder.group({
    title:"",
    description:""
  })

  Submitted(){
    const {title, description}= this.newProjectForm.value
    this.adminService.addProject(title,description).subscribe({
      next: data => {
        console.log(data)
        this.router.navigate(["/admin/project"])
      },
      error: err => console.log(err)
    })
  }
}
