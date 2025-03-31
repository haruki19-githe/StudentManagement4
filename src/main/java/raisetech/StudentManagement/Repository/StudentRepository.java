package raisetech.StudentManagement.Repository;
//Model

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM students")
    List<Student> studentListSearch();
//Repositoryでデータの保存
    @Insert("INSERT INTO students VALUES (#{id},#{name},#{furigana},#{nickName},#{email},#{area},#{age},#{gender},#{remark},false)")
    void insertStudent(Student student);

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> studentCourseListSearch();

}
