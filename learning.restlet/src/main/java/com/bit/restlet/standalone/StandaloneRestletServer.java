package com.bit.restlet.standalone;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class StandaloneRestletServer {
    private static void startServer(int port) throws Exception {
        Component component = new Component();
        // Add a new HTTP server listening on port 8111.
        Context context = new Context();
        context.getParameters().add("maxThreads", "200");
        context.getParameters().add("maxConnectionsPerHost", "100");
        context.getParameters().add("maxTotalConnections", "100");

        Server server = new Server(context, Protocol.HTTP, port);
        component.getServers().add(server);
//        component.getServers().add(Protocol.HTTP);
        component.getClients().add(Protocol.HTTP);
//        component.getContext().getParameters().add("maxThreads", "200");
//        component.getClients().add(Protocol.CLAP);
//        component.getClients().add(Protocol.FILE);
        component.getDefaultHost().attach("/restlet", new RestApplication());
        // Start the component.
        component.start();
    }

    public static void main(String[] args) throws Exception {
        startServer(10001);
    }
}
