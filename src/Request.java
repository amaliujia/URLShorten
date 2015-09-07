/**
 * Created by amaliujia on 15-9-6.
 */
public class Request {
    private char type;
    private String content;

    private ShortenResponse callback;

    /**
     * Constructor
     * @param t
     *          type of request
     *          1, long url to short (write)
     *          2, short url to long (read)
     * @param c
     */
    public Request(char t, String c, ShortenResponse response){
        this.type = t;
        this.content = c;
        this.callback = response;
    }

    public ShortenResponse getCallback(){
        return callback;
    }

    public char getType(){
        return type;
    }

    public String getContent(){
        return content;
    }
}
