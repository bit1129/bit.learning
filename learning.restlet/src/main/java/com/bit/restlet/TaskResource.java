package com.bit.restlet;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.ServerResource;

public class TaskResource extends ServerResource {

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
}
