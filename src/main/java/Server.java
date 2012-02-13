import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.resource.Resource;

/**
 * User: kzimnick
 * Date: 12.02.12
 * Time: 12:24
 */
@Singleton
public class Server {

    private Config config;

    private LogTailer logTailer;

    @Inject
    public Server(LogTailer logTailer, Config config) {
        this.logTailer = logTailer;
        this.config = config;
        init();
    }

    private void init() {
        ResourceHandler resource_handler = prepareResourceHandler();
        Handler handler = new SimpleHandler(logTailer);
        org.mortbay.jetty.Server server = createServer(resource_handler, handler);
        startServer(server);
    }

    private org.mortbay.jetty.Server createServer(ResourceHandler resource_handler, Handler handler) {
        org.mortbay.jetty.Server server = new org.mortbay.jetty.Server(config.getPort());
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, handler});
        server.setHandler(handlers);
        return server;

    }

    private ResourceHandler prepareResourceHandler() {
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
//        try {
        resource_handler.setBaseResource(Resource.newClassPathResource("static"));
//            System.out.println(new FileResource(Server.class.getResource("static")));
//            URLResource res;
//            res = new URLResource(Server.class.getResource("static")));
//            resource_handler.setBaseResource(new FileResource(Server.class.getResource("static")));
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (URISyntaxException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        return resource_handler;
    }

    private void startServer(org.mortbay.jetty.Server server) {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
