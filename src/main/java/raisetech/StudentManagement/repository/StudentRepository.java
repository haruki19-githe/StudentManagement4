package raisetech.StudentManagement.repository;
//Model

import org.apache.ibatis.annotations.*;
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
    @Select("SELECT * FROM students WHERE is_deleted = false ")
    List<Student> searchStudentList();

    /**
     * 受講生の検索を行います。
     *
     * @param id 　受講生ID
     * @return　受講生
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);

    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return　受講生のコース情報（全件）
     */
    @Select("SELECT * FROM students_courses")
    List<StudentCourse> searchStudentCourseList();

    /**
     * 受講生IDに紐づく受講生コース情報を検索検索します。
     *
     * @param studentId 　受講生ID
     * @return　受講生IDに紐づく受講生コース情報
     */
    @Select("SELECT * FROM students_courses WHERE student_id = #{student_id}")
    List<StudentCourse> searchStudentCourse(String studentId);

    /**
     * 受講生を新規登録します。IDに関しては自動採番を行う。
     *
     * @param student 　受講生
     */
    //Repositoryでデータの保存、受講生情報更新処理
    @Insert("INSERT INTO students(name,furigana,nickName,email,area,age,gender,remark,is_deleted)" +
            " VALUES (#{name},#{furigana},#{nickName},#{email},#{area},#{age},#{gender},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    /**
     * 受講生コース情報を新規登録します。IDに関しては自動採番を行う。
     *
     * @param studentCourse 　受講生コース情報
     */
    @Insert("INSERT INTO students_courses(student_id,course_name,course_start_date,course_end_date) " +
            "VALUES(#{studentId},#{courseName},#{courseStartDate},#{courseEndDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentCourse(StudentCourse studentCourse);

    //受講生情報更新処理

    /**
     * 受講生を更新します。
     *
     * @param student　受講生
     */
    @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickName}, email = #{email}," +
            " area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);

    /**
     * 受講生コース情報のコース名を更新します。
     *
     * @param studentCourse　受講生コース情報
     */
    @Update("UPDATE  students_courses SET course_name = #{courseName}  WHERE id = #{id} ")
    void updateStudentCourse(StudentCourse studentCourse);

    //受講生情報削除処理
    @Delete("DELETE FROM students WHERE id = #{id}")
    void deleteStudent(Student student);


    @Delete("DELETE FROM students_courses WHERE id = #{id} ")
    void deleteStudentCourse(StudentCourse studentCourse);
}
