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