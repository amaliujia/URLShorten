import java.util.LinkedList;

/**
 * Created by amaliujia on 15-9-6.
 */
public class RequestQueue {
    public static final RequestQueue singleton = new RequestQueue();

    LinkedList<Request> requests;

    public static synchronized RequestQueue sharedInstance(){
        return singleton;
    }

    public RequestQueue(){
        requests = new LinkedList<Request>();
    }

    public synchronized void addRequest(Request q){
        requests.offer(q);
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException {
        if (requests.size() <= 0){
            wait();
        }

        return requests.poll();
    }
}
