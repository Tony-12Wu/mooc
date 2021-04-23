<template>
  <div class="card mb-4 shadow-sm course">
    <a href="javascript:;" class="text-info bigger-110" v-bind:title="course.teacherName">
      <img v-show="!course.image" class="img-fluid" src="http://lingnan-imooc.oss-cn-guangzhou.aliyuncs.com/course/3xzByd6GCMOocGK0qIa0kI.jpg"/>
      <img v-bind:src="course.image" class="img-fluid">
    </a>
    <div class="card-body">
      <h4 class="">{{course.name}} {{course.teacherName}}</h4>
      <p class="card-text">{{course.summary}}</p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <router-link v-bind:to="'/detail?id=' + course.id" class="btn btn-outline-secondary">课程详情</router-link>
        </div>
        <div class="text-muted">
          <span class="badge badge-info">{{COURSE_ONLINE | optionKV(course.online)}}</span>
          <span class="badge badge-info">{{COURSE_CATEGORY | optionKV(course.category)}}</span>
          <span class="badge badge-info"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;{{course.enroll}}</span>&nbsp;
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'the-course',
    props: {
      course: {}
    },
    data: function () {
      return {
          COURSE_ONLINE:COURSE_ONLINE,
          COURSE_CATEGORY:COURSE_CATEGORY
      }
    },
  }
</script>

<style>
  .course h4 {
    font-size: 1.25rem;
    margin: 15px 0;
  }

  .course .text-muted .badge {
    font-size: 1rem;
  }
</style>
