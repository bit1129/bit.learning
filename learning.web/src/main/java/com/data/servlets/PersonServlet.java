package com.data.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedWriter writer = null;
        try {
            OutputStream out = response.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out)) ;
            writer.write("Hello, World");

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
