/*
SQLyog Community v8.7 RC
MySQL - 5.0.77-community-nt : Database - yourvest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yourvest` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yourvest`;

/*Table structure for table `code_detail` */

DROP TABLE IF EXISTS `code_detail`;

CREATE TABLE `code_detail` (
  `cd_no` int(11) NOT NULL auto_increment,
  `grp_cd` varchar(20) NOT NULL,
  `detail_cd` varchar(20) NOT NULL,
  `detail_nm` varchar(100) NOT NULL,
  `priority` int(11) NOT NULL COMMENT '조회우선순위',
  `use_yn` varchar(2) NOT NULL,
  PRIMARY KEY  (`cd_no`),
  KEY `fk_code_detail_code_master` (`grp_cd`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=euckr COMMENT='상세코드를 정의한 테이블';

/*Data for the table `code_detail` */

insert  into `code_detail`(`cd_no`,`grp_cd`,`detail_cd`,`detail_nm`,`priority`,`use_yn`) values (1,'CS001','00','자체생성',0,'Y'),(2,'CS001','01','RSS',0,'Y'),(3,'CS001','01','트위터',0,'Y'),(4,'CS002','01','네이버블로그',0,'Y'),(5,'CS002','02','다음블로그',0,'Y'),(6,'CS002','03','싸이블로그',0,'Y'),(7,'CS003','01','본인',0,'Y'),(8,'CS003','02','자신의 친구를 초청',0,'Y'),(9,'CS004','01','승인대기',0,'Y'),(10,'CS004','02','승인',0,'Y'),(11,'CS004','03','승인거부',0,'Y'),(12,'CS004','04','삭제',0,'Y'),(13,'CS005','00','일반',0,'Y'),(14,'CS005','01','기타',0,'Y'),(15,'CS003','03','자신의 관심컨텐츠',0,'Y');

/*Table structure for table `code_master` */

DROP TABLE IF EXISTS `code_master`;

CREATE TABLE `code_master` (
  `cm_no` int(11) NOT NULL auto_increment COMMENT '일련번호\n',
  `grp_cd` varchar(20) NOT NULL,
  `grp_nm` varchar(100) NOT NULL,
  `use_yn` varchar(2) NOT NULL,
  PRIMARY KEY  (`cm_no`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=euckr COMMENT='각종 코드타이틀을 정의한 테이블';

/*Data for the table `code_master` */

insert  into `code_master`(`cm_no`,`grp_cd`,`grp_nm`,`use_yn`) values (1,'CS001','출처종류','Y'),(2,'CS002','출처상세종류','Y'),(3,'CS003','등록자 구분','Y'),(4,'CS004','컨텐츠승인상태','Y'),(5,'CS005','타이틀종류','Y');

/*Table structure for table `content_source_tbl` */

DROP TABLE IF EXISTS `content_source_tbl`;

CREATE TABLE `content_source_tbl` (
  `cs_no` int(11) NOT NULL auto_increment,
  `tt_no` int(11) NOT NULL COMMENT '관련 글제목 번호',
  `mt_no` int(11) NOT NULL COMMENT '회원번호',
  `rss2_url` varchar(8000) default NULL COMMENT 'rss url',
  `source_kind` varchar(20) default NULL COMMENT '소스 종류 00:자체생성 \n, 01:rss\n, 02:트위터\n[CS001]',
  `source_dtl_kind` varchar(5) default NULL COMMENT '소스의 자세한출처\n네이버\n다음\n등등.\n[CS002]',
  `source_owner_kind` varchar(20) NOT NULL COMMENT '해당  등록자 구분\n01  :  본인\n02  :  자신의 친구를 초청\n03  :  자신의 관심컨텐츠\n[CS003]',
  `source_login_id` varchar(20) default NULL COMMENT 'id로 컨텐츠 등록요청시\n사용하는 id\n',
  `reg_stat` varchar(45) default NULL COMMENT '등록된 컨텐츠의 활성화여부\n01 : 승인대기\n02 : 승인\n03:  승인거부\n04: 삭제\nCS004',
  `use_yn` varchar(2) default NULL COMMENT '사용여부',
  `create_dt` datetime NOT NULL COMMENT '생성일',
  `create_no` int(11) NOT NULL COMMENT '생성자',
  `update_dt` datetime default NULL,
  `update_no` int(11) default NULL,
  PRIMARY KEY  (`cs_no`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=euckr COMMENT='컨텐츠 소스  정보\n\n글고리 타이틀에 소속되어\n자동으로 등록될 컨텐츠 정보 rss,트위터정보';

/*Data for the table `content_source_tbl` */

insert  into `content_source_tbl`(`cs_no`,`tt_no`,`mt_no`,`rss2_url`,`source_kind`,`source_dtl_kind`,`source_owner_kind`,`source_login_id`,`reg_stat`,`use_yn`,`create_dt`,`create_no`,`update_dt`,`update_no`) values (6,7,5,'나의 블로그/RSS 인터넷주소(URL)','01','01','01','로그인ID','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(7,7,5,'관심1','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(8,7,5,'관심2','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(9,7,5,'관심3','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(10,7,5,'관심4','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(11,7,5,'관심5','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(12,7,5,'관심6','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL),(13,7,5,'관심7','01','','03','','02',NULL,'2010-12-04 23:55:36',5,NULL,NULL);

/*Table structure for table `content_tbl` */

DROP TABLE IF EXISTS `content_tbl`;

CREATE TABLE `content_tbl` (
  `ct_no` int(11) NOT NULL auto_increment,
  `tt_no` int(11) NOT NULL COMMENT 'title_tbl 일련번호',
  `cs_no` int(11) NOT NULL COMMENT 'context_source_tbl 일련번호',
  `mt_no` int(11) default NULL COMMENT '회원번호',
  `content_title` varchar(400) NOT NULL,
  `source_kind` varchar(20) NOT NULL COMMENT '소스 종류 00:자체생성 , 01:rss , 02:트위터',
  `content_desc` varchar(8000) NOT NULL COMMENT '내용',
  `create_ip` varchar(20) NOT NULL COMMENT '생성자IP',
  `create_dt` datetime NOT NULL COMMENT '생성일',
  `create_no` int(11) NOT NULL COMMENT '생성자',
  `update_dt` datetime default NULL,
  `update_no` int(11) default NULL,
  PRIMARY KEY  (`ct_no`)
) ENGINE=MyISAM DEFAULT CHARSET=euckr COMMENT='게시물 정보\n게시물  별 등록된 글 내용';

/*Data for the table `content_tbl` */

/*Table structure for table `content_title_tbl` */

DROP TABLE IF EXISTS `content_title_tbl`;

CREATE TABLE `content_title_tbl` (
  `tt_no` int(11) NOT NULL auto_increment COMMENT '글제목 일련번호',
  `title_name` varchar(400) NOT NULL COMMENT '타이틀명',
  `title_kind` varchar(20) NOT NULL COMMENT '타이틀 종류 00:일반',
  `branch_conf_yn` varchar(2) NOT NULL COMMENT '가지치기 가능여부\n글고리 가지치기 가능여부(다른사용자가 가져가는것)\n',
  `create_dt` datetime NOT NULL COMMENT '생성일',
  `create_no` int(11) NOT NULL COMMENT '생성자',
  `update_dt` datetime default NULL,
  `update_no` int(11) default NULL,
  `mt_no` int(11) NOT NULL COMMENT '회원번호',
  `order_mem_open_yn` varchar(2) default NULL COMMENT '글고리 타사용자공개여부',
  `order_mem_join_yn` varchar(2) default NULL COMMENT '글고리 타사용자참여가능여부',
  `order_mem_join_passwd` varchar(100) default NULL COMMENT '글고리 참여가능시 참여비밀번호',
  PRIMARY KEY  (`tt_no`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=euckr COMMENT='게시물 타이틀 목록';

/*Data for the table `content_title_tbl` */

insert  into `content_title_tbl`(`tt_no`,`title_name`,`title_kind`,`branch_conf_yn`,`create_dt`,`create_no`,`update_dt`,`update_no`,`mt_no`,`order_mem_open_yn`,`order_mem_join_yn`,`order_mem_join_passwd`) values (7,'나의 기본 글모음','00','Y','2010-12-04 23:55:36',5,NULL,NULL,5,'Y','Y','참여비번');

/*Table structure for table `gps_list` */

DROP TABLE IF EXISTS `gps_list`;

CREATE TABLE `gps_list` (
  `Id` int(11) NOT NULL auto_increment,
  `gps_longitude` varchar(100) default NULL,
  `gps_latitude` varchar(100) default NULL,
  `gps_altitude` varchar(100) default NULL,
  `res_context` varchar(4000) default NULL,
  `create_dt` varchar(20) default NULL,
  `aa` varchar(45) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `gps_list` */

/*Table structure for table `inno_db_test` */

DROP TABLE IF EXISTS `inno_db_test`;

CREATE TABLE `inno_db_test` (
  `idinno_db_test` int(11) NOT NULL,
  `column1` varchar(45) default NULL,
  PRIMARY KEY  (`idinno_db_test`)
) ENGINE=MyISAM DEFAULT CHARSET=euckr;

/*Data for the table `inno_db_test` */

insert  into `inno_db_test`(`idinno_db_test`,`column1`) values (1,'column1'),(2,'column1');

/*Table structure for table `mem_tbl` */

DROP TABLE IF EXISTS `mem_tbl`;

CREATE TABLE `mem_tbl` (
  `mt_no` int(11) NOT NULL auto_increment,
  `mem_id` varchar(100) NOT NULL COMMENT '회원id',
  `mem_nm` varchar(20) NOT NULL COMMENT '회원명',
  `passwd` varchar(100) NOT NULL COMMENT '비밀번호',
  `relation_kind` varchar(20) NOT NULL COMMENT '관계종류 01:피드백 필요,02:필드백 불필요',
  `remot_login_id` varchar(100) default NULL COMMENT '피드백필요시 로그인id',
  `remot_login_pw` varchar(100) default NULL COMMENT '피드백필요시 로그인pw',
  `cert_key` varchar(100) default NULL COMMENT '인증키',
  `feed_back_url` varchar(500) default NULL COMMENT '피드백URL',
  `use_yn` varchar(2) default 'Y',
  `create_dt` datetime NOT NULL COMMENT '생성일',
  `create_ip` varchar(20) NOT NULL COMMENT '생성자',
  `update_dt` datetime default NULL,
  `update_ip` varchar(20) default NULL,
  PRIMARY KEY  (`mt_no`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='회원정보';

/*Data for the table `mem_tbl` */

insert  into `mem_tbl`(`mt_no`,`mem_id`,`mem_nm`,`passwd`,`relation_kind`,`remot_login_id`,`remot_login_pw`,`cert_key`,`feed_back_url`,`use_yn`,`create_dt`,`create_ip`,`update_dt`,`update_ip`) values (1,'aaaa','장재훈','passwd','01',NULL,NULL,NULL,NULL,'Y','2010-01-01 00:00:00','',NULL,NULL),(2,'bbbb','장재훈2','passwd','01',NULL,NULL,NULL,NULL,'Y','2010-01-01 00:00:00','',NULL,NULL),(4,'mem_id','mem_nm','7ba00ed47f10af52','relation_kind',NULL,NULL,NULL,NULL,'Y','2010-11-27 00:00:00','',NULL,NULL),(5,'이메일','이메일','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 22:00:18','',NULL,NULL),(6,'이메일2','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 22:13:55','',NULL,NULL),(7,'이메일3','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 22:17:54','',NULL,NULL),(8,'이메일10','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 23:46:16','',NULL,NULL),(9,'이메일5','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 23:47:00','',NULL,NULL),(10,'이메일7','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-27 23:55:46','',NULL,NULL),(11,'이메일8','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 00:04:54','',NULL,NULL),(12,'이메일9','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 00:07:19','',NULL,NULL),(13,'이메일11','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 00:12:16','',NULL,NULL),(14,'이메일16','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 00:40:27','',NULL,NULL),(15,'이메일18','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 00:56:19','',NULL,NULL),(16,'이메일19','이름','7d68232f22516af6','',NULL,NULL,NULL,NULL,'Y','2010-11-28 01:00:18','',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
