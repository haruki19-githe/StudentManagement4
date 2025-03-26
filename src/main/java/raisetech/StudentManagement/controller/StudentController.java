package raisetech.StudentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentCourseList();

        model.addAttribute("studentList",converter.convertStudentDetails(students,studentCourses));
        return "studentList";

    }


    @GetMapping("/studentCourseList")
    public List<StudentCourses> getStudentCourseList() {
        return service.searchStudentCourseList();
    }
}
