package raisetech.StudentManagement.data;
//Model

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class StudentCourse {
    private String id;
    private String studentId;
    private String courseName;
    private LocalDateTime courseStartDate;
    private LocalDateTime courseEndDate;
}
