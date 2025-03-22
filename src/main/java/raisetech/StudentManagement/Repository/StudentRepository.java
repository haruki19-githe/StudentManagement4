package raisetech.StudentManagement.Repository;

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM students")
    List<Student> StudentListSearch();

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> StudentCourseListSearch();

}
