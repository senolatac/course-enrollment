import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./services/auth.service";
import {EmitterService} from "./services/emitter.service";
import {User} from "./model/user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'course-enrollment-client';
  searchText: string;
  currentUser: User;
  private _serviceSubscription;

  constructor(private authService: AuthService, private router: Router, private emitterService: EmitterService) {
     this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
     this._serviceSubscription = this.emitterService.onLogin.subscribe({
         next: (event: any) => {
             this.currentUser = JSON.parse(event.message);
         }
     })
  }

  logOut() {
    this._serviceSubscription.unsubscribe();
    this.authService.logOut()
      .subscribe(
        data => {
          this.currentUser = null;
          this.router.navigate(['/login']);
        },
        error => {

        });
  }

  search(){
    if(this.router.url == '/course-list'){
      this.emitterService.emitSearch(0, this.searchText);
      return;
    }
    this.router.navigate(['/course-list',{id: 0, text:this.searchText}]);
    console.log(this.router.url);
  }

  searchAll(){
    if(this.router.url == '/course-list'){
      this.emitterService.emitSearch(1, this.searchText);
      return;
    }
    this.router.navigate(['/course-list',{id: 1}]);
  }

  searchPopular(){
    if(this.router.url == '/course-list'){
      this.emitterService.emitSearch(2, this.searchText);
      return;
    }
    this.router.navigate(['/course-list',{id: 2}]);
  }
}
