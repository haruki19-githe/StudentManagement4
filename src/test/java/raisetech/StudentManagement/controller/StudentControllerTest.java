package raisetech.StudentManagement.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    ArgumentCaptor<StudentDetail> captor = ArgumentCaptor.forClass(StudentDetail.class);


    @Test
    void 受講生詳細の一覧検索が実行できてからのリストが返ってくること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
                .andExpect(status().isOk());

        verify(service, times(1)).searchStudentList();
    }

    @Test
    void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
        Student student = new Student("1", "佐藤二郎", "サトウジロウ", "ジロ",
                "ziro@example.com", "東京", 44, "男性", "白米が好き");
        student.setId("1");
        student.setName("江南康二");
        student.setFurigana("エナミコウジ");
        student.setNickName("エナミ");
        student.setEmail("test@example.com");
        student.setArea("奈良県");
        student.setGender("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックにかかること() {
        Student student = new Student("1", "佐藤二郎", "サトウジロウ", "ジロ",
                "ziro@example.com", "東京", 44, "男性", "白米が好き");
        student.setId("テストです。");
        student.setName("江南康二");
        student.setFurigana("エナミコウジ");
        student.setNickName("エナミ");
        student.setEmail("test@example.com");
        student.setArea("奈良県");
        student.setGender("男性");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").containsOnly("数字のみを入力するようにしてください。");


    }

    @Test
    void 受講生詳細の受講生コース情報で適切な値を入力した時に入力チェックに異常が発生しないこと() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId("1");
        studentCourse.setStudentId("1");
        studentCourse.setCourseName("Javaコース");

        Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void 受講生詳細の受講生コース情報でIDに数字以外を用いたときに入力チェックにかかること() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId("これはテストです。");
        studentCourse.setStudentId("1");
        studentCourse.setCourseName("Javaコース");

        Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").containsOnly("数字のみを入力するようにしてください。");


    }

    @Test
    void 受講生詳細の検索が実行できること() throws Exception {
        String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", "1"))
                .andExpect(status().isOk());

        verify(service, times(1)).searchStudent(id);
    }

    @Test
    void 受講生詳細の登録が実行できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "student": {
                                         "name": "佐藤太朗",
                                         "furigana" : "サトウタロウ",
                                         "nickName":"タロ",
                                         "email":"taro@example.com ",
                                         "area":"東京",
                                         "age":"25",
                                         "gender":"男性",
                                         "remark":"白米が好き"
                                     },
                                     "studentCourseList" :[
                                 {
                                     "courseName":"java制作コース"
                                 }
                                     ]
                                
                                 }
                                """))
                .andExpect(status().isOk());


        verify(service, times(1)).registerStudent(captor.capture());

    }


    @Test
    void 受講生詳細の更新が実行できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/updateStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "student": {
                                    "id":"1",
                                    "name":"田中太郎",
                                    "furigana":"サトウタロウ",
                                    "nickname":"タロ",
                                    "email":"taro@example.com",
                                    "area":"東京",
                                    "age":25,
                                    "gender":"男性",
                                    "remark":"白米が好き"
                                  },
                                  "studentCourseList": [{
                                
                                                                    "courseName": "javaコース"
                                
                                                                }]
                                }
                                """))
                .andExpect(status().isOk());


        verify(service, times(1)).updateStudent(captor.capture());
    }


}