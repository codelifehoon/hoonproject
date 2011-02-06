DELIMITER $$

USE `yourvest`$$

DROP PROCEDURE IF EXISTS `sp_insert_content_collect`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_content_collect`()
    COMMENT '생성일 :2010.12.13 \n	생성자 :장재훈\n	내용   : content_source_tbl를 이용해서 cont'
BEGIN
	INSERT INTO content_collect (cs_no,proc_step,create_dt)
	SELECT cs_no,'00',SYSDATE() FROM content_source_tbl WHERE use_yn = 'Y' AND next_prod_dt <= SYSDATE()  AND read_fail_count <= 10;
	
    END$$

DELIMITER ;