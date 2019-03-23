import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../model/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User=new User();
  currentUser: User;
  errorMessage:string;
  constructor(private authService :AuthService, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    if(this.currentUser){
      this.router.navigate(['/profile']);
    }
  }

  register() {
  this.authService.createAccount(this.user).subscribe(data => {
      this.router.navigate(['/login']);
    }, err => {
      console.log(err);
      this.errorMessage = "username already exist";
    }
  )
}

}
