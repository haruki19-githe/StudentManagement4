package raisetech.StudentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "受講生管理システム",
                description = "受講生（生徒）の情報を登録・検索・更新できるシステムです。",
                version = "1.0",
                license = @License(name = "ライセンス名", url = "https://example.com/satou"),
                contact = @Contact(url = "https://example.com/satou", name = "satou", email = "satou@example.com")))
@Server(
        description = "サーバ説明",
        url = "https://qiita.com/Ikeponias",
        variables = {})


@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }


}