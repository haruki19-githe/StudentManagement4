package raisetech.StudentManagement.data;
//Model

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

    @Pattern(regexp = "^\\d+$", message = "数字のみを入力するようにしてください。")
    @Schema(description = "id", type = "string", example = "1")
    private String id;

    @Schema(description = "受講生id", type = "string", example = "12")
    private String studentId;

    @Schema(description = "コース名", type = "string", example = "javaコース")
    private String courseName;

    @Schema(description = "コース開始日", type = "LocalDateTime", example = "2024-01-01T00:00:00")
    private LocalDateTime courseStartDate;

    @Schema(description = "コース終了日", type = "LocalDateTime", example = "2025-12-31T00:00:00")
    private LocalDateTime courseEndDate;


}
