package com.bit.restlet.spring;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.Set;

public class TaskResource extends ServerResource {

    @Get
    public Representation get(Representation representation) {
        Form q = getRequest().getResourceRef().getQueryAsForm();
        //names是header name么？
        Set<String> names = q.getNames();
        for (String name : names) {
            System.out.println(name);
        }

        // /tasks/1/2?q1=1&q2=2
        String taskId = (String) getRequest().getAttributes().get("taskId");
        System.out.println(taskId);

        String subTaskId = (String) getRequest().getAttributes().get("subTaskId");
        System.out.println(subTaskId);

        //?q1=123&q2=456
        String value = q.getValues("q1");
        System.out.println("value is: " + value);

        value = q.getValues("q2");
        System.out.println("value is: " + value);

        return null;
    }


}
