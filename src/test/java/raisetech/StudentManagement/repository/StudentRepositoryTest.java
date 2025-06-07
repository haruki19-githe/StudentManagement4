package raisetech.StudentManagement.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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
        assertThat(actual.size()).isEqualTo(1);

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

}