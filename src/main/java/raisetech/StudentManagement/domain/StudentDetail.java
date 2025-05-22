package raisetech.StudentManagement.domain;
//Model

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

import java.util.List;

@Data
@Schema(description = "受講生詳細")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

    private Student student;
    private List<StudentCourse> studentCourseList;
}
