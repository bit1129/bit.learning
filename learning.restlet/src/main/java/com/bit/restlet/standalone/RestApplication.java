package com.bit.restlet.standalone;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestApplication extends Application {

    //How does Restlet support 位置参数？，比如/task/123
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/tasks", TasksResource.class);
        router.attach("/task", TaskResource.class);
        router.attach("/books", BookResource.class);
        return router;
    }
}
