import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;

/**
 * Created by IntelliJ IDEA.
 * User: kzimnick
 * Date: 10.02.12
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
public class Test {

           //git test
    public static void main(String args[]) throws Exception {
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        resource_handler.setResourceBase("./src/main/resources/");

        LogTailer logTailer = new LogTailer();
        new Thread(logTailer).start();

        Handler handler = new SimpleHandler(logTailer);
        Server server = new Server(1235);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, handler});
        server.setHandler(handlers);
        server.start();
    }
}
