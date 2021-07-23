DROP TABLE IF EXISTS groups, students, teachers, subjects, lessons;

CREATE TABLE groups 
(
    group_id SERIAL PRIMARY KEY, 
    group_name VARCHAR(50) UNIQUE
);

CREATE TABLE students 
(
    student_id SERIAL PRIMARY KEY, 
    group_id INTEGER, 
    first_name VARCHAR(50), 
    last_name VARCHAR(50),
    FOREIGN KEY (group_id) REFERENCES groups (group_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE teachers 
(
    teacher_id SERIAL PRIMARY KEY, 
    teacher_first_name VARCHAR(50),
    teacher_last_name VARCHAR(50) 
);

CREATE TABLE subjects 
(
    subject_id SERIAL PRIMARY KEY, 
    subject_name VARCHAR(50),
    subject_desc VARCHAR(50), 
    teacher_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE lessons 
(
    lesson_id SERIAL PRIMARY KEY, 
    subject_id INTEGER,
    group_id INTEGER,
    lesson_number INTEGER,
    begin_time TIMESTAMP,
    FOREIGN KEY (subject_id) REFERENCES subjects (subject_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (group_id) ON UPDATE CASCADE ON DELETE CASCADE
);
