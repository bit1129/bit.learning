$(function () {

    function _initialUI() {
        $("#dynamicContent").hide();
        $("div.defaultTab").addClass("active");
    }

    _initialUI();

    $("ul li").each(function (index) {
        var self = $(this);

        self.mouseover(function () {
            /**
             * 已经在当前tab页面上
             */
            if (self.hasClass("active")) {
                return;
            }
            $("ul li").removeClass("active");
            self.addClass("active");

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
                    "success": function (response) {
                        data = $.parseJSON(response).data;
                        for (var i = 0; i < data.length; i++) {
                            var label = data[i].label;
                            var href = data[i].href;
                            var p = $("<p><a href=" + href + " target=_blank>" + label + "</a></p>");
                            contentArea.append(p);

                        }
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