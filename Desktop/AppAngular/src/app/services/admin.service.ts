import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'any'
})

export class AdminService {
  AUTH_API="http://localhost:8085/api/v1/"
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*'})
  };
  constructor(private httpClient:HttpClient) { }

  getAllUsers(){
    return this.httpClient.get(this.AUTH_API+"users",this.httpOptions);
  }
  saveUser(username:string, name:string, password:string){
    return this.httpClient.post(this.AUTH_API+"user/save", {
      username,
      password,
      name,
      roles: []
    } , this.httpOptions)
  }
  getUsernames(){
    return this.httpClient.get(this.AUTH_API+"usernames", this.httpOptions)
  }
  getRoleNames(){
    return this.httpClient.get(this.AUTH_API+"roles",this.httpOptions)
  }

  grantRoleToUser(username:string, rolename:string){
    return this.httpClient.post(this.AUTH_API+"grant",{"username":username,"roleName":rolename},this.httpOptions)
  }

  addNewRole(roleName:string){
    return this.httpClient.post(this.AUTH_API+"role/save",{roleName},this.httpOptions)
  }

  getProjects(){
    return this.httpClient.get(this.AUTH_API+"project", this.httpOptions)
  }
  addProject(title:string,description:string){
    return this.httpClient.post(this.AUTH_API+"project",{title,description}, this.httpOptions)
  }
  

  addTaskToProject(title:string,description:string, projectIdSample:number, isDone:boolean, isAssigned:boolean){
    return this.httpClient.post(this.AUTH_API+"task", {title,description,projectIdSample,isDone,isAssigned}, this.httpOptions)
  }
  assignTaskToUser(taskId:number,username:string){
    return this.httpClient.post(this.AUTH_API+"task/assignto", {taskId,username}, this.httpOptions)
  }
  deleteProject(id:any){
    return this.httpClient.delete(this.AUTH_API+"project/"+id, this.httpOptions)
  }

  getProjectTasks(id:number){
    let queryParam= new HttpParams().set('projectId',id)
    return this.httpClient.get(this.AUTH_API+"task", {params:queryParam})
  }

  deleteTask(id:number){
    return this.httpClient.delete(this.AUTH_API+"task/"+id, this.httpOptions)
  }

  getUserRoles(username:string){
    let queryParam=new HttpParams().set("username",username)
    return this.httpClient.get(this.AUTH_API+"user/roles",{params:queryParam})
  }

  //TODO:
  revokeRole(username:string, roleName:string){
    return this.httpClient.post(this.AUTH_API+"role/revoke", {username,roleName}, this.httpOptions)
  }
}
