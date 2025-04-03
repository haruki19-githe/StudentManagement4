package raisetech.StudentManagement.Repository;
//Model

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM students")
    List<Student> studentListSearch();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(String id);

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> studentCourseListSearch();

    @Select("SELECT * FROM students_courses WHERE student_id = #{student_id}")
    List<StudentCourses> searchStudentCourse(String studentId);

    //Repositoryでデータの保存
    @Insert("INSERT INTO students(name,furigana,nickName,email,area,age,gender,remark,is_deleted)" +
            " VALUES (#{name},#{furigana},#{nickName},#{email},#{area},#{age},#{gender},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);


    @Insert("INSERT INTO students_courses(student_id,course_name,course_start_date,course_end_date) " +
            "VALUES(#{studentId},#{courseName},#{courseStartDate},#{courseEndDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentCourses(StudentCourses studentCourses);

    //受講生情報更新処理
    @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickName}, email = #{email}," +
            " area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
    void updateStudent(Student student);


    @Update("UPDATE  students_courses SET course_name = #{courseName}  WHERE id = #{id} ")
    void updateStudentCourse(StudentCourses studentCourses);

}
