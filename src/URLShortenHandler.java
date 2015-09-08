import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

/**
 * Created by amaliujia on 15-9-6.
 */
public class URLShortenHandler extends URLHandler{

    public void handle(HttpExchange httpExchange) throws IOException {
        this.exchange = httpExchange;
        Map params = (Map)httpExchange.getAttribute("parameters");

        Request request = new Request('1', (String) params.get("longURL"), (ShortenResponse)this);
        RequestQueue queue = RequestQueue.sharedInstance();
        queue.addRequest(request);
    }
}
