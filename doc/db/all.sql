drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'ID',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '大章名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment = '大章';

insert into `chapter` (id, course_id, name) VALUES ('00000001','0000000','测试大章01');
insert into `chapter` (id, course_id, name) VALUES ('00000002','0000000','测试大章02');
insert into `chapter` (id, course_id, name) VALUES ('00000003','0000000','测试大章03');
insert into `chapter` (id, course_id, name) VALUES ('00000004','0000000','测试大章04');
insert into `chapter` (id, course_id, name) VALUES ('00000005','0000000','测试大章05');
insert into `chapter` (id, course_id, name) VALUES ('00000006','0000000','测试大章06');
insert into `chapter` (id, course_id, name) VALUES ('00000007','0000000','测试大章07');
insert into `chapter` (id, course_id, name) VALUES ('00000008','0000000','测试大章08');
insert into `chapter` (id, course_id, name) VALUES ('00000009','0000000','测试大章09');
insert into `chapter` (id, course_id, name) VALUES ('00000010','0000000','测试大章10');
insert into `chapter` (id, course_id, name) VALUES ('00000011','0000000','测试大章11');
insert into `chapter` (id, course_id, name) VALUES ('00000012','0000000','测试大章12');
insert into `chapter` (id, course_id, name) VALUES ('00000013','0000000','测试大章13');
insert into `chapter` (id, course_id, name) VALUES ('00000014','0000000','测试大章14');

-- 小节
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`(
    `id`         CHAR(8)     NOT NULL DEFAULT '' COMMENT 'ID',
    `title`      VARCHAR(50) NOT NULL COMMENT '标题',
    `course_id`  CHAR(8) COMMENT '课程|course.id',
    `chapter_id` CHAR(8) COMMENT '大章|chapter.id',
    `video`      VARCHAR(200) COMMENT '视频',
    `time`       INT COMMENT '时长|单位秒',
    `charge`     CHAR(1) COMMENT '收费|c 收费; F 免费',
    `sort`       INT COMMENT '顺序',
    `created_at` DATETIME(3) COMMENT '创建时间',
    `updated_at` DATETIME(3) COMMENT '修改时间',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '小节';

INSERT INTO section (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
VALUES ('00000001', '测试小节1', '00000001', '00000000', '', 500, 'F', 1, now(), now());

