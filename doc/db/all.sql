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

-- 课程
drop table if exists course;
create table course (
    id char(8) not null default '' comment 'id',
    name varchar(50) not null comment '名称',
    summary varchar(2000) comment '概述',
    time int default 0 comment '时长|单位秒',
    image varchar(100) comment '封面',
    online char(1) comment '线上|枚举[CourseOnlineEnum]: ONLINE("0","线上"), Offline("1","线下")',
    status char(1) comment '状态|枚举[CourseStatusEnum]: PUBLISH("P","发布"), DRAFT("D","草稿")',
    category char(1) comment '类别|枚举[CourseCategoryEnum]: PUBLIC_ELECTIVE("0","校公选课"), PUBLIC_COMPULSORY("2","校公共必修课"), MAJOR_REQUIRED("3","专业必修课"),DEGREE("4","学位课")',
    enroll integer default 0 comment '报名数',
    sort int comment '顺序',
    create_at datetime(3) comment '创建时间',
    update_at datetime(3) comment '修改时间',
    primary key (id)
)engine = innodb default charset = utf8mb4 comment = '课程表';

-- 表已存在且有数据，再执行原来的sql会清空历史数据
alter table course add column (`teacher_id` char(8) comment '讲师|teacher.id');

insert into course (id, name, summary, time, image, online, status, category, enroll, sort, create_at, update_at) values
('00000001', '测试课程', '测试课程的概述', '1000',  '', '0', 'P', '0', '100', '1', now(), now());

-- 分类表
drop table if exists `category`;
create table `category` (
    `id` char(8) not null default '' comment 'id',
    `parent` char(8) not null default '' comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='分类';

insert into category (id, parent, name, sort) VALUES ('00000100', '00000000', '前端技术', 100);
insert into category (id, parent, name, sort) VALUES ('00000101', '00000100', 'html/css', 101);
insert into category (id, parent, name, sort) VALUES ('00000102', '00000100', 'javascript', 102);
insert into category (id, parent, name, sort) VALUES ('00000103', '00000100', 'vue.js', 103);
insert into category (id, parent, name, sort) VALUES ('00000104', '00000100', 'react.js', 104);
insert into category (id, parent, name, sort) VALUES ('00000105', '00000100', 'angular', 105);
insert into category (id, parent, name, sort) VALUES ('00000106', '00000100', 'node.js', 106);
insert into category (id, parent, name, sort) VALUES ('00000107', '00000100', 'jquery', 107);
insert into category (id, parent, name, sort) VALUES ('00000108', '00000100', '小程序', 108);
insert into category (id, parent, name, sort) VALUES ('00000200', '00000000', '后端技术', 200);
insert into category (id, parent, name, sort) VALUES ('00000202', '00000200', 'springboot', 202);
insert into category (id, parent, name, sort) VALUES ('00000203', '00000200', 'spring cloud', 203);
insert into category (id, parent, name, sort) VALUES ('00000204', '00000200', 'ssm', 204);
insert into category (id, parent, name, sort) VALUES ('00000205', '00000200', 'python', 205);
insert into category (id, parent, name, sort) VALUES ('00000206', '00000200', '爬虫', 206);
insert into category (id, parent, name, sort) VALUES ('00000300', '00000000', '移动开发', 300);
insert into category (id, parent, name, sort) VALUES ('00000301', '00000300', 'android', 301);
insert into category (id, parent, name, sort) VALUES ('00000302', '00000300', 'ios', 302);
insert into category (id, parent, name, sort) VALUES ('00000303', '00000300', 'react native', 303);
insert into category (id, parent, name, sort) VALUES ('00000304', '00000300', 'ionic', 304);
insert into category (id, parent, name, sort) VALUES ('00000400', '00000000', '前沿技术', 400);
insert into category (id, parent, name, sort) VALUES ('00000401', '00000400', '微服务', 401);
insert into category (id, parent, name, sort) VALUES ('00000402', '00000400', '区块链', 402);
insert into category (id, parent, name, sort) VALUES ('00000403', '00000400', '机器学习', 403);
insert into category (id, parent, name, sort) VALUES ('00000404', '00000400', '深度学习', 404);
insert into category (id, parent, name, sort) VALUES ('00000405', '00000400', '数据挖掘', 405);

-- 课程分类表
drop table if exists `course_category`;
create table `course_category` (
    `id` char(8) not null default '' comment 'id',
    `course_id` char(8) comment '课程|course.id',
    `category_id` char(8) comment '分类|category.id',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='课程分类';

-- 课程内容表
drop table if exists `course_content`;
create table `course_content` (
    `id` char(8) not null default '' comment 'id',
    `content` mediumtext not null comment '课程内容',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='课程内容';

-- 讲师表
drop table if exists `teacherr`;
create table `teacher` (
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) not null comment '姓名',
    `department` varchar(50) comment '学院',
    `image` varchar(100) comment '头像',
    `position` varchar(50) comment '职位',
    `motto` varchar(50) comment '座右铭',
    `intro` varchar(500) comment '简介',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='讲师';

-- 文件表
drop table if exists `file`;
create table `file` (
    `id` char(8) not null default '' comment 'id',
    `path` varchar(100) not null comment '相对路径',
    `name` varchar(100) comment '文件名',
    `suffix` varchar(10) comment '后缀',
    `size` int comment '大小|字节B',
    `use` char(1) comment '用途|枚举[FileUseEnum]: COURSE("C", "讲师"), TEACHER("T", "课程")',
    created_at datetime(3) comment '创建时间',
    updated_at datetime(3) comment '修改时间',
    primary key (`id`),
    unique key `path_unique` (`path`)
)engine = innodb default charset = utf8mb4 comment ='文件';

-- 课程内容文件表
drop table if exists `course_content_file`;
create table `course_content_file` (
    `id` char(8) not null default '' comment 'id',
    `course_id` varchar(100) not null comment '课程id',
    `url` varchar(100) comment '地址',
    `name` varchar(100) comment '文件名',
    `size` int comment '大小|字节B',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='课程内容文件';