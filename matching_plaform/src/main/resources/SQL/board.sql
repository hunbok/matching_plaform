
SHOW DATABASES;

SHOW tables;
CREATE DATABASE category;

USE category;
USE member;
USE board;
USE reply;

-- 카테고리
DROP TABLE IF EXISTS category;
CREATE TABLE IF NOT EXISTS category(
category_code INTEGER AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(10) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO category(category_code, category_name) VALUES ('1','자취생 레시피');
INSERT INTO category(category_code, category_name) VALUES ('2','건강 레시피');
INSERT INTO category(category_code, category_name) VALUES ('3','꿀팁 게시글');
INSERT INTO category(category_code, category_name) VALUES ('4','공지사항');

-- 회원
DROP TABLE IF EXISTS member;
CREATE TABLE IF NOT EXISTS member(
id VARCHAR(20) PRIMARY KEY,
name VARCHAR(10) NOT NULL,
pass VARCHAR(100) NOT NULL,
email VARCHAR(30) NOT NULL,
mobile VARCHAR(13) NOT NULL,
zipcode  VARCHAR(5) NOT NULL,
address1  VARCHAR(80) NOT NULL,
address2  VARCHAR(60) NOT NULL,
email_get VARCHAR(1) NOT NULL,
reg_date  TIMESTAMP NOT NULL,
nickname VARCHAR(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO member(id, name, pass, email, mobile, zipcode, address1, address2, email_get, reg_date, nickname)
VALUES('bokbok', '이헌복', '$2b$12$Bs0NFD2NPno.KtRQeFPvHesFhOx57WXcyDmgyCaWvivnK.EECrGVu', 'bokbok@naver.com', '010-1111-2222', '08787', '서울시 관악구 남부순환로 1820 (봉천동, 에그엘로우)', '6층', '0', '2022-12-22 11:44:58', '관리자');
INSERT INTO member(id, name, pass, email, mobile, zipcode, address1, address2, email_get, reg_date, nickname)
VALUES('midas', '원 진', '$2b$12$Bs0NFD2NPno.KtRQeFPvHesFhOx57WXcyDmgyCaWvivnK.EECrGVu', 'midas@naver.com', '010-2222-3333', '08802', '서울시 관악구 남부순환로 1820 (봉천동, 에그엘로우)', '14층', '1', '2022-12-23 11:44:58', '미다스');



-- 게시판
DROP TABLE IF EXISTS board;
CREATE TABLE IF NOT EXISTS board(
board_no INTEGER AUTO_INCREMENT PRIMARY KEY,
board_title VARCHAR(50) NOT NULL,
board_content VARCHAR(10000) NOT NULL,
board_reg_date TIMESTAMP NOT NULL,
board_view INTEGER NOT NULL,
category_code INTEGER NOT NULL,
member_id VARCHAR(20) NOT NULL,
board_file1 VARCHAR(100),
board_like INTEGER DEFAULT 0,
board_dislike INTEGER DEFAULT 0,
CONSTRAINT member_id_fk FOREIGN KEY (member_id) REFERENCES member(id),
CONSTRAINT category_code_fk FOREIGN KEY (category_code) REFERENCES category(category_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO board(board_title, board_content, board_reg_date, board_view, category_code, member_id, board_file1)
VALUES('공지 사항 입니다.', '안녕하세요\r\n이번에 어쩌구 저쩌구 해서리...\r\n\r\n\r\r\n\n이렇게 운영계획과 약관을 바꾸게 되었습니다.\r\n\r\n회원님들의 양해를 구하며 사이트 이용해 참고 하시기 바랍니다.\r\n\r\n','2022-12-22 11:44:58', 102, 4, 'bokbok', null);
INSERT INTO board(board_title, board_content, board_reg_date, board_view, category_code, member_id, board_file1)
VALUES('건강 레시피 입니다.', '안녕하세요\r\n이번에 어쩌구 저쩌구 해서리...\r\n\r\n\r\r\n\n이렇게 운영계획과 약관을 바꾸게 되었습니다.\r\n\r\n회원님들의 양해를 구하며 사이트 이용해 참고 하시기 바랍니다.\r\n\r\n','2022-12-22 11:44:58', 102, 2, 'bokbok', null);
INSERT INTO board(board_title, board_content, board_reg_date, board_view, category_code, member_id, board_file1)
VALUES('자취생 레시피 입니다.', '안녕하세요\r\n이번에 어쩌구 저쩌구 해서리...\r\n\r\n\r\r\n\n이렇게 운영계획과 약관을 바꾸게 되었습니다.\r\n\r\n회원님들의 양해를 구하며 사이트 이용해 참고 하시기 바랍니다.\r\n\r\n','2022-12-22 11:44:58', 102, 1, 'bokbok', null);
INSERT INTO board(board_title, board_content, board_reg_date, board_view, category_code, member_id, board_file1)
VALUES('꿀팁 게시판 입니다.', '안녕하세요\r\n이번에 어쩌구 저쩌구 해서리...\r\n\r\n\r\r\n\n이렇게 운영계획과 약관을 바꾸게 되었습니다.\r\n\r\n회원님들의 양해를 구하며 사이트 이용해 참고 하시기 바랍니다.\r\n\r\n','2022-12-22 11:44:58', 102, 3, 'bokbok', null);

-- 댓글
DROP TABLE IF EXISTS reply;
CREATE TABLE IF NOT EXISTS reply(
   reply_no INTEGER AUTO_INCREMENT PRIMARY KEY,
   board_no INTEGER NOT NULL,
   reply_content VARCHAR(500) NOT NULL,
   member_id VARCHAR(20) NOT NULL,
   reply_reg_date TIMESTAMP NOT NULL,
   reply_like INTEGER DEFAULT 0,
   reply_dislike INTEGER DEFAULT 0,
   CONSTRAINT board_no_reply_fk FOREIGN KEY(board_no) REFERENCES board(board_no),
   CONSTRAINT reply_member_id_fk FOREIGN KEY(member_id) REFERENCES member(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO reply(board_no, reply_content, member_id, reply_reg_date) 
VALUES(2, 'ㅎㅇㅎㅇ.', 'midas', '2022-12-23 11:44:58');
INSERT INTO reply(board_no, reply_content, member_id, reply_reg_date) 
VALUES(1, 'ㅎㅇㅎㅇㅎㅇㅎㅇ', 'midas', '2022-12-23 11:44:58');
INSERT INTO reply(board_no, reply_content, member_id, reply_reg_date) 
VALUES(3, '꿀팁 감사합니다.', 'midas', '2022-12-23 11:44:58');
INSERT INTO reply(board_no, reply_content, member_id, reply_reg_date) 
VALUES(4, '고생하십니다.', 'midas', '2022-12-23 11:44:58');

SELECT board_like, board_dislike FROM board WHERE board_no = 12;
SELECT * FROM member;
SELECT * FROM reply;
SELECT * FROM category ORDER BY category_code DESC;
SELECT * FROM board ORDER BY board_no DESC;


