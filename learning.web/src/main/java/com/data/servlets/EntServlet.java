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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedWriter writer = null;
        try {
            ResponseObject obj = new ResponseObject();

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            Map<String, String> entry = new HashMap<String, String>();
            entry.put("label", "娱乐新闻1");
            entry.put("href", "http://abc.txt");
            data.add(entry);

            entry = new HashMap<String, String>();
            entry.put("label", "娱乐新闻2");
            entry.put("href", "http://abc.txt");
            data.add(entry);

            entry = new HashMap<String, String>();
            entry.put("label", "娱乐新闻3");
            entry.put("href", "http://abc.txt");
            data.add(entry);

            entry = new HashMap<String, String>();
            entry.put("label", "娱乐新闻4");
            entry.put("href", "http://abc.txt");
            data.add(entry);

            obj.setData(data);
            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            String json = GsonUtils.java2String(obj);
            writer.write(json);
        } finally {
            writer.close();
        }
    }
}
