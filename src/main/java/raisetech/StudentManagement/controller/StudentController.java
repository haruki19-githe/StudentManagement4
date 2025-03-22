package raisetech.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/studentList")
    public List<Student> getStudentList() {
        return service.searchStudentList();
    }

    @GetMapping("/studentCourseList")
    public List<StudentCourses> getStudentCourseList() {
        return service.searchStudentcourseList();
    }
}
