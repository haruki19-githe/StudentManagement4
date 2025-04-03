package raisetech.StudentManagement.Repository;
//Model

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM students")
    List<Student> studentListSearch();

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> studentCourseListSearch();

    //Repositoryでデータの保存
    @Insert("INSERT INTO students(name,furigana,nickName,email,area,age,gender,remark,is_deleted)" +
            " VALUES (#{name},#{furigana},#{nickName},#{email},#{area},#{age},#{gender},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);


    @Insert("INSERT INTO students_courses(student_id,course_name,course_start_date,course_end_date) " +
            "VALUES(#{studentId},#{courseName},#{courseStartDate},#{courseEndDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentCourses(StudentCourses studentCourses);

    @Update("UPDATE students SET name= #{name}, furigana = #{furigana}, nickName= #{nickName}," +
            "email = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, false ), WHERE id = #{id}")
    void updateStudent(Student student);
}
