CREATE TABLE IF NOT EXISTS students
(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
furigana VARCHAR(100) NOT NULL,
nickname VARCHAR(100),
email VARCHAR(100) NOT NULL,
area VARCHAR(100),
age INT,
gender VARCHAR(100),
remark VARCHAR(100),
is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT NOT NULL,
course_name VARCHAR(100) NOT NULL,
course_start_date TIMESTAMP,
course_end_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS registration_status
(
id INT AUTO_INCREMENT PRIMARY KEY,
student_course_id VARCHAR(100) NOT NULL,
registration_status VARCHAR(100)
);