import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {EmitterService} from "../../services/emitter.service";
import {Router} from "@angular/router";
import {User} from "../../model/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User=new User();
  currentUser: User;
  errorMessage:string;
  constructor(private authService :AuthService, private router: Router, private emitterService: EmitterService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    if(this.currentUser){
      this.router.navigate(['/profile']);
    }
  }

  login(){
    this.authService.logIn(this.user)
      .subscribe(data=>{
        this.emitterService.emitLogin(JSON.stringify(data));
        this.router.navigate(['/profile']);
        },err=>{
        this.errorMessage="Error :  Username or password is incorrect";
        }
      )
  }

}
