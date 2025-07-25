package raisetech.StudentManagement.repository;
//Model

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

    /**
     * 受講生の全件検索を行います。
     *
     * @return　受講生一覧（全件）
     */
    List<Student> searchStudentList();

    /**
     * 受講生の検索を行います。
     *
     * @param id 　受講生ID
     * @return　受講生
     */
    Student searchStudent(String id);

    /**
     * @param name 受講生の名前
     * @return　受講生のリスト
     */
    List<Student> searchStudentName(String name);

    /**
     * @param area 受講生の住まい
     * @return　受講生のリスト
     */
    List<Student> searchStudentArea(String area);

    /**
     * @param gender 受講生の性別
     * @return　受講生のリスト
     */
    List<Student> searchStudentGender(String gender);


    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return　受講生のコース情報（全件）
     */
    List<StudentCourse> searchStudentCourseList();

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param studentId 　受講生ID
     * @return　受講生IDに紐づく受講生コース情報
     */
    List<StudentCourse> searchStudentCourse(String studentId);

    /**
     * @return 受講生の申込状況（全件）
     */
    List<RegistrationStatus> searchRegistrationStatusList();

    /**
     * 受講生を新規登録します。IDに関しては自動採番を行う。
     *
     * @param student 　受講生
     */
    //Repositoryでデータの保存、受講生情報更新処理
    void registerStudent(Student student);

    /**
     * 受講生コース情報を新規登録します。IDに関しては自動採番を行う。
     *
     * @param studentCourse 　受講生コース情報
     */
    void registerStudentCourse(StudentCourse studentCourse);

    //受講生情報更新処理

    /**
     * 受講生を更新します。
     *
     * @param student 　受講生
     */
    void updateStudent(Student student);

    /**
     * 受講生コース情報のコース名を更新します。
     *
     * @param studentCourse 　受講生コース情報
     */
    void updateStudentCourse(StudentCourse studentCourse);


    //受講生情報削除処理
    @Delete("DELETE FROM students WHERE id = #{id}")
    void deleteStudent(Student student);


    @Delete("DELETE FROM students_courses WHERE id = #{id} ")
    void deleteStudentCourse(StudentCourse studentCourse);

}
