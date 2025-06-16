package raisetech.StudentManagement.converter;

import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentCourseDetail;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCourseConverter {

    public List<StudentCourseDetail> convertStudentCourseDetails(List<StudentCourse> studentCourseList, List<RegistrationStatus> registrationStatusList) {
        List<StudentCourseDetail> studentCourseDetails = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseList) {
            StudentCourseDetail studentCourseDetail = new StudentCourseDetail();
            studentCourseDetail.setStudentCourse(studentCourse);
            List<RegistrationStatus> convertRegistrationStatusList = new ArrayList<>();
            for (RegistrationStatus registrationStatus : registrationStatusList) {
                if (studentCourse.getId().equals(registrationStatus.getStudentCourseId())) {
                    convertRegistrationStatusList.add(registrationStatus);
                }
            }
            studentCourseDetail.setRegistrationStatuseList(convertRegistrationStatusList);
            studentCourseDetails.add(studentCourseDetail);
        }
        return studentCourseDetails;
    }
}
