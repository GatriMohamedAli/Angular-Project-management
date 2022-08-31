import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthentificationGuard } from '../guards/authentification.guard';
import { AdminComponent } from './admin.component';
import { AddNewRoleComponent } from './components/add-new-role/add-new-role.component';
import { AddRoleComponent } from './components/add-role/add-role.component';
import { AddTaskComponent } from './components/add-task/add-task.component';
import { ListProjectsComponent } from './components/list-projects/list-projects.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { NewProjectComponent } from './components/new-project/new-project.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RevokeRoleComponent } from './components/revoke-role/revoke-role.component';
import { SaveUserComponent } from './components/save-user/save-user.component';
import { ViewProjectTasksComponent } from './components/view-project-tasks/view-project-tasks.component';

const routes: Routes = [
  {path:"admin", component:AdminComponent, canActivate:[AuthentificationGuard], children:[
    {path:"users", component:ListUsersComponent},
    {path:"save", component:SaveUserComponent},
    {path:"role/grant", component:AddRoleComponent},
    {path:"role/new",component:AddNewRoleComponent},
    {path:"project",component:ListProjectsComponent},
    {path:"project/view", component:ViewProjectTasksComponent},
    {path:"project/new", component:NewProjectComponent},
    {path:"project/task", component:AddTaskComponent},
    {path:"profile", component:ProfileComponent},
    {path:"role/revoke", component:RevokeRoleComponent}
    
    
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
