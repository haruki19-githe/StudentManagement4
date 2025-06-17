package raisetech.StudentManagement.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository sut;


    @Test
    void 受講生の全件検索が行えること() {
        List<Student> actual = sut.searchStudentList();
        assertThat(actual.size()).isEqualTo(5);
    }

    @Test
    void 受講生の登録が行えていること() {
        Student student = createStudent();


        sut.registerStudent(student);

        List<Student> actual = sut.searchStudentList();
        assertThat(actual.size()).isEqualTo(6);

    }

    @Test
    void 受講生詳細の検索が行えること() {
        String id = "1";
        Student student = createStudent();
        student.setId(id);


        Student student2 = createStudent();
        student2.setId(id);

        Student actual = sut.searchStudent(id);
        assertThat(actual.getId()).isEqualTo(student2.getId());

    }

    @Test
    void 受講生のコース情報の全件検索が行えること() {
        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.size()).isEqualTo(10);
    }

    @Test
    void 受講生IDに紐づく受講生コース情報の検索が行えること() {
        String studentId = "1";
        List<StudentCourse> actual = sut.searchStudentCourse(studentId);
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void 受講生コース情報の登録が行えること() {
        StudentCourse studentCourse = createStudentCourse();


        sut.registerStudentCourse(studentCourse);

        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.size()).isEqualTo(11);
    }

    @Test
    void 受講生の情報を更新できること() {
        Student student = createStudent();
        student.setArea("仙台");

        sut.updateStudent(student);
        Student actual = sut.searchStudent("2");

        assertThat(actual.getArea()).isEqualTo(student.getArea());
    }

    @Test
    void 受講生コース情報のコース名を更新が行えること() {
        StudentCourse studentCourse = createStudentCourse();
        studentCourse.setCourseName("AI基礎コース");

        sut.updateStudentCourse(studentCourse);

        List<StudentCourse> actual = sut.searchStudentCourse("5");
        assertThat(actual.get(0).getCourseName()).isEqualTo("AI基礎コース");
    }

    private static Student createStudent() {
        Student student = new Student();
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
        studentCourse.setStudentId("1");
        studentCourse.setCourseName("javaコース");
        return studentCourse;
    }
}