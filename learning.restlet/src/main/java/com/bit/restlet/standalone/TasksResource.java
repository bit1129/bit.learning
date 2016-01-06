package com.bit.restlet.standalone;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class TasksResource extends ServerResource {

    @Post
    public Representation create(JsonRepresentation entity) {
        JSONObject obj = entity.getJsonObject();
        int id = obj.getInt("id");
        String name = obj.getString("name");
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        InMemoryData.data.add(task);
        JsonRepresentation jr = new JsonRepresentation(task);
        return jr;
    }

}
