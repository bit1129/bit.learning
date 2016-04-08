package com.data.servlets;

import com.data.models.IPersonFactory;
import com.data.models.MemoryPersonFactory;
import com.data.models.Person;
import com.data.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedWriter writer = null;
        try {
            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            IPersonFactory personFactory = new MemoryPersonFactory();
            List<Person> persons = personFactory.get();
            String json = GsonUtils.java2String(persons);
            writer.write(json);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
