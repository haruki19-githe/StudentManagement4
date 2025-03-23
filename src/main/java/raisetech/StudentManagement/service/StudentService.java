package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.Repository.StudentRepository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> searchStudentList() {
        List<Student> only30sai = repository.StudentListSearch().stream().filter(student -> student.getAge() >= 30 && student.getAge() <= 39).collect(Collectors.toList());
        return only30sai;
    }

    public List<StudentCourses> searchStudentCourseList() {
        List<StudentCourses> onlyJavaCourse = repository.StudentCourseListSearch().stream().filter(student2 -> student2.getCourseName().matches("javaコース")).collect(Collectors.toList());
        return onlyJavaCourse;
    }

}
