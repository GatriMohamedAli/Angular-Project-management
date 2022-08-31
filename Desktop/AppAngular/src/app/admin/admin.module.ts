import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { SaveUserComponent } from './components/save-user/save-user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddRoleComponent } from './components/add-role/add-role.component';
import { AddNewRoleComponent } from './components/add-new-role/add-new-role.component';
import { ListProjectsComponent } from './components/list-projects/list-projects.component';
import { ViewProjectTasksComponent } from './components/view-project-tasks/view-project-tasks.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NewProjectComponent } from './components/new-project/new-project.component';
import { AddTaskComponent } from './components/add-task/add-task.component';
import { RevokeRoleComponent } from './components/revoke-role/revoke-role.component';


@NgModule({
  declarations: [
    AdminComponent,
    ListUsersComponent,
    SaveUserComponent,
    AddRoleComponent,
    AddNewRoleComponent,
    ListProjectsComponent,
    ViewProjectTasksComponent,
    ProfileComponent,
    NewProjectComponent,
    AddTaskComponent,
    RevokeRoleComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
