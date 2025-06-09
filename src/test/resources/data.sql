INSERT INTO students (name,furigana,nickname,email,area,age,gender,remark,is_deleted)VALUES
('山田太朗','ヤマダタロウ','タロ','taro@example.com','東京',25,'男性','',false),
('鈴木一郎','スズキイチロウ','イチ','ichiro@example.com','仙台',35,'女性','',false),
('田中花子','タナカハナコ','ハナ','hanako@example.com','神奈川',45,'男性','',false),
('佐藤良子','サトウリョウコ','リョウ','ryoko@example.com','山梨',55,'女性','',false),
('伊藤悠','イトウハルカ','ハル','haruka@example.com','静岡',15,'その他','',false);

INSERT INTO students_courses(student_id,course_name,course_start_date,course_end_date)VALUES
(1,'Javaコース','2024-12-01','2024-12-31'),
(1,'AWSコース','2023-05-01','2023-08-01'),
(2,'デザインコース','2023-06-01','2023-09-01'),
(2,'Pythonコース','2023-07-01','2023-10-01'),
(3,'UI/UXコース','2023-08-01','2023-11-01'),
(3,'Reactコース','2023-09-01','2023-12-01'),
(1,'インフラコース','2023-10-01','2024-01-01'),
(4,'セキュリティコース','2023-11-01','2024-02-01'),
(4,'データ分析コース','2023-12-01','2024-03-01'),
(5,'AI基礎コース','2024-01-01','2024-04-01');
