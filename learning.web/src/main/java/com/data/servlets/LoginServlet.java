package com.data.servlets;

import com.data.models.ResponseObject;
import com.data.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ResponseObject obj = new ResponseObject();

        /**此处用户名和密码直接使用123，实际中需要查询数据库*/
        if ("123".equals(username) && "123".equals(password)) {
            obj.setCode(ResponseObject.Code.SUCCESS);
            obj.setMsg("登录成功");
        } else {
            obj.setCode(ResponseObject.Code.ERROR);
            obj.setMsg("登录失败");
        }

        BufferedWriter writer = null;
        try {
            OutputStream out = resp.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            String json = GsonUtils.java2String(obj);
            writer.write(json);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
