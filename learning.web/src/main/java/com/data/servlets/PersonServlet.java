package com.data.servlets;

import com.data.models.IPersonFactory;
import com.data.models.MemoryPersonFactory;
import com.data.models.Person;
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
import java.util.List;

public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedWriter writer = null;
        ResponseObject obj = new ResponseObject();
        try {

            String action = request.getParameter("action");
            if (action == null || action.equals("get")) {
                IPersonFactory personFactory = new MemoryPersonFactory();
                List<Person> persons = personFactory.get();
                obj.setData(persons);
                obj.setMsg("success");
                obj.setCode(ResponseObject.Code.SUCCESS);
            } else if (action.equals("update")) {
                String id = request.getParameter("id");
                String salary = request.getParameter("salary");
                IPersonFactory personFactory = new MemoryPersonFactory();
                personFactory.updateSalary(id, Double.parseDouble(salary));
                obj.setMsg("success");
                obj.setCode(ResponseObject.Code.SUCCESS);
            }

            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            String json = GsonUtils.java2String(obj);
            writer.write(json);

        } catch (Exception e) {
            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            obj.setCode(ResponseObject.Code.ERROR);
            obj.setMsg(e.getMessage());
            String json = GsonUtils.java2String(obj);
            writer.write(json);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
