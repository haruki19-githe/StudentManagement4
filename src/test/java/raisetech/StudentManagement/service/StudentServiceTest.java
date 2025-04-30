package raisetech.StudentManagement.service;


import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentDetail studentDetail;

    @Mock
    private StudentConverter converter;

    private StudentService sut;

    //テストクラスの各テストメソッドの実行前に毎回実行される。
    @BeforeEach
    void before() {
        sut = new StudentService(repository, converter);
    }

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
    void 受講生詳細の検索＿リポジトリの処理＿StudentDetailが適切に呼び出せていること＿() {
        String id = "1";
        Student student = new Student();
        List<StudentCourse> studentCourse = new ArrayList<>();

        Student studentTest = new Student();
        studentTest.setId("1");
        studentTest.setName("佐藤太朗");
        studentTest.setFurigana("サトウタロウ");
        studentTest.setNickName("タロ");
        studentTest.setEmail("taro@example.com");
        studentTest.setArea("東京");
        studentTest.setAge(25);
        studentTest.setGender("男性");
        studentTest.setRemark("白米が好き");

        List<StudentCourse> studentCourseTest = new ArrayList<>();

        StudentDetail studentDetailTest = new StudentDetail(studentTest, studentCourseTest);
        when(repository.searchStudent(id)).thenReturn(student);
        when(repository.searchStudentCourse(id)).thenReturn(studentCourse);

        //実行
        sut.searchStudent(id);

        //検証
        verify(repository, Mockito.times(1))
                .searchStudent(id);
        verify(repository, Mockito.times(1))
                .searchStudentCourse(id);
        assertThat(studentDetailTest).isEqualTo(new StudentDetail(student, studentCourse));

    }


}