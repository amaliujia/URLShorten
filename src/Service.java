import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by amaliujia on 15-9-6.
 */
public class Service{
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/query", new URLLookupHandler());
        HttpContext context = server.createContext("/insert", new URLShortenHandler());
        context.getFilters().add(new ParameterFilter());
        server.setExecutor(null);
        server.start();

        ShortenEngine engine = new ShortenEngine();
        engine.startService();
    }
}

