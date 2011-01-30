SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `yourvest` DEFAULT CHARACTER SET utf8 ;
USE `yourvest` ;

-- -----------------------------------------------------
-- Table `yourvest`.`gps_list`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`gps_list` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gps_longitude` VARCHAR(100) NULL DEFAULT NULL ,
  `gps_latitude` VARCHAR(100) NULL DEFAULT NULL ,
  `gps_altitude` VARCHAR(100) NULL DEFAULT NULL ,
  `res_context` VARCHAR(4000) NULL DEFAULT NULL ,
  `create_dt` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`Id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr;


-- -----------------------------------------------------
-- Table `yourvest`.`mem_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`mem_tbl` (
  `mt_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�Ϸù�ȣ' ,
  `mem_id` VARCHAR(100) NOT NULL COMMENT 'ȸ��id' ,
  `mem_nm` VARCHAR(20) NOT NULL COMMENT 'ȸ����' ,
  `passwd` VARCHAR(100) NOT NULL COMMENT '��й�ȣ' ,
  `relation_kind` VARCHAR(20) NOT NULL COMMENT '�������� 01:�ǵ�� �ʿ�,02:�ʵ�� ���ʿ�' ,
  `create_dt` DATETIME NOT NULL COMMENT '������' ,
  `create_no` INT(11) NOT NULL COMMENT '������' ,
  PRIMARY KEY (`mt_no`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = 'ȸ������';


-- -----------------------------------------------------
-- Table `yourvest`.`title_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`title_tbl` (
  `tt_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '������ �Ϸù�ȣ' ,
  `title_name` VARCHAR(400) NOT NULL COMMENT 'Ÿ��Ʋ��' ,
  `title_kind` VARCHAR(20) NOT NULL COMMENT 'Ÿ��Ʋ ���� 00:�Ϲ�' ,
  `branch_conf_yn` VARCHAR(2) NOT NULL COMMENT '����ġ�� ���ɿ���' ,
  `create_dt` DATETIME NOT NULL COMMENT '������' ,
  `create_no` INT(11) NOT NULL COMMENT '������' ,
  PRIMARY KEY (`tt_no`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '�Խù� Ÿ��Ʋ ���';


-- -----------------------------------------------------
-- Table `yourvest`.`context_source_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`context_source_tbl` (
  `cs_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ȸ���Ϸù�ȣ' ,
  `tt_no` INT(11) NOT NULL COMMENT '���� ������ ��ȣ' ,
  `rss2_url` VARCHAR(8000) NULL DEFAULT NULL COMMENT 'rss url' ,
  `source_kind` VARCHAR(20) NULL DEFAULT NULL COMMENT '�ҽ� ���� 00:��ü���� , 01:rss , 02:Ʈ����' ,
  `remot_login_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '�ǵ���ʿ�� �α���id' ,
  `remot_login_pw` VARCHAR(100) NULL DEFAULT NULL COMMENT '�ǵ���ʿ�� �α���pw' ,
  `feed_back_url` VARCHAR(1000) NULL DEFAULT NULL COMMENT '�ǵ��URL' ,
  `cert_key` VARCHAR(1000) NULL DEFAULT NULL COMMENT '�ǵ��� �ʿ��� ����Ű' ,
  `create_dt` DATETIME NOT NULL COMMENT '������' ,
  `create_no` INT(11) NOT NULL COMMENT '������' ,
  PRIMARY KEY (`cs_no`) ,
  INDEX `fk_context_source_tbl_mem_tbl` (`create_no` ASC) ,
  INDEX `fk_context_source_tbl_title_tbl1` (`tt_no` ASC) ,
  CONSTRAINT `fk_context_source_tbl_mem_tbl`
    FOREIGN KEY (`create_no` )
    REFERENCES `yourvest`.`mem_tbl` (`mt_no` )
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_context_source_tbl_title_tbl1`
    FOREIGN KEY (`tt_no` )
    REFERENCES `yourvest`.`title_tbl` (`tt_no` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '�۳��뿡 �ڵ����� ��ϵ� ������ ���� rss,Ʈ��������';


-- -----------------------------------------------------
-- Table `yourvest`.`context_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`context_tbl` (
  `ct_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�۵���Ϸù�ȣ' ,
  `tt_no` INT(11) NOT NULL COMMENT 'title_tbl �Ϸù�ȣ' ,
  `cs_no` INT(11) NOT NULL COMMENT 'context_source_tbl �Ϸù�ȣ' ,
  `context_title` VARCHAR(400) NOT NULL ,
  `source_kind` VARCHAR(20) NOT NULL COMMENT '�ҽ� ���� 00:��ü���� , 01:rss , 02:Ʈ����' ,
  `context_desc` VARCHAR(8000) NOT NULL COMMENT '����' ,
  `create_ip` VARCHAR(20) NOT NULL COMMENT '������IP' ,
  `create_dt` DATETIME NOT NULL COMMENT '������' ,
  `create_no` INT(11) NOT NULL COMMENT '������' ,
  PRIMARY KEY (`ct_no`) ,
  INDEX `fk_context_tbl_title_tbl1` (`tt_no` ASC) ,
  CONSTRAINT `fk_context_tbl_title_tbl1`
    FOREIGN KEY (`tt_no` )
    REFERENCES `yourvest`.`title_tbl` (`tt_no` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '�Խù��� ��ϵ� �� ����';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
