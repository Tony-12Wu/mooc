<template>
    <!--等待模态框-->
    <div class="modal fade" id="loading-modal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
                <img src="http://lingnan-imooc.oss-cn-guangzhou.aliyuncs.com/teacher/6xZK3Z4C8UUAU4S6kW8y.gif">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "loading.vue",
    methods:{
        showModel(){
            $('#loading-modal').modal('show');
            console.log("打开等待框")
        },
        hideModel(){
            $('#loading-modal').modal('hide');
            console.log("关闭等待框");
        }
    }
}
</script>

<style scoped>

</style>