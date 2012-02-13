import com.google.inject.Inject;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleHandler extends AbstractHandler {

    private LogTailer logTailer;

    @Inject
    public SimpleHandler(LogTailer logTailer) {
        this.logTailer = logTailer;
    }

    public void handle(String s, HttpServletRequest request, HttpServletResponse response, int i) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(logTailer.getLogs());
        ((Request) request).setHandled(true);
    }
}
