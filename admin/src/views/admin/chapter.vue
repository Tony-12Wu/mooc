<template>
  <div>
    <h3 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
    </h3>
    <hr>
    <p>
      <router-link to="/business/course" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-arrow-left"></i>
        返回课程
      </router-link>
      &nbsp;
      <button v-show="hasResource('020202')" v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-show="hasResource('020202')" v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>
    <!-- 单独的样式每页8条 -->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>排序</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <!--数据绑定 -->
      <tr v-for="chapter in chapters">
        <td>{{chapter.id}}</td>
        <td>{{chapter.name}}</td>
        <td>{{chapter.sort}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              小节
            </button>
            <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              编辑
            </button>
            <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-info btn-round">
             删除
            </button>
          </div>
          <!-- 响应式设计，中屏或小屏时只显示一个按钮 -->
          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                  <span class="blue">
                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                  <span class="green">
                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                  <span class="red">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                  </span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <!-- /.modal 模态框-->
    <div id="form-model" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="名称">
                </div>
              </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">排序</label>
                    <div class="col-sm-10">
                        <input v-model="chapter.sort" class="form-control">
                    </div>
                </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{course.name}}</p>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-show="hasResource('020202')" v-on:click="save()" type="button" class="btn btn-primary">保存</button>
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
        name: 'chapter',
        data: function() {
            return {
                chapter: {},
                chapters: [],
                course: {},
            }
        },
        mounted: function () {
            let _this = this;
            //设置初始分页条数
            _this.$refs.pagination.size = 10,
            _this.course = SessionStorage.get(SESSION_KEY_COURSE) || {};
            if (Tool.isEmpty(_this.course)) {
                _this.$route.push("/welcome");
            }
            _this.list(1);
            this.$parent.activeSidebar("business-course-sidebar");

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
             * 点击【新增】按钮
             */
            add() {
                let _this = this;
                _this.chapter = {
                    sort: _this.$refs.pagination.total + 1
                };
                $('#form-model').modal('show');
            },
            /**
             * 点击【编辑】按钮
             * @param chapter
             */
            edit(chapter) {
                let _this = this;
                _this.chapter = $.extend({}, chapter);
                $('#form-model').modal('show');
            },
            /**
             * 列表查询
             * @param page
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list',{
                    page: page,
                    size: _this.$refs.pagination.size,
                    courseId: _this.course.id,
                }).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.chapters = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },
            /**
             * 点击【保存】按钮
             */
            save() {
                let _this = this;

                //保存检验
                if (!Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId,"课程ID", 1, 8)){
                    return;
                }
                _this.chapter.courseId = _this.course.id;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save',_this.chapter).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        $('#form-model').modal('hide');
                        _this.list(1);
                        Toast.success("保存成功！");
                    }else {
                        Toast.warning(resp.message);
                    }
                })
            },
            /**
             * 点击【删除】按钮
             * @param id
             */
            del(id) {
                let _this = this;
                Confirm.show("删除大章数据后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response)=>{
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            Toast.success("删除成功！")
                        }
                    })
                })
            },
            /**
             * 点击【小节】按钮跳转到小节页面
             */
            toSection(chapter) {
                let _this = this;
                SessionStorage.set(SESSION_KEY_CHAPTER,chapter);
                _this.$router.push("/business/section");
            }


        }
    }
</script>