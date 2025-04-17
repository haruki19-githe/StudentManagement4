package raisetech.StudentManagement.controller;
//Controller


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.ResourceNotFoundException;
import raisetech.StudentManagement.service.StudentService;


import java.util.List;

/**
 * 受講生の検索や登録、更新などを行うRESTAPIとして受付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * 受講生詳細の一覧検索機能です。全件検索を行うので、条件指定は行わないものになります。
     *
     * @return　受講生詳細一覧（全件）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    @GetMapping("studentListNotUseUrl")
    public List<StudentDetail> getStudentList2() throws Exception {
        throw new ResourceNotFoundException("無効のURLです。");
    }


    //登録処理

    /**
     * 受講生詳細の登録を行います
     *
     * @param studentDetail 　受講生詳細
     * @return　実行結果
     */
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
        //StudentServiceに名前やIDの情報を持ったstudentDetailを送る
        StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }

    //更新処理

    /**
     * 受講生詳細の検索です。IDに紐づく任意の受講生の情報を取得します。
     *
     * @param id 　受講生ID
     * @return　受講生
     */
    @GetMapping("/student/{id}")
    public StudentDetail getStudent
    (@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {
        return service.searchStudent(id);
    }

    /**
     * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います。（論理削除）
     *
     * @param studentDetail 　受講生詳細
     * @return　実行結果
     */
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }


    //削除処理
    @GetMapping("/students/{id}")
    public String getStudents(@PathVariable String id, Model model) {
        StudentDetail studentDetail = service.searchStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "deleteStudent";
    }


    @PostMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "deleteStudent";
        }
        service.deleteStudent(studentDetail);
        return "redirect:/studentList";
    }


}




