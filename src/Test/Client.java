package Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by amaliujia on 15-9-13.
 */
public class Client implements Runnable {
    private int client_id;
    private int next_id;
    private long counter;
    private LoadTest loadTest;
    private static String fix_prefix= "longURL=http://127.0.0.1:8000/insert/";

    public Client(int client_id, LoadTest test){
        this.client_id = client_id;
        this.next_id = 0;
        this.counter = 0;

        fix_prefix += this.client_id;
        fix_prefix += "/";

        loadTest = test;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        while (true){
            try {
                String rawData = getNextURL();
                String type = "application/x-www-form-urlencoded";
                String encodedData = URLEncoder.encode(rawData);
                URL u = new URL("http://127.0.0.1:8000/insert");
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", type);
                conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
                OutputStream os = null;
                os = conn.getOutputStream();
                os.write(encodedData.getBytes());
                counter++;
                if(System.currentTimeMillis() - startTime >= 1000){
                    //System.out.println(client_id + " " + counter);
                    loadTest.counts[client_id] = counter;
                    counter = 0;
                    startTime = System.currentTimeMillis();
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
        }

    }

    private String getNextURL(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(fix_prefix);
        buffer.append(next_id++);
        return buffer.toString();
    }

    public static void main(String[] args){
        Client client = new Client(0, null);
        client.run();
    }
}
