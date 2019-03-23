import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { CourseComponent } from './components/course/course.component';
import { CourseListComponent } from './components/course-list/course-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'course', component: CourseComponent },
  { path: 'course/:id', component: CourseComponent },
  { path: 'course-list', component: CourseListComponent },
  { path: "course-list/:id", component: CourseListComponent },
  { path: "course-list/:id/:text", component: CourseListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
