import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by amaliujia on 15-9-7.
 */
public class URLLookupHandler extends URLHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        this.exchange = httpExchange;
        if(httpExchange.getRequestMethod().equalsIgnoreCase("GET")){
            RequestQueue queue = RequestQueue.sharedInstance();
            Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery());
            Request request = new Request('2', params.get("shortURL"), (ShortenResponse)this);
            queue.addRequest(request);
        }else{
            String err = "Wrong method";
            exchange.sendResponseHeaders(403, err.length());
            OutputStream os = exchange.getResponseBody();
            os.write(err.getBytes());
            os.close();
        }
    }
}
