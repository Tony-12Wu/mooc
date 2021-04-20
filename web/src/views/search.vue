<template>
    <main role="main">
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <pagination ref="pagination" v-bind:list="SearchCourse"></pagination>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div v-for="o in courses" class="col-md-4">
                        <the-course v-bind:course="o"></the-course>
                    </div>
                    <h3 v-show="courses.length === 0">未搜索到课程</h3>
                </div>
            </div>
        </div>

    </main>
</template>

<script>
import TheCourse from "../components/the-course";
import Pagination from "../components/pagination";

export default {
    components: {Pagination, TheCourse},
    name: 'search',
    data: function () {
        return {
            courses: [],
            course: {}
        }
    },
    mounted() {
        let _this = this;
        _this.$refs.pagination.size = 3;
        _this.name = _this.$route.query.name;
        _this.SearchCourse(1);
    },
    methods: {
        /**
         * 搜索课程列表
         */
        SearchCourse(page) {
            let _this = this;
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/course/search', {
                page: page,
                size: _this.$refs.pagination.size,
                courseName:_this.name
            }).then((response) => {
                let resp = response.data;
                if (resp.success) {
                    _this.courses = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                }
            }).catch((response) => {
                console.log("error：", response);
            })
        },

    }
}
</script>
