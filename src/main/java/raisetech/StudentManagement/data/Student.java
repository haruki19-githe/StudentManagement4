package raisetech.StudentManagement.data;
//Model

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
public class Student {
    @Schema(description = "id", type = "string", example = "1")
    @Pattern(regexp = "^\\d+$", message = "数字のみを入力するようにしてください。")
    private String id;

    @Schema(description = "名前", type = "string", example = "山田")
    private String name;

    @Schema(description = "名前のフリガナ", type = "string", example = "ヤマダ")
    private String furigana;

    @Schema(description = "名前のニックネーム", type = "string", example = "やまちゃん")
    private String nickName;

    @Schema(description = "メールアドレス", type = "string", example = "taroYamada@emample.com")
    private String email;

    @Schema(description = "住んでいる地域", type = "string", example = "北海道")
    private String area;

    @Schema(description = "年齢", type = "int", example = "35")
    private int age;

    @Schema(description = "性別", type = "string", example = "男性")
    private String gender;

    @Schema(description = "備考欄", type = "string", example = "ご飯が好き")
    private String remark;

    @Schema(description = "削除フラグ", type = "boolean")
    private boolean isDeleted;


}
