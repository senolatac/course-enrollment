import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material';
import {User} from "../../model/user";
import {Course} from "../../model/course";
import {Transaction} from "../../model/transaction";
import {CourseService} from "../../services/course.service";
import {Router, ActivatedRoute} from "@angular/router";
import {EmitterService} from "../../services/emitter.service";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses: Array<Course>;
  currentUser: User;
  errorMessage:string;
  infoMessage:string;
  searchText:string;
  displayedColumns: string[] = ['detail', 'title', 'author', 'category', 'action'];
  dataSource: MatTableDataSource<Course> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private courseService :CourseService, private router: Router, private route: ActivatedRoute
             , private emitterService: EmitterService) {
     this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
     this.emitterService.onSearch.subscribe({
         next: (event: any) => {
             this.search(event.id, event.text);
         }
     })
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      var id = params.get('id');
      if(id == "1" || id=="2"){
        this.searchText = null;
      }else{
        this.searchText = params.get('text');
      }
          this.search(params.get('id'), this.searchText);
    });
  }

  search(id, text){
    if(id==2){
      this.courseService.popularCourses().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
      return;
    }
    if(id!=1 && this.searchText){
      this.courseService.filterCourses(this.searchText).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    }else{
      this.courseService.allCourses().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    }
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  detail(course: Course){
    localStorage.setItem('currentCourse', JSON.stringify(course));
    this.router.navigate(['/course',course.id]);
  }

  enroll(course: Course) {
    if(!this.currentUser){
      this.errorMessage="To enroll a course, you should sign in.";
      return;
    }
    var transaction = new Transaction();
    transaction.course = course;
    transaction.userId = this.currentUser.id;
    this.courseService.createTransaction(transaction)
    .subscribe(data=>{
            this.infoMessage = "You enrolled the cource successfully.";
            },err=>{
            this.errorMessage="Unexpected error is occurred.";
            }
          )
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
