package raisetech.StudentManagement.controller;
//Controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
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

        model.addAttribute("studentList", converter.convertStudentDetails(students, studentCourses));
        return "studentList";

    }

    @GetMapping("/studentCourseList")
    public List<StudentCourses> getStudentCourseList() {
        return service.searchStudentCourseList();
    }

    //登録処理
    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourses(Arrays.asList(new StudentCourses()));
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";
    }


    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "registerStudent";
        }
        //StudentServiceに名前やIDの情報を持ったstudentDetailを送る
        service.registerStudent(studentDetail);
        //新規受講生情報の登録処理を実装する
        //コース情報も一緒に登録できるに実装する。コースは単体でいい。
        return "redirect:/studentList";
    }

    //更新処理
    @GetMapping("/student/{id}")
    public String getStudent (@PathVariable String id, Model model) {
        StudentDetail studentDetail = service.searchStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "updateStudent";
    }


    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "updateStudent";
        }
        service.updateStudent(studentDetail);
        return "redirect:/studentList";
    }


}
