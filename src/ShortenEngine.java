import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by amaliujia on 15-9-6.
 */
public class ShortenEngine {
    // id starts from
    public static AtomicInteger atomicInteger = new AtomicInteger((int)Math.pow(62, 4));

    private URLShortenWorker[] workers;

    public void startService(){
        workers = new URLShortenWorker[10];
        for(int i = 0; i < 10; i++){
            workers[i] = new URLShortenWorker();
            workers[i].run();
        }
    }

}
