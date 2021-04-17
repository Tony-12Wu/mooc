<template>
    <div>
        <p>
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程
            </router-link>
            <button v-show="hasResource('020206')" v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-show="hasResource('020206')" v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>课程id</th>
                <th>相对地址</th>
                <th>文件名</th>
                <th>大小</th>
                <th>下载地址</th>
                <th>下载次数</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="courseResource in courseResources">
                <td>{{courseResource.id}}</td>
                <td>{{courseResource.courseId}}</td>
                <td>{{courseResource.url}}</td>
                <td>{{courseResource.name}}</td>
                <td>{{courseResource.size}}</td>
                <td>{{courseResource.downloadpath}}</td>
                <td>{{courseResource.frequency}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="edit(courseResource)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>
                        <button v-on:click="del(courseResource.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>
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

    export default {
        components: {Pagination},
        name: "business-courseResource",
        data: function () {
            return {
                courseResource: {},
                courseResources: [],
                course: []
            }
        },
        mounted: function () {
            let _this = this;
            // 设置初始页面条数
            _this.$refs.pagination.size = 10;
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
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.courseResource = {};
                $("#form-modal").modal("show");
            },

            /**
             * 点击【编辑】
             */
            edit(courseResource) {
                let _this = this;
                _this.courseResource = $.extend({}, courseResource);
                $("#form-modal").modal("show");
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
             * 点击【保存】
             */
            save() {
                let _this = this;
                let course = SessionStorage.get(SESSION_KEY_COURSE) || {};

                // 保存校验
                if (1 != 1
                    || !Validator.length(_this.courseResource.courseId, "课程id", 1, 100)
                    || !Validator.length(_this.courseResource.url, "相对地址", 1, 100)
                    || !Validator.length(_this.courseResource.name, "文件名", 1, 100)
                    || !Validator.length(_this.courseResource.downloadpath, "下载地址", 1, 100)
                ) {
                    return;
                }
                _this.courseResource.courseId = course.id;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/courseResource/save', _this.courseResource).then((response) => {
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功！");
                    } else {
                        Toast.warning(resp.message)
                    }
                })
            },

            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除课程资源表后不可恢复，确认删除？", function () {
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
            }
        }
    }
</script>