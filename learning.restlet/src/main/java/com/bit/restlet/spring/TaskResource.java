package com.bit.restlet.spring;

import com.bit.restlet.standalone.InMemoryData;
import com.bit.restlet.standalone.ResponseObject;
import com.bit.restlet.standalone.Task;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import java.util.Set;

public class TaskResource extends ServerResource {

    /**
     * 通过JsonRepresentation获取请求参数
     *
     * @param representation
     * @return
     */
    @Delete
    public Representation delete(JsonRepresentation representation) {
        JSONObject jo = representation.getJsonObject();
        int id = jo.getInt("id");
        Task task = new Task();
        task.setId(id);
        boolean b = InMemoryData.data.delete(task);
        if (b) {
            return new JsonRepresentation(ResponseObject.SUCC);
        } else {
            return new JsonRepresentation(ResponseObject.FAIL);
        }

    }


    /**
     * 获取客户端发送来的header值,restlet将所有的headers放到org.restlet.http.headers这个attribute中了
     *
     * @param representation
     * @return
     */
    @Put
    public Representation put(Representation representation) {
        Form headers = (Form) getRequest().getAttributes().get("org.restlet.http.headers");
        //names是header name么？
        Set<String> names = headers.getNames();
        for (String name : names) {
            System.out.println(name);
        }

        //header-user-name是自定义的header
        String value = headers.getValues("header-user-name");
        System.out.println("value is: " + value);

        //query-param-user-name是请求参数，类似?query-param-user-name=123，这里得不到值，因为headers是封装的headers信息
        value = headers.getValues("query-param-user-name");
        System.out.println("value is: " + value);

        return null;
    }

    @Get
    public Representation get(Representation representation) {
        Form q = getRequest().getResourceRef().getQueryAsForm();
        //names是header name么？
        Set<String> names = q.getNames();
        for (String name : names) {
            System.out.println(name);
        }

        //?q1=123&q2=456
        String value = q.getValues("q1");
        System.out.println("value is: " + value);

        value = q.getValues("q2");
        System.out.println("value is: " + value);

        return null;
    }


}
