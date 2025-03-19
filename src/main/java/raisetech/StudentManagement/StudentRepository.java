package raisetech.StudentManagement;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select("SELECT * FROM students")
    List<Student> StudentListSearch();

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> StudentCourseListSearch();

}
