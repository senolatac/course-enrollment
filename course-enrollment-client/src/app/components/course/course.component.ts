import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material';
import {User} from "../../model/user";
import {Log} from "../../model/log";
import {Ip} from "../../model/ip";
import {Course} from "../../model/course";
import {CourseService} from "../../services/course.service";
import {LogService} from "../../services/log.service";
import {Router, ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  courseId: string;
  currentCourse: Course;
  currentLog: Log;
  courseHitCount: any;
  displayedColumns: string[] = ['name'];
  dataSource: MatTableDataSource<string> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private courseService :CourseService, private logService :LogService,
     private router: Router, private route: ActivatedRoute) {
    this.currentCourse = JSON.parse(localStorage.getItem('currentCourse'));
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.has('id')){
        this.courseId = params.get('id');
        this.currentLog = new Log();
        this.currentLog.courseId = this.courseId;
        this.logService.getSummaryOfCourse(this.courseId).subscribe(data => {
          if(data){
           this.courseHitCount = data.hitCount;
         }else{
           this.courseHitCount = 0;
         }

        });
        this.logService.getIpClient().subscribe((data: Ip) => {
          this.currentLog.ip = data.ip;
          this.hit(data.ip);
        });
        this.findStudents();
      }
    });
  }

  hit(ip){
    this.logService.createLog(this.currentLog).subscribe(data =>{
      console.log("hit : " + ip);
    });
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  findStudents(){
    this.courseService.filterStudents(this.courseId).subscribe(data => {
      this.dataSource.data = data;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
