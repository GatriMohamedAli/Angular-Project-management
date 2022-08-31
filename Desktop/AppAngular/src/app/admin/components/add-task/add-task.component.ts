import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  constructor(private adminService:AdminService, private router:Router, private formBuilder:FormBuilder) { }
  listProjects:any 
  listUsers:any=[] 
  idTaskAdded=-1;
  ngOnInit(): void {
    this.adminService.getProjects().subscribe({
      next: data=>this.listProjects=data,
      error: err=>console.log(err)
    })
    this.adminService.getAllUsers().subscribe({
      next: data=>{
        const arrayListUSer=Object.values(data)
       arrayListUSer.forEach((line)=>{
        const arrayRole=Object.values(line.roles)
        console.log(arrayRole)
        arrayRole.forEach((role:any)=> {
          console.log(role.roleName)
          if (role.roleName=="ROLE_USER"){
            this.listUsers=[...this.listUsers,line];
          }
        })
        
       })
        console.log(arrayListUSer)
        console.log(Object.values(this.listUsers))
        

      },
      error: err=>console.log(err)
    })
  }
  addTaskForm=this.formBuilder.group({
    title:"",
    description:"",
    projectIdSample:-1,
    isDone: false,
    isAssigned: false,
    takenBy:false
  })
  //TODO: redirect to the project tasks view, need implementation.
  Submitted(){
    if(this.addTaskForm.value.takenBy == false){
     this.newTaskRequest().then(()=>this.router.navigate(['admin/project/view'], {queryParams:{id:this.addTaskForm.value.projectIdSample}}));
    }
   else{
      this.newTaskRequest()
      setTimeout(() => {
        this.adminService.assignTaskToUser(this.idTaskAdded, this.addTaskForm.value.takenBy).subscribe({
          next: data=>this.router.navigate(["admin/project/view"], {queryParams:{id:this.addTaskForm.value.projectIdSample}}),
          error: err=>console.log(err)
        })
      }, 1000);
      
      

    }
  }

  async newTaskRequest(){
    const {title, description,projectIdSample,isDone,isAssigned,takenBy} = this.addTaskForm.value
    console.log(isDone)
    const isDoneBool=(isDone === "true")
    console.log(isDoneBool)
    const isAssignedBool=(takenBy !== false)
    this.adminService.addTaskToProject(title, description, projectIdSample * 1, isDoneBool, isAssignedBool).subscribe({
      next: data => {
        const arrayData = Object.values(data);
        this.idTaskAdded=arrayData[0]
      },
      error: err => console.log(err)
    })
      
    
  }
}
