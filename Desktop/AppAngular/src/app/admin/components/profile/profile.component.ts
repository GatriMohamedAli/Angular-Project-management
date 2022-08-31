import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { JwtHandleService } from 'src/app/services/jwt-handle.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private jwtHandler:JwtHandleService, private adminService:AdminService) { }
  imageUrl="http://localhost:8085/api/v1/file/"
  ngOnInit(): void {
    const user=this.jwtHandler.getUser();
    this.imageUrl+=user.Id;

  }

}
