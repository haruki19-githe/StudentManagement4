package raisetech.StudentManagement.service;
//Model

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.Repository.StudentRepository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentDetail searchStudent(String id) {
        Student student = repository.searchStudent(id);
        List<StudentCourses> studentsCourses = repository.searchStudentCourse(student.getId());
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourses(studentsCourses);
        return studentDetail;
    }

    public List<Student> searchStudentList() {
        return repository.studentListSearch();
    }


    public List<StudentCourses> searchStudentCourseList() {
        return repository.studentCourseListSearch();
    }

    //StudentServiceで登録処理
    @Transactional
    public void registerStudent(StudentDetail studentDetail) {
        repository.registerStudent(studentDetail.getStudent());
        //コース情報登録
        for (StudentCourses studentCourse : studentDetail.getStudentCourses()) {
            studentCourse.setStudentId(studentDetail.getStudent().getId());
            studentCourse.setCourseStartDate(LocalDateTime.now());
            studentCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));
            repository.registerStudentCourses(studentCourse);
        }
    }

    //更新処理
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        //コース情報登録
        for (StudentCourses studentCourse : studentDetail.getStudentCourses()) {
            repository.updateStudentCourse(studentCourse);
        }
    }


}
