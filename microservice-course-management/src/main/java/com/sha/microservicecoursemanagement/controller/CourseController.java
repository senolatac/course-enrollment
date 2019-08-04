package com.sha.microservicecoursemanagement.controller;

import com.sha.microservicecoursemanagement.intercomm.LogClient;
import com.sha.microservicecoursemanagement.intercomm.UserClient;
import com.sha.microservicecoursemanagement.model.Transaction;
import com.sha.microservicecoursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
public class CourseController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private LogClient logClient;

    @Autowired
    private CourseService courseService;
	
	@Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment env;

    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/service/port")
    public String getPort(){
        return "Service is working at port : " + env.getProperty("local.server.port");
    }

    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances() {
        return ResponseEntity.ok(discoveryClient.getInstances(serviceId));
    }

    @PostMapping("/user")
    public ResponseEntity<?> filterTransactions(@RequestBody Long userId){
        return new ResponseEntity<>(courseService.filterTransactionsOfUser(userId), HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> popularCourses(){
        List<Long> popularIdList = logClient.getPopularCourses();
        if(popularIdList==null || popularIdList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(courseService.filterCoursesByIdList(popularIdList), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> allCourses(){
        return new ResponseEntity<>(courseService.allCourses(), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterCourses(@RequestBody String text){
        return new ResponseEntity<>(courseService.filterCourses(text), HttpStatus.OK);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction){
        transaction.setDateOfIssue(LocalDateTime.now());
        transaction.setCourse(courseService.findCourseById(transaction.getCourse().getId()));
        courseService.saveTransaction(transaction);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/students")
    public ResponseEntity<?> findCourseStudents(@RequestBody Long courseId){
        List<Transaction> list = courseService.filterTransactionsOfCourse(courseId);
        if(list!=null && !list.isEmpty()){
            List<Long> userIdList = list.parallelStream().map(t->t.getUserId()).collect(Collectors.toList());
            List<String> students = userClient.getUsers(userIdList);
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.notFound().build();
    }

}
