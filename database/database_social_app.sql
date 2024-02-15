DROP DATABASE IF EXISTS social_app;
CREATE DATABASE social_app;
USE social_app;

CREATE TABLE student_info (
  student_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  student_name TEXT,
  student_password TEXT,
  student_email TEXT,
  student_phone_number TEXT
);

CREATE TABLE club_info (
  club_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  club_name TEXT,
  club_description TEXT,
  club_president INTEGER,
  FOREIGN KEY (club_president) REFERENCES student_info(student_ID)
);

CREATE TABLE club_members (
  club_ID INTEGER,
  membership_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  student_id INTEGER,
  join_date DATETIME,
  status TEXT,
  FOREIGN KEY (club_ID) REFERENCES club_info(club_ID),
  FOREIGN KEY (student_id) REFERENCES student_info(student_ID)
);

CREATE TABLE message_info(
    message_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_ID INTEGER,
    message_text TEXT,
    club_ID INTEGER,
    timestamp TEXT,
    FOREIGN KEY (student_ID) REFERENCES student_info(student_ID),
    FOREIGN KEY (club_ID) REFERENCES club_info(club_ID)
);

-- Insert fake data into student_info table
INSERT INTO student_info (student_name, student_password, student_email, student_phone_number)
VALUES 
    ('John Doe', 'password123', 'john.doe@example.com', '123-456-7890'),
    ('Jane Smith', 'pass1234', 'jane.smith@example.com', '987-654-3210'),
    ('Mike Johnson', 'abc123', 'mike.johnson@example.com', '456-789-0123');

-- Insert fake data into club_info table
INSERT INTO club_info (club_name, club_description, club_president)
VALUES 
    ('Chess Club', 'A club for chess enthusiasts', 1),
    ('Photography Club', 'For those who love capturing moments', 2),
    ('Debate Club', 'Engage in meaningful discussions', 3);

-- Insert fake data into club_members table
INSERT INTO club_members (club_ID, student_id, join_date, status)
VALUES 
    (1, 2, '2023-01-15', 'active'),
    (1, 3, '2023-02-20', 'active'),
    (2, 1, '2023-03-10', 'active'),
    (2, 3, '2023-04-05', 'active'),
    (3, 1, '2023-05-20', 'active'),
    (3, 2, '2023-06-15', 'active');

-- Insert fake data into message_info table
INSERT INTO message_info (student_ID, message_text, club_ID, timestamp)
VALUES 
    (1, 'Let\'s meet this Friday for a game!', 1, '2023-01-30 08:00:00'),
    (2, 'Don\'t forget about the photography exhibition!', 2, '2023-02-10 10:30:00'),
    (3, 'Debate topic for this week: Climate Change', 3, '2023-03-05 14:45:00');
