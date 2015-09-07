import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amaliujia on 15-9-7.
 */
public abstract class URLHandler implements ShortenResponse, HttpHandler{
    protected HttpExchange exchange;

    public void responesString(String content) {
        try {
            if(content == "nil"){
                content = "Invalid short url";
            }
            exchange.sendResponseHeaders(200, content.length());
            OutputStream os = exchange.getResponseBody();
            os.write(content.getBytes());
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    protected Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
