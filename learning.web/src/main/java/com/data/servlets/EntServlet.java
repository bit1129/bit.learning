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

public class EntServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedWriter writer = null;
        try {
            ResponseObject obj = new ResponseObject();
            obj.setData("this is the data");
            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            String json = GsonUtils.java2String(obj);
            writer.write(json);
        } finally {
            writer.close();
        }
    }
}
