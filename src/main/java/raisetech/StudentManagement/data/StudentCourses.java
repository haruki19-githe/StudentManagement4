package raisetech.StudentManagement.data;
//Model

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class StudentCourses {
    private String id;
    private String studentId;
    private String courseName;
    private LocalDateTime courseStartDate;
    private LocalDateTime courseEndDate;
}
