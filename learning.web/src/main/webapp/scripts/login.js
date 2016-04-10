$(function () {

    $("#loginButton").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var method = "post"   //can be get or post
        var requestObject = {};
        requestObject.type = method;
        if (method === "get") {
            requestObject.url = "/login?username=" + username + "&password=" + password;
        } else if (method === "post") {
            requestObject.url = "/login";
            requestObject.data = "username=" + username + "&password=" + password;
        }
        requestObject.success = function (data) {
            data = $.parseJSON(data);
            var messageArea = $("#messageArea");
            if (data.code == "20000") {
                if (messageArea.hasClass("fail")) {
                    messageArea.removeClass("fail");
                }
                messageArea.addClass("success");
            } else {
                if (messageArea.hasClass("success")) {
                    messageArea.removeClass("success");
                }
                messageArea.addClass("fail");
            }
            messageArea.html(data.msg);
        };
        requestObject.error = function (error) {
            alert(error.responseText);
        };

        $.ajax(requestObject);
    });


});