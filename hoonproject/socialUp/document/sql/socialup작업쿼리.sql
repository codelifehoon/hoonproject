#mysql> grant all privileges on 데이타베이스명.*(*.*) to 계정명@IP identified by '비밀번호';
GRANT ALL PRIVILEGES ON goreee.* TO root@192.168.0.2 IDENTIFIED BY '1234';
SELECT * FROM USER;
 FLUSH PRIVILEGES;
 UPDATE USER
      SET select_priv = 'Y', 
          insert_priv = 'Y', 
          update_priv = 'Y', 
          delete_priv = 'Y', 
          create_priv = 'Y', 
          drop_priv = 'Y', 
          reload_priv = 'Y', 
          shutdown_priv = 'Y', 
          process_priv = 'Y', 
          file_priv = 'Y', 
          grant_priv = 'Y', 
          references_priv = 'Y', 
          index_priv = 'Y', 
          alter_priv = 'Y', 
          show_db_priv = 'Y', 
          super_priv = 'Y', 
          create_tmp_table_priv = 'Y', 
          lock_tables_priv = 'Y', 
          execute_priv = 'Y', 
          repl_slave_priv = 'Y', 
          repl_client_priv = 'Y', 
          create_view_priv = 'Y', 
          show_view_priv = 'Y', 
          create_routine_priv = 'Y', 
          alter_routine_priv = 'Y', 
          create_user_priv = 'Y'
    WHERE HOST = '192.168.0.2 ' AND USER = 'root';
    
;

SHOW VARIABLES LIKE "%ft_min_word_len%";
SHOW VARIABLES LIKE "%max_allowed_packet%";
SELECT * FROM mem_tbl;
SELECT * FROM content_title_tbl ORDER  BY tt_no;
SELECT * FROM content_source_tbl;
SELECT * FROM content_branch  WHERE tt_no =2 ORDER BY branch_no;
SELECT * FROM content_join_mem;
SELECT* FROM content_collect ORDER BY coll_no DESC;
SELECT* FROM  code_detail WHERE grp_cd = 'CS006';
SELECT * FROM content_dtl_tbl ORDER BY cd_no DESC;
SELECT * FROM content_dtl_img;
SELECT * FROM  upload_files;
DELETE  FROM  upload_files;

SELECT * FROM content_dtl_tbl LIMIT 10,10
DESC upload_files
;
 SELECT /* ContentDtlComment.xml selectUploadFilesList 2010.12.16*/ SQL_CALC_FOUND_ROWS uf_id 
, cd_no , file_name , file_path , file_kind , file_size , use_yn , create_dt , create_no FROM 
upload_files uf WHERE uf.create_no = '1' AND uf.use_yn = 'Y' ORDER BY uf.uf_id DESC LIMIT 0 
, 10 
;

;
REPAIR TABLE content_dtl_tbl;
;
;
SET NAMES UTF8
;
SET NAMES EUCKR
;
ixfull_ix1_content_dtl_tbl


SELECT  cdt.*  FROM content_dtl_tbl cdt WHERE MATCH(content_title,cdt.content_desc) AGAINST('통큰*')

;
SELECT cdt.*  FROM content_dtl_tbl cdt WHERE MATCH(cdt.content_desc) AGAINST('요리*')
;
SELECT cdt.*  FROM content_dtl_tbl cdt WHERE MATCH(cdt.content_desc) AGAINST('네이버*')


;
SELECT /*isContentDtlView 2010.12.25 */ 2 AS flag FROM content_title_tbl ctt WHERE ctt.tt_no 
= '2' AND ctt.order_mem_open_yn = 'Y' LIMIT 1 

UNION ALL SELECT /*isContentDtlView 2010.12.25 
*/ 3 AS flag FROM content_join_mem cjm WHERE cjm.tt_no = '2' AND cjm.mt_no = '1' AND cjm.stat 
= '01' LIMIT 1 

UNION ALL SELECT /*isContentDtlView 2010.12.25 */ 4 AS flag FROM content_branch 
cb , content_dtl_tbl cdt WHERE cb.belong_tt_no = '2' AND cb.mt_no = '1' LIMIT 1 
;
SELECT/* selectContentBranchSelfJoinList 2011.01.09 */
  cb.branch_no,
  cb.tt_no,
  cb.belong_tt_no,
  cb.org_branch_no,
  cb.mt_no,
  cb.use_yn,
  cb.create_dt,
  cb.create_no,
  cb.update_dt,
  cb.update_no,
  ctt.title_name,
  ctt.title_kind
FROM content_branch cb,
  content_title_tbl ctt
WHERE cb.use_yn = 'Y'
    AND cb.tt_no = '2'
    AND cb.belong_tt_no = ctt.tt_no
    AND ctt.mt_no = '1'
    AND ctt.use_yn = 'Y'
;
-- 1. 나의 컨텐츠 타이틀을 다른 컨텐츠 타이틀에 추가할때는  나의 컨텐츠 타이틀에 소속된 모든 브랜치가 이동해야됨.
-- 2. 이동을 원하는 브랜치에 나의 컨텐츠가 기존에 등록되어 있는지 확인하고 기 등록 되어 있다면 등록하지 읺는다.
SELECT * FROM content_branch cb WHERE cb.tt_no = 

--  1 삭제시에 삭제 대상 컨텐츠타이틀에  삭제를 원하는 나의 컨텐츠타이틀의 branch_no 와 같은 값이 존재하면 삭제한다.

-- 추가해야될 대상
SELECT * FROM content_branch cb WHERE cb.tt_no IN ( #{orgBranchTtNo} )
AND cb.use_yn  = 'Y'
AND NOT EXISTS (SELECT * FROM content_branch cb WHERE cb.tt_no =  AND cb.mt_no =  AND cb.use_yn ='Y')

-- 삭제대상(추가대상에 없는데 기존에 존재하는놈)
UPDATE content_branch cb SET 
				 cb.use_yn 	  = 'N'
				,cb.update_no = #{create_no}
				,cb.update_dt = DATE_FORMAT(#{create_dt},'%Y%m%d%H%i%s')
				
			WHERE cb.tt_no 	 = 	#{orgBranchTtNos}
			AND cb.mt_no =  	#{mt_no}
			AND cb.org_branch_no IN (SELECT branch_no FROM content_branch cb2 WHERE cb2.tt_no = #{tt_no})
			AND cb.use_yn = 'Y'
			
;
UPDATE/* ContentBranch.xml deleteContentBranchSelfJoins 2011.01.16 */ 
content_branch 
SET use_yn = 'N',
  update_no = '1',
  update_dt = DATE_FORMAT('20110118233435','%Y%m%d%H%i%s')
WHERE tt_no = '2'
    AND org_branch_no  = (SELECT  a.branch_no FROM
				( SELECT
					      cb2.branch_no
					    FROM content_branch cb2
					    WHERE cb2.tt_no IN('5')
						AND cb2.mt_no = '1'
                                ) a
                        )
AND use_yn = 'Y'
;


