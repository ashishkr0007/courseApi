package com.courseRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.courseRest.entities.Course;
import com.courseRest.services.CourseService;

@RestController
public class MyController {
    
    @Autowired
    private CourseService  courseService;
    //@RequestMapping(value ="/books", method = RequestMethod.GET )
   //GET
    @GetMapping("/courses")
     public ResponseEntity<List<Course>> getCourses(){
        List<Course> list =this.courseService.getAllCourses();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

     @GetMapping("/courses/{id}")
     public ResponseEntity<Optional<Course>> getCourse(@PathVariable("id") int id){
    	 Optional<Course> course= courseService.getCourseById(id);
     if(course==null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
     return ResponseEntity.of(Optional.of(course));
    }

    //POST
    @PostMapping("/courses")
   public ResponseEntity<Course> addCourse(@RequestBody Course course){
    	Course c=null;
    try {
        c=this.courseService.addCourse(course);
      System.out.println(course);
      return ResponseEntity.status(HttpStatus.CREATED).body(c);
        
    } catch (Exception e) {
        e.printStackTrace();
      return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
   
 }

//DELETE
@DeleteMapping("/courses/{courseId}")
     public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") int courseId)
     {
        try {
            this.courseService.deleteCourse(courseId);
            return ResponseEntity.status(HttpStatus.GONE).build();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      
    }

 //UPDATE
@PutMapping("/courses/{courseId}")
     public ResponseEntity<Course> UpdateBook(@RequestBody Course course,@PathVariable("courseId") int courseId)
      {
        try {
            this. courseService.updateCourse(course,courseId);
            return ResponseEntity.ok().body(course);
        } catch (Exception e) {
            e.printStackTrace();
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      
      
    }   
}