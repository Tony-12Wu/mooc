Loading = {
  show: function () {
    $.blockUI({
      message: '<img src="http://lingnan-imooc.oss-cn-guangzhou.aliyuncs.com/teacher/6xZK3Z4C8UUAU4S6kW8y.gif" />',
      css: {
        zIndex: "10011",
        padding: "10px",
        left: "50%",
        width: "80px",
        marginLeft: "-40px",
      }
    });
  },
  hide: function () {
    //  $.unblockUI(); 生产环境直接使用这行代码即可
    // 本地查询速度太快，loading显示一瞬间，故意做个延迟
    setTimeout(function () {
      $.unblockUI();
    }, 200)
  }
};