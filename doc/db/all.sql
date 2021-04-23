drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'ID',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '大章名称',
    `sort` INT COMMENT '顺序',
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

alter table `file` add column (`shard_index` int comment '已上传分片');
alter table `file` add column (`shard_size` int comment '分片大小|B');
alter table `file` add column (`shard_total` int comment '分片总数');
alter table `file` add column (`key` varchar(32)  comment '文件标识');
alter table `file` add unique  key key_unique (`key`);


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

-- 用户表
drop table if exists `user`;
create table `user` (
   `id` char(8) not null default '' comment 'id',
   `name` varchar(50) comment '昵称',
   `login_name` varchar(50) not null comment '账号名',
   `password` char(32) not null comment '密码',
   `power` int comment '权限 | 0为超级管理员，1位普通管理员',
   primary key (`id`),
   unique key `login_name_unique` (`login_name`)
)engine = innodb default charset = utf8mb4 comment ='用户';

insert into user (id, name, login_name, password, power) VALUES ('10000000', '+T', '2017764315', '2017764315','0');

-- 资源表
drop table if exists `resource`;
create table `resource` (
    `id` char(6) not null default '' comment 'id',
    `name` varchar(100) not null comment '名称|菜单或按钮',
    `page` varchar(50) null comment '页面|路由',
    `request` char(200) null comment '请求|接口',
    `parent` char(6) comment '父id',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='资源';

insert into resource (id, name, page, request, parent) VALUES ('01','系统管理','','','');
insert into resource (id, name, page, request, parent) VALUES ('0101','用户管理','/system/user','','01');
insert into resource (id, name, page, request, parent) VALUES ('010101','保存','','["/system/admin/user/list","/system/admin/user/save"]','0101');
insert into resource (id, name, page, request, parent) VALUES ('010102','删除','','["/system/admin/user/delete"]','0101');
insert into resource (id, name, page, request, parent) VALUES ('010103','重置密码','','["/system/admin/user/save-password"]','0101');
insert into resource (id, name, page, request, parent) VALUES ('0102','资源管理','/system/resource','','01');
insert into resource (id, name, page, request, parent) VALUES ('010201','保存/显示','','["/system/admin/resource"]','0102');
insert into resource (id, name, page, request, parent) VALUES ('0103','角色管理','/system/role','','01');
insert into resource (id, name, page, request, parent) VALUES ('010301','角色/权限管理','','["/system/admin/role"]','0103');

-- 角色表
drop table if exists `role`;
create table `role` (
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) not null comment '角色',
    `desc` varchar(100) not null comment '描述',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='角色';

insert into role  values ('00000000', '系统管理员', '管理用户、角色权限');
insert into role  values ('00000001', '维护管理员', '维护资源');
insert into role  values ('00000002', '业务管理员', '负责维护业务');
insert into role  values ('00000003', '讲师管理员', '负责讲师业务和课程业务');

-- 角色资源关联表
drop table if exists `role_resource`;
create table `role_resource` (
    `id` char(8) not null default '' comment 'id',
    `role_id` char(8) not null comment '角色|id',
    `resource_id` char(6) not null comment '资源|id',
    primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='角色资源关联表';

insert into role_resource values ('00000001', '00000000', '01');
insert into role_resource values ('00000002', '00000000', '0101');
insert into role_resource values ('00000003', '00000000', '010101');
insert into role_resource values ('00000004', '00000000', '010102');
insert into role_resource values ('00000005', '00000000', '010103');
insert into role_resource values ('00000006', '00000000', '0102');
insert into role_resource values ('00000007', '00000000', '010201');
insert into role_resource values ('00000008', '00000000', '0103');
insert into role_resource values ('00000009', '00000000', '010301');

-- 角色用户关联表
drop table if exists `role_user`;
create table `role_user` (
     `id` char(8) not null default '' comment 'id',
     `role_id` char(8) not null comment '角色|id',
     `user_id` char(8) not null comment '用户|id',
     primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='角色用户关联表';

insert into role_user values ('00000000', '00000000', '10000000');


-- 会员表
drop table if exists `member`;
create table `member` (
   `id` char(8) not null default '' comment 'id',
   `name` varchar(50) comment '昵称',
   `mobile` varchar(11) not null comment '手机号',
   `password` char(32) not null comment '密码',
   `photo` varchar(200) comment '头像url',
   `register_time` datetime(3) comment '注册时间',
   primary key (`id`),
   unique key `mobile_unique` (`mobile`)
)engine = innodb default charset = utf8mb4 comment ='会员';

insert into member values ('00000001','测试','15802079428','123456','',now());

-- 短信验证表
drop table if exists `sms`;
create table `sms` (
  `id` char(8) not null default '' comment 'id',
  `mobile` varchar(11) not null comment '手机号',
  `code` varchar(6) not null comment '验证码',
  `use` char(1) not null comment '用途|枚举[SmsUseEnum]: REGISTER("R", "注册"), FORGET("F", "找回密码")',
  `at` datetime(3) comment '生成时间',
  `status` char(1) not null comment '状态|枚举[SmsStatusEnum]: USED("U", "已使用"), NOT_USED("N", "未使用")',
  primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='短信验证码';

insert into sms values ('00000000', '15802079428', '123456', 'F', now(),'U');

-- 成员课程报名表
drop table if exists `member_course`;
create table `member_course` (
   `id` char(8) not null default '' comment 'id',
   `member_id` varchar(8) not null comment '会员id',
   `course_id` varchar(8) not null comment '课程id',
   `at` datetime(3) not null comment '报名时间',
   primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='成员课程报名表';

insert into member_course values ('I4oFpSW5','JCVNuGzl','00000001',now());
insert into member_course values ('I4oFpSW3','JCVNuGz2','00000001',now());

-- 课程资源表
drop table if exists `course_resource`;
create table `course_resource` (
   `id` char(8) not null default '' comment 'id',
   `course_id` char(8) not null comment '课程id',
   `url` varchar(100) comment '相对地址',
   `name` varchar(100) comment '文件名',
   `size` int comment '大小|字节B',
   `frequency` int default 0 comment '下载次数',
   primary key (`id`)
)engine = innodb default charset = utf8mb4 comment ='课程资源表';

alter table `course_resource` add column (`at` datetime(3) not null comment '上传时间');
