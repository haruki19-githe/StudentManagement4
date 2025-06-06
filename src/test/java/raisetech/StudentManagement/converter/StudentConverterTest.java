package raisetech.StudentManagement.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class StudentConverterTest {
    @InjectMocks
    private StudentConverter sut;

    @Mock
    private StudentConverter studentConverter;


    @Test
    void 複数のstudentCourseがあった上で対象のものだけがconverterで紐づくことを確認() {
        Student student = createStudent();


        List<Student> studentList = List.of(student);
        StudentCourse studentCourse = new StudentCourse("1", "1", "Javaコース");
        StudentCourse studentCourse2 = new StudentCourse("2", "2", "AWSコース");
        List<StudentCourse> studentCourseList = List.of(studentCourse);

        List<StudentDetail> studentDetails = new ArrayList<>();
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        List<StudentCourse> convertStudentCourseList = new ArrayList<>();
        if (student.getId().equals(studentCourse.getStudentId())) {
            convertStudentCourseList.add(studentCourse);
        }
        if (student.getId().equals(studentCourse2.getStudentId())) {
            convertStudentCourseList.add(studentCourse2);
        }
        studentDetail.setStudentCourseList(convertStudentCourseList);
        studentDetails.add(studentDetail);


        List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

        Assertions.assertEquals(studentDetails, actual);

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
}

