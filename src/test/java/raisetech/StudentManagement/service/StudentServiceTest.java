package raisetech.StudentManagement.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService sut;

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;


    @Test
    void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
        //事前準備
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        when(repository.searchStudentList()).thenReturn(studentList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        //実行
        sut.searchStudentList();

        //検証
        verify(repository, Mockito.times(1))
                .searchStudentList();
        verify(repository, Mockito.times(1))
                .searchStudentCourseList();
        verify(converter, Mockito.times(1))
                .convertStudentDetails(studentList, studentCourseList);


    }

    @Test
    void 受講生詳細の検索＿リポジトリの処理が適切に呼び出せていること() {
        String id = "1";
        Student student = createStudent();


        List<StudentCourse> studentCourse = new ArrayList<>();
        when(repository.searchStudent(id)).thenReturn(student);
        when(repository.searchStudentCourse(id)).thenReturn(studentCourse);

        //実行
        StudentDetail expected = new StudentDetail(student, studentCourse);
        StudentDetail actual = sut.searchStudent(id);


        //検証
        verify(repository, Mockito.times(1))
                .searchStudent(id);
        verify(repository, Mockito.times(1))
                .searchStudentCourse(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void 受講生詳細の登録＿リポジトリの処理が適切に呼び出せていること() {
        Student student = createStudent();

        StudentCourse studentCourse = createStudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);

        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        StudentDetail result = sut.registerStudent(studentDetail);

        verify(repository, Mockito.times(1))
                .registerStudent(student);
        verify(repository, Mockito.times(1))
                .registerStudentCourse(studentCourse);

        Assertions.assertEquals(studentDetail, result);

    }


    //Todo initのテストをいつかやる

    @Test
    void 受講生詳細の更新＿リポジトリの処理が適切に呼び出せていること() {
        Student student = createStudent();

        StudentCourse studentCourse = createStudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);


        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        sut.updateStudent(studentDetail);


        verify(repository, Mockito.times(1))
                .updateStudent(studentDetail.getStudent());
        verify(repository, Mockito.times(1))
                .updateStudentCourse(studentCourse);

    }

    private static Student createStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("佐藤二郎");
        student.setFurigana("サトウジロウ");
        student.setNickName("ジロ");
        student.setEmail("ziro@example.com");
        student.setArea("東京");
        student.setAge(44);
        student.setGender("男性");
        student.setRemark("白米が好き");
        return student;
    }

    private static StudentCourse createStudentCourse() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId("1");
        studentCourse.setStudentId("1");
        studentCourse.setCourseName("javaコース");
        return studentCourse;
    }
}