package raisetech.StudentManagement.converter;

import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentCourseDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentCourseConverter {
    /**
     * 受講生コース情報に紐づく受講生の申込状況をマッピングする。
     * 受講生の申込状況は受講生コース情報に対して複数存在するのでループを回して受講生コース詳細情報を組み立てる。
     *
     * @param studentCourseList      　受講生コース一覧
     * @param registrationStatusList 　受講生のコースの申込状況一覧
     * @return　受講生コース詳細情報のリスト
     */
    public List<StudentCourseDetail> convertStudentCourseDetails(List<StudentCourse> studentCourseList,
                                                                 List<RegistrationStatus> registrationStatusList) {
        List<StudentCourseDetail> studentCourseDetails = new ArrayList<>();
        studentCourseList.forEach(studentCourse -> {
            StudentCourseDetail studentCourseDetail = new StudentCourseDetail();
            studentCourseDetail.setStudentCourse(studentCourse);
            List<RegistrationStatus> convertRegistrationStatusList = registrationStatusList.stream()
                    .filter(registrationStatus -> studentCourse.getId()
                            .equals(registrationStatus.getStudentCourseId())).collect(Collectors.toList());
            studentCourseDetail.setRegistrationStatusList(convertRegistrationStatusList);
            studentCourseDetails.add(studentCourseDetail);
        });
        return studentCourseDetails;
    }
}
