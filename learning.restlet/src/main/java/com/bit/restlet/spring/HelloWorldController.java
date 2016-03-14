package com.bit.restlet.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

@Controller
@RequestMapping(value = "helloworld")
public class HelloWorldController {

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ResponseBody
    public String samyFSello() {
        return "Welcome to the Spring world";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String data(HttpServletRequest request) throws IOException {
        InputStream in = null;
        Map map = request.getParameterMap();

        String x = request.getParameter("x");
        String y = request.getParameter("y");

        try {
            in = request.getInputStream();
            System.out.println("input stream is null? " + (in == null));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            System.out.println("==========================Data Received:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            return "success";

        } catch (Exception e) {
            File f = new File("/home/yuzt/test1.1");
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            PrintWriter writer = new PrintWriter(out);
            e.printStackTrace(writer);
            writer.close();
            return e.getMessage();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
