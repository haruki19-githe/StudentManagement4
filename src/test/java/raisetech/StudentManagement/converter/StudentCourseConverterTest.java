package raisetech.StudentManagement.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.data.RegistrationStatus;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentCourseDetail;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentCourseConverterTest {
    @InjectMocks
    private StudentCourseConverter sut;

    @Mock
    private StudentCourseConverter studentCourseConverter;

    @Test
    void 複数のregistrationStatusがあった上で対象のものだけがconverterで紐づくことを確認() {
        List<StudentCourseDetail> studentCourseDetails = new ArrayList<>();
        StudentCourse studentCourse = createStudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);
        StudentCourseDetail studentCourseDetail = new StudentCourseDetail();
        studentCourseDetail.setStudentCourse(studentCourse);

        List<RegistrationStatus> convertRegistrationStatusList = new ArrayList<>();
        RegistrationStatus registrationStatus = createRegistrationStatus();
        List<RegistrationStatus> registrationStatusList = List.of(registrationStatus);

        if (studentCourse.getId()
                .equals(registrationStatus.getStudentCourseId())) {
            convertRegistrationStatusList.add(registrationStatus);
        }
        studentCourseDetail.setRegistrationStatusList(convertRegistrationStatusList);
        studentCourseDetails.add(studentCourseDetail);

        List<StudentCourseDetail> actual = sut.convertStudentCourseDetails(studentCourseList, registrationStatusList);

        Assertions.assertEquals(studentCourseDetails, actual);

    }

    private static RegistrationStatus createRegistrationStatus() {
        RegistrationStatus registrationStatus = new RegistrationStatus();
        registrationStatus.setId("1");
        registrationStatus.setStudentCourseId("1");
        registrationStatus.setRegistrationStatus("受講中");
        return registrationStatus;
    }

    private static StudentCourse createStudentCourse() {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId("1");
        studentCourse.setStudentId("1");
        studentCourse.setCourseName("Javaコース");
        return studentCourse;
    }


}

