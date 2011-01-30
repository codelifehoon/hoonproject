

-- Rss 묶음별 글제목 테이블
CREATE TABLE title_tbl
(
  tt_no INTEGER PRIMARY KEY  		NOT NULL COMMENT '글제목 일련번호'  AUTO_INCREMENT , 
  title_name   	  VARCHAR(400) 		NOT NULL COMMENT '타이틀명',   
  title_kind	  VARCHAR(20) 		NOT NULL COMMENT '타이틀 종류 00:일반',
  branch_conf_yn  VARCHAR(2) 		NOT NULL COMMENT '가지치기 가능여부',
  create_dt       DATETIME 		NOT NULL COMMENT '생성일',
  create_no       INTEGER		NOT NULL COMMENT '생성자'
) DEFAULT CHARSET=euckr COMMENT='게시물 타이틀 목록';


-- 글제목별 등록된 글내용
CREATE TABLE context_tbl
(
  ct_no INTEGER 	PRIMARY KEY  	NOT NULL 	COMMENT '글등록일련번호'  AUTO_INCREMENT , 
  tt_no INTEGER  	NOT NULL  	NOT NULL 	COMMENT 'title_tbl 일련번호' , 
  cs_no INTEGER  	NOT NULL  	NOT NULL 	COMMENT 'context_source_tbl 일련번호' , 
  context_title VARCHAR(400) 		NOT NULL COMMENT '',   context_source_tbl
  source_kind	VARCHAR(20) 		NOT NULL COMMENT '소스 종류 00:자체생성 , 01:rss , 02:트위터'  ,
  context_desc	VARCHAR(8000) 		NOT NULL COMMENT '내용',
  create_ip     VARCHAR(20) 		NOT NULL COMMENT '생성자IP',
  create_dt     DATETIME 		NOT NULL COMMENT '생성일',
  create_no     INTEGER			NOT NULL COMMENT '생성자'
) DEFAULT CHARSET=euckr COMMENT='게시물별 등록된 글 내용';



CREATE TABLE context_source_tbl
(
  cs_no 	INTEGER PRIMARY KEY  	NOT NULL COMMENT '회원일련번호'  AUTO_INCREMENT , 
  tt_no 	INTEGER NOT NULL  	NOT NULL COMMENT '관련 글제목 번호'  , 
  rss2_url	VARCHAR(8000)		COMMENT 'rss url'  , 	
  source_kind	VARCHAR(20) 		COMMENT '소스 종류 00:자체생성 , 01:rss , 02:트위터',
  remot_login_id  VARCHAR(100)		COMMENT '피드백필요시 로그인id'  ,
  remot_login_pw  VARCHAR(100)		COMMENT '피드백필요시 로그인pw'  ,
  feed_back_url   VARCHAR(1000)		COMMENT '피드백URL'  		 ,
  cert_key        VARCHAR(1000)		COMMENT '피드백시 필요한 인증키' ,
  create_dt     DATETIME 		NOT NULL COMMENT '생성일',
  create_no     INTEGER			NOT NULL COMMENT '생성자'
) DEFAULT CHARSET=euckr COMMENT='글내용에 자동으로 등록될 컨텐츠 정보 rss,트위터정보';



-- 글제목에 등록될 소스정보(Rss,트위터정보등)
CREATE TABLE mem_tbl
(
  mt_no INTEGER PRIMARY KEY  	NOT NULL		COMMENT '일련번호'  AUTO_INCREMENT , 
  mem_id 	  VARCHAR(100)	NOT NULL		COMMENT '회원id'  , 	
  mem_nm	  VARCHAR(20)	NOT NULL		COMMENT '회원명'  , 	
  passwd	  VARCHAR(100) 	NOT NULL		COMMENT '비밀번호',
  relation_kind   VARCHAR(20)	NOT NULL		COMMENT '관계종류 01:피드백 필요,02:필드백 불필요',
  create_dt       DATETIME 	NOT NULL		COMMENT '생성일',
  create_no       INTEGER	NOT NULL		COMMENT '생성자'
) DEFAULT CHARSET=euckr COMMENT='회원정보';


DESC mem_tbl;


-- * 가지치기 할때 현재 상태의 원소스의 변화에 따라 가지치기한것들이 변화할것이냐, 하지않을것이냐?

 




/*
desc res_list;

CREATE TABLE res_list
(
  Id integer PRIMARY KEY  AUTO_INCREMENT,
  file_name varchar(1000) NOT NULL default '',
  file_size integer default NULL,  
  file_type varchar(2),
  upload_yn varchar(1),  
  upload_sink_key varchar(1000),  
  upload_date     varchar(2),  
  gps_longitude   varchar(100),  
  gps_latitude    varchar(100),    
  gps_altitude    varchar(100),
  res_context     varchar(4000),                 
  create_dt       varchar(20)
);

*/