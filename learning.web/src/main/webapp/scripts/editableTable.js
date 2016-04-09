$(document).ready(function () {
    //页面加载完成，开始加载数据，渲染页面
    function _fireRequestAndRenderUI() {
        $.ajax({
            type: "get",
            url: "/persons",
            success: function (response) {
                //将响应结果转换为JSON
                response = $.parseJSON(response);
                _doRenderUI(response.data);
            },
            error: function (error) {
                alert("Error: " + error.responseText);
            }
        })
    }

    function _doRenderUI(data) {

        if (!data) {
            console.log("data is empty");
            return;
        }
        for (var i = 0; i < data.length; i++) {
            var person = data[i];
            var id = person.id;
            var name = person.name;
            var age = person.age;
            var salary = person.salary;
            var job = person.job;
            var tbody = $("tbody")
            var tr = $("<tr></tr>");
            if (i % 2 === 0) {
                tr.addClass("highlight");
            }

            //高亮当前行
            tr.mouseover(function () {
                $(this).addClass("hover");
            }).mouseout(function () {
                $(this).removeClass("hover");
            });

            tbody.append(tr);
            var td = $("<td></td>")
            td.html(id);
            tr.append(td)

            td = $("<td></td>");
            td.html(name);
            tr.append(td);

            td = $("<td></td>")
            td.html(age);
            tr.append(td)

            //支持可编辑
            td = $("<td></td>")
            td.html(salary);
            tr.append(td);
            td.attr("_id", id);
            td.click(function () {
                var elem = $(this);
                if (elem.children("input").length > 0) {
                    return false;
                }
                var id = elem.attr("_id");
                var salary = elem.html();
                //创建一个input text
                var input = $("<input type='text'>");
                input.val(salary);
                elem.empty();
                elem.append(input);

                //保持input text的样式与包含它的td一致
                //1. 去掉边框
                //2. 宽度与td一致
                //3. 背景色与td一致
                //4. 字体大小要保持一致
                input.css("border-width", "0px");
                input.width(elem.width());
                input.css("background-color", elem.css("background-color"));
                input.css("font-size", elem.css("font-size"));

                //编辑完成，用户按下回车则提交编辑结果，按下Esc键取消编辑
                input.keyup(function (evt) {
                    var keyCode = evt.which;
                    var self = $(this)
                    if (keyCode === 13) {
                        //发送Ajax提交请求
                        $.ajax({
                            type: "get",
                            url: "/persons?action=update&id=" + id + "&salary=" + self.val(),
                            success: function (data) {
                                elem.html(self.val());
                            },
                            error: function (error) {
                                alert(error.responseText);
                            }
                        });
                    } else if (keyCode === 27) {
                        elem.html(salary);
                    }
                });


            });

            td = $("<td></td>")
            td.html(job);
            tr.append(td)
        }
    }

    _fireRequestAndRenderUI();

});
