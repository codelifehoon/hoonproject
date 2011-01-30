
SELECT * FROM content_source_tbl
;
SELECT * FROM code_master;
SELECT * FROM code_detail;

DROP PROCEDURE IF EXISTS sp_create_content_collect$$
CREATE PROCEDURE sp_insert_content_collect()
BEGIN 
/*
	생성일 :2010.12.13 
	생성자 :장재훈
	내용   : content_source_tbl를 이용해서 content_collect 를 생성한다.
*/

 
	INSERT INTO content_collect (cs_no,proc_step,create_dt)
	SELECT cs_no,'00',SYSDATE() FROM content_source_tbl WHERE use_yn = 'Y' AND next_prod_dt <= SYSDATE()  AND read_fail_count <= 10;

END $$


;
-- *********
INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS001','출처종류','Y');


INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS001','00','자체생성','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS001','01','RSS','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS001','01','트위터','Y');



-- *********
INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS002','출처상세종류','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS002','01','네이버블로그','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS002','02','다음블로그','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS002','03','싸이블로그','Y');

-- *********
INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS003','등록자 구분','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS003','01','본인','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS003','02','자신의 친구를 초청','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS003','03','자신의 관심컨텐츠','Y');


-- *********
INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS004','컨텐츠승인상태','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS004','01','승인대기','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS004','02','승인','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS004','03','승인거부','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS004','04','삭제','Y');


-- *********
INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS005','타이틀종류','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS005','01','기타','Y');



INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS006','수집 컨텐츠 처리상태','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS006','00','수집대상','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS006','01','수집완료','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS006','02','분석완료(등록완료)','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS006','04','수집실패','Y');



