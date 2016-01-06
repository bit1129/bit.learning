package com.bit.restlet;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class BookResource extends ServerResource {
    @Post
    public Representation createResource(Representation entity) {
        return null;
    }
}
