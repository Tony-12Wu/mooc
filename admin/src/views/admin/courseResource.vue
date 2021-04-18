<template>
    <div>
        <h4 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" class="pink"> {{course.name}}</router-link>
        </h4>
        <hr>
        <big-file v-bind:input-id="'resource-file-upload'"
                  v-bind:text="'上传资源'"
                  v-bind:suffixs="['jpg', 'jpeg', 'png', 'mp4', 'doc','docx','excel', 'pdf', 'pptx','md']"
                  v-bind:use="FILE_USE.RESOURCE.key"
                  v-bind:after-upload="afterUploadResourceFile"></big-file>
        <br>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>课程id</th>
                <th>相对地址</th>
                <th>文件名</th>
                <th>大小</th>
                <th>下载次数</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="courseResource in courseResources">
                <td>{{courseResource.courseId}}</td>
                <td>{{courseResource.url}}</td>
                <td>{{courseResource.name}}</td>
                <td>{{courseResource.size | formatFileSize}}</td>
                <td>{{courseResource.frequency}}</td>
                <td>
                    <button v-on:click="del(courseResource.id)" class="btn btn-white btn-xs btn-warning btn-round">
                        <i class="ace-icon fa fa-times red2"></i>
                        删除
                    </button>
                </td>
            </tr>
            </tbody>
        </table>

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">相对地址</label>
                                <div class="col-sm-10">
                                    <input v-model="courseResource.url" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">文件名</label>
                                <div class="col-sm-10">
                                    <input v-model="courseResource.name" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">大小</label>
                                <div class="col-sm-10">
                                    <input v-model="courseResource.size" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">下载地址</label>
                                <div class="col-sm-10">
                                    <input v-model="courseResource.downloadpath" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">下载次数</label>
                                <div class="col-sm-10">
                                    <input v-model="courseResource.frequency" class="form-control">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button v-on:click="save()" v-show="hasResource('020206')"  type="button" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    import BigFile from "../../components/big-file";

    export default {
        components: {Pagination, BigFile},
        name: "business-courseResource",
        data: function () {
            return {
                courseResource: {},
                courseResources: [],
                course: [],
                FILE_USE: FILE_USE
            }
        },
        mounted: function () {
            let _this = this;
            // 设置初始页面条数
            _this.$refs.pagination.size = 10;
            _this.course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            _this.list(1);
            // sidebar激活样式方法一
            // this.$parent.activeSidebar("business-courseResource-sidebar");

        },
        methods: {

            /**
             * 查找是否有权限
             * @param id
             */
            hasResource(id) {
                return Tool.hasResource(id);
            },

            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/courseResource/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response) => {
                    Loading.hide();
                    let resp = response.data;
                    _this.courseResources = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);

                })
            },

            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除课程资源后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/courseResource/delete/' + id).then((response) => {
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            Toast.success("删除成功！");
                        }
                    })
                });
            },

            /**
             * 上传课程资源文件后，保存课程资源记录
             */
            afterUploadResourceFile(response) {
                let _this = this;
                console.log("开始保课程资源记录");
                let file = response.content;
                file.courseId = _this.course.id;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/courseResource/save', file).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        Toast.success("上传课程资源和保存记录成功");
                        _this.list(1);
                    }
                });
            },
        }
    }
</script>