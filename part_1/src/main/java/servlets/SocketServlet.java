package servlets;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import echosocket.EchoSocket;

@WebServlet(name = "SocketServlet", urlPatterns = {"/chat"})
public class SocketServlet extends WebSocketServlet {
    private final static int LOGOUT_TIMEOUT = 600000;

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIMEOUT);
        factory.register(EchoSocket.class);
    }
}
