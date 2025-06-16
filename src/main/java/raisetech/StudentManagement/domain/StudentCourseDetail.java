package raisetech.StudentManagement.domain;

import lombok.*;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.data.StudentCourse;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseDetail {
    private StudentCourse studentCourse;
    private List<RegistrationStatus> registrationStatuseList;
}
