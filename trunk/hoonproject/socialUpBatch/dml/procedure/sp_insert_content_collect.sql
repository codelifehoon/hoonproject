DELIMITER $$

USE `yourvest`$$

DROP PROCEDURE IF EXISTS `sp_insert_content_collect`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_content_collect`()
    COMMENT '������ :2010.12.13 \n	������ :������\n	����   : content_source_tbl�� �̿��ؼ� cont'
BEGIN
	INSERT INTO content_collect (cs_no,proc_step,create_dt)
	SELECT cs_no,'00',SYSDATE() FROM content_source_tbl WHERE use_yn = 'Y' AND next_prod_dt <= SYSDATE()  AND read_fail_count <= 10;
	
    END$$

DELIMITER ;