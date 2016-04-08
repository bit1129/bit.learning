$(function () {
    //页面加载完成，开始加载数据，渲染页面
    function _fireRequestAndRenderUI() {
        $.ajax({
            type: "get",
            url: "persons",
            success: function (data) {
                alert(data);
            },
            error: function (error) {
                alert("Error: " + error.responseText);
            }

        })
    }

    function _doRenderUI() {

    }

    _fireRequestAndRenderUI();
});