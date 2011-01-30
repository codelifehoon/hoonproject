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
  `mt_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호' ,
  `mem_id` VARCHAR(100) NOT NULL COMMENT '회원id' ,
  `mem_nm` VARCHAR(20) NOT NULL COMMENT '회원명' ,
  `passwd` VARCHAR(100) NOT NULL COMMENT '비밀번호' ,
  `relation_kind` VARCHAR(20) NOT NULL COMMENT '관계종류 01:피드백 필요,02:필드백 불필요' ,
  `create_dt` DATETIME NOT NULL COMMENT '생성일' ,
  `create_no` INT(11) NOT NULL COMMENT '생성자' ,
  PRIMARY KEY (`mt_no`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '회원정보';


-- -----------------------------------------------------
-- Table `yourvest`.`title_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`title_tbl` (
  `tt_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '글제목 일련번호' ,
  `title_name` VARCHAR(400) NOT NULL COMMENT '타이틀명' ,
  `title_kind` VARCHAR(20) NOT NULL COMMENT '타이틀 종류 00:일반' ,
  `branch_conf_yn` VARCHAR(2) NOT NULL COMMENT '가지치기 가능여부' ,
  `create_dt` DATETIME NOT NULL COMMENT '생성일' ,
  `create_no` INT(11) NOT NULL COMMENT '생성자' ,
  PRIMARY KEY (`tt_no`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '게시물 타이틀 목록';


-- -----------------------------------------------------
-- Table `yourvest`.`context_source_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`context_source_tbl` (
  `cs_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '회원일련번호' ,
  `tt_no` INT(11) NOT NULL COMMENT '관련 글제목 번호' ,
  `rss2_url` VARCHAR(8000) NULL DEFAULT NULL COMMENT 'rss url' ,
  `source_kind` VARCHAR(20) NULL DEFAULT NULL COMMENT '소스 종류 00:자체생성 , 01:rss , 02:트위터' ,
  `remot_login_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '피드백필요시 로그인id' ,
  `remot_login_pw` VARCHAR(100) NULL DEFAULT NULL COMMENT '피드백필요시 로그인pw' ,
  `feed_back_url` VARCHAR(1000) NULL DEFAULT NULL COMMENT '피드백URL' ,
  `cert_key` VARCHAR(1000) NULL DEFAULT NULL COMMENT '피드백시 필요한 인증키' ,
  `create_dt` DATETIME NOT NULL COMMENT '생성일' ,
  `create_no` INT(11) NOT NULL COMMENT '생성자' ,
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
COMMENT = '글내용에 자동으로 등록될 컨텐츠 정보 rss,트위터정보';


-- -----------------------------------------------------
-- Table `yourvest`.`context_tbl`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yourvest`.`context_tbl` (
  `ct_no` INT(11) NOT NULL AUTO_INCREMENT COMMENT '글등록일련번호' ,
  `tt_no` INT(11) NOT NULL COMMENT 'title_tbl 일련번호' ,
  `cs_no` INT(11) NOT NULL COMMENT 'context_source_tbl 일련번호' ,
  `context_title` VARCHAR(400) NOT NULL ,
  `source_kind` VARCHAR(20) NOT NULL COMMENT '소스 종류 00:자체생성 , 01:rss , 02:트위터' ,
  `context_desc` VARCHAR(8000) NOT NULL COMMENT '내용' ,
  `create_ip` VARCHAR(20) NOT NULL COMMENT '생성자IP' ,
  `create_dt` DATETIME NOT NULL COMMENT '생성일' ,
  `create_no` INT(11) NOT NULL COMMENT '생성자' ,
  PRIMARY KEY (`ct_no`) ,
  INDEX `fk_context_tbl_title_tbl1` (`tt_no` ASC) ,
  CONSTRAINT `fk_context_tbl_title_tbl1`
    FOREIGN KEY (`tt_no` )
    REFERENCES `yourvest`.`title_tbl` (`tt_no` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = euckr
COMMENT = '게시물별 등록된 글 내용';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
