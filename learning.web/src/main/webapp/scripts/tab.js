$(function () {
    $("ul li").each(function (index) {
        var self = $(this);

        self.mouseover(function () {
            if (index === 0) {
                $("#dynamicContent").hide();
                $("#staticContent").show();
            } else if (index === 1) {  //从服务器端获取html页面内容
                $("#staticContent").hide();
                var contentArea = $("#dynamicContent");
                contentArea.empty();
                //load方法用于从服务器端获取页面内容，然后渲染到当前元素(contentArea)
                contentArea.load("../htmls/eco.html");
                contentArea.show();
            } else if (index === 2) {  //从服务器端获取JSON数据
                $("#staticContent").hide();
                var contentArea = $("#dynamicContent");
                contentArea.empty();
                $.ajax({
                    "type": "get",
                    "url": "/ent",
                    "success": function (data) {
                        contentArea.html(data);
                        contentArea.show();
                    },
                    "error": function (error) {
                        contentArea.html(error.response);
                    }
                })
            }
        });

    })


});