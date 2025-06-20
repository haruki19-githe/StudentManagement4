package raisetech.StudentManagement.service;
//Model

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.converter.StudentCourseConverter;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.domain.StudentCourseDetail;
import raisetech.StudentManagement.exception.ResourceNotFoundException;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {
    private StudentRepository repository;
    private StudentConverter converter;
    private StudentCourseConverter courseConverter;

    @Autowired
    public StudentService(StudentRepository repository, StudentConverter converter, StudentCourseConverter courseConverter) {
        this.repository = repository;
        this.converter = converter;
        this.courseConverter = courseConverter;
    }

    /**
     * 受講生詳細検索です。IDに紐づく受講生詳細情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
     *
     * @param id 　受講生ID
     * @return　受講生詳細
     */
    public StudentDetail searchStudent(String id) {
        //受講生情報(id一個)
        Student student = repository.searchStudent(id);
        if (student == null) {
            throw new ResourceNotFoundException("指定されたIDの受講生は存在しません。:" + id);
        }
        //コース情報(受講生情報に紐づくid)
        List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
        return new StudentDetail(student, studentCourse);
    }

    /**
     * @param name 　受講生の名前
     * @return　受講生詳細
     */
    public List<StudentDetail> searchStudentName(String name) {
        List<Student> students = repository.searchStudentName(name);
        if (students == null || students.isEmpty()) {
            throw new ResourceNotFoundException("指定された名前の受講生は存在しません。:" + name);
        }
        List<StudentDetail> StudentDetails = new ArrayList<>();
        for (Student student : students) {
            List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
            StudentDetails.add(new StudentDetail(student, studentCourse));
        }

        return StudentDetails;
    }

    /**
     * @param area 　受講生の住まい
     * @return　受講生詳細
     */
    public List<StudentDetail> searchStudentArea(String area) {
        List<Student> students = repository.searchStudentArea(area);
        if (students == null || students.isEmpty()) {
            throw new ResourceNotFoundException("指定された住まいの受講生は存在しません。:" + area);
        }
        List<StudentDetail> StudentDetails = new ArrayList<>();
        for (Student student : students) {
            List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
            StudentDetails.add(new StudentDetail(student, studentCourse));
        }

        return StudentDetails;
    }

    /**
     * @param gender 　受講生の性別
     * @return　受講生詳細
     */
    public List<StudentDetail> searchStudentGender(String gender) {
        List<Student> students = repository.searchStudentGender(gender);
        if (students == null || students.isEmpty()) {
            throw new ResourceNotFoundException("指定された性別の受講生は存在しません。:" + gender);
        }
        List<StudentDetail> StudentDetails = new ArrayList<>();
        for (Student student : students) {
            List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
            StudentDetails.add(new StudentDetail(student, studentCourse));
        }

        return StudentDetails;
    }

    /**
     * 受講生詳細の一覧検索機能です。全件検索を行うので、条件指定は行わないものになります
     *
     * @return　受講生詳細一覧（全件）
     */
    public List<StudentDetail> searchStudentList() {
        List<Student> studentList = repository.searchStudentList();
        List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
        return converter.convertStudentDetails(studentList, studentCourseList);
    }

    /**
     * @return 受講生コース詳細一覧（全件）
     */
    public List<StudentCourseDetail> searchStudentCourseList() {
        List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
        List<RegistrationStatus> registrationStatus = repository.searchRegistrationStatusList();
        return courseConverter.convertStudentCourseDetails(studentCourseList, registrationStatus);

    }

    //StudentServiceで登録処理

    /**
     * 受講生詳細の登録を行います。
     * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値やコース開始日、コース終了日を設定します。
     *
     * @param studentDetail 　受講生詳細
     * @return　登録情報を付与した受講生詳細
     */
    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        Student student = studentDetail.getStudent();

        repository.registerStudent(student);
        //コース情報登録
        for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
            initStudentsCourse(studentCourse, student.getId());
            repository.registerStudentCourse(studentCourse);
        }
        return studentDetail;
    }

    /**
     * 受講生コース情報を登録する際の初期情報を設定する。
     *
     * @param studentCourse 　受講生コース情報
     * @param student       　受講生
     */
    private void initStudentsCourse(StudentCourse studentCourse, String id) {
        LocalDateTime now = LocalDateTime.now();

        studentCourse.setStudentId(id);
        studentCourse.setCourseStartDate(now);
        studentCourse.setCourseEndDate(now.plusYears(1));
    }

    //更新処理

    /**
     * 受講生詳細の更新を行います。受講生と受講生コース情報をそれぞれ更新します。
     *
     * @param studentDetail 　受講生詳細
     */
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        //コース情報登録
        for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
            repository.updateStudentCourse(studentCourse);
        }
    }

    //削除処理
    @Transactional
    public void deleteStudent(StudentDetail studentDetail) {
        repository.deleteStudent(studentDetail.getStudent());
        //コース情報登録
        for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
            repository.deleteStudentCourse(studentCourse);
        }
    }

}
