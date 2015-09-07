import redis.clients.jedis.Jedis;

/**
 * Created by amaliujia on 15-9-6.
 */
public class URLShortenWorker implements Runnable {

    private RequestQueue queue = RequestQueue.sharedInstance();
    private Jedis jedis = new Jedis("10.211.55.13", 6379);

    public void run() {
        while (true){
            try {
                Request r = queue.getRequest();
                if (r.getType() == '1'){
                    LongToShort(r);
                }else{
                    ShortToLong(r);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void LongToShort(Request request){
        int id = ShortenEngine.atomicInteger.getAndIncrement();
        String shortURL = getShortURL(id);
        jedis.set(request.getContent(), shortURL);
        request.getCallback().responesString(shortURL);
    }

    private void ShortToLong(Request request){
        String result = jedis.get(request.getContent());
        request.getCallback().responesString(result);
    }

    private String getShortURL(int number){
        char []Encode = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P', 'Q','R','S','T','U','V','W','X','Y','Z'};
        String ret = "";

        while (number != 0){
            ret += Encode[number % 62];
            number /= 62;
        }
        return ret;
    }
}
