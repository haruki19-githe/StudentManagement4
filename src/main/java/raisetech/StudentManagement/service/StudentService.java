package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.Repository.StudentRepository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> searchStudentList() {
        List<Student> only30sai = new ArrayList<>();
        for (Student student : repository.StudentListSearch()) {
            if (student.getAge() >= 30 && student.getAge() <= 39) {
                only30sai.add(student);
            }
        }
        return only30sai;
    }

    public List<StudentCourses> searchStudentcourseList() {
        List<StudentCourses> onlyJavaCourse = new ArrayList<>();
        for (StudentCourses student2 : repository.StudentCourseListSearch()) {
            if (student2.getCourseName().matches("javaコース")) {
                onlyJavaCourse.add(student2);
            }
        }
        return onlyJavaCourse;
    }

}
