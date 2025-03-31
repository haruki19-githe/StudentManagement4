package raisetech.StudentManagement.service;
//Model

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.Repository.StudentRepository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> searchStudentList() {
        return repository.studentListSearch();
    }

    //StudentServiceで登録処理
    public void registerStudent(StudentDetail studentDetail) {
        repository.insertStudent(studentDetail.getStudent());
    }

    public List<StudentCourses> searchStudentCourseList() {
        return repository.studentCourseListSearch();
    }
}
