/*
SELECT * FROM content_source_tbl
;
SELECT * FROM code_master;
SELECT * FROM code_detail WHERE grp_cd = 'CS008';

	INSERT INTO content_collect (cs_no,proc_step,create_dt)
	SELECT cs_no,'00',SYSDATE() FROM content_source_tbl WHERE use_yn = 'Y' AND next_prod_dt <= SYSDATE()  AND read_fail_count <= 10;




;
*/


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



INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS007','컨텐츠 타이틀 가입상태','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS007','00','가입신청','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS007','01','가입완료','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS007','02','가입보류','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS007','04','가입거부','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS007','99','자진탈퇴','Y');


INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS008','컨텐츠소속등급','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS008','00','소유자','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS008','01','참여자','Y');



INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS009','컨텐츠타이블참여방법종류','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS009','01','참여자유','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS009','02','비밀번호방식','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS009','03','참여신청확인','Y');


INSERT  INTO  code_master(grp_cd,grp_nm,use_yn)
VALUE('CS010','이미지종류','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS010','01','원본이미지경로','Y');

INSERT  INTO  code_detail(grp_cd,detail_cd,detail_nm,use_yn)
VALUE('CS010','02','섬네일이미지경로','Y');

