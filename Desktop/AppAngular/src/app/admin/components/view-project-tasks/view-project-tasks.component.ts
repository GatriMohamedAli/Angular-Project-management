import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
interface objectLine{
  id:"",
  title:"",
  description:"",
  isDone:boolean,
  isAssigned:boolean,
  takenBy:[]
}

@Component({
  selector: 'app-view-project-tasks',
  templateUrl: './view-project-tasks.component.html',
  styleUrls: ['./view-project-tasks.component.css']
})
export class ViewProjectTasksComponent implements OnInit {

  constructor(private activatedRoute:ActivatedRoute, private adminService:AdminService, private formBuilder:FormBuilder) { }
  projectid=0;
  doneArray: any[]=[]
  assignedArray:any[]=[]
  toDoArray:any[]=[]
  listUsers:any[]=[]

  assignTaskForm=this.formBuilder.group({
    title:"",
    id:0,
    username:""
  })


  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(param=>{
      this.projectid=param['id']
      console.log(this.projectid)
      this.getTasks(this.projectid)
      this.getlistUserModal();
      
    })
  }
  /* DTO FIX ASAP */
  getTasks(id:number){
    this.adminService.getProjectTasks(this.projectid).subscribe({
      next: data=>{
        const resArray=Object.values(data)
        console.log("res 3 ",resArray[3])
        let object:[]=resArray[3]
        
        object.forEach((obj:objectLine) => {
          let takenArray: any[]=[]
          takenArray=[...takenArray,obj.takenBy]
          if((obj.isDone == true)){
            this.doneArray=[...this.doneArray, obj]
          }else if((obj.isAssigned==true)){
            this.assignedArray=[...this.assignedArray, obj]
          }else 
            { 
            this.toDoArray=[...this.toDoArray,obj]
          }          
        });
      },
      error: err=>console.log(err)
    })
  }
  deleteTask(id:number){
    this.adminService.deleteTask(id).subscribe({
      next: data=>console.log(data),
      error: err=>console.log(err)
    })
    window.location.reload();
  }


  getlistUserModal(){
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

  assign(){
    console.log(this.assignTaskForm.value.username);
    console.log(this.assignTaskForm.value.title);
    console.log(this.assignTaskForm.value.id);
    const {id, username}= this.assignTaskForm.value
    this.adminService.assignTaskToUser(id, username).subscribe({
      next:data=>window.location.reload(),
      error: err=>console.log(err)
    })
  }
  assignValues(id:number, title:string){
    this.assignTaskForm.controls['title'].setValue(title);
    this.assignTaskForm.controls['id'].setValue(id);
  }

  // get single project admin service
  //get single project spring
}
