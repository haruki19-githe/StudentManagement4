package raisetech.StudentManagement.data;
//Model

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    @Pattern(regexp = "^\\d+$")
    private String id;
    private String name;
    private String furigana;
    private String nickName;
    private String email;
    private String area;
    private int age;
    private String gender;
    private String remark;
    private boolean isDeleted;
}
