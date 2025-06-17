package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationStatus {

    @Pattern(regexp = "^\\d+$", message = "数字のみを入力するようにしてください。")
    @Schema(description = "id", type = "string", example = "1")
    private String id;

    @Schema(description = "受講生id", type = "string", example = "12")
    private String studentCourseId;

    @Schema(description = "申込状況", type = "string", example = "受講中")
    private String registrationStatus;
}
