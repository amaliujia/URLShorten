package Test;

/**
 * Created by amaliujia on 15-9-13.
 */
public class LoadTest {

    public long []counts;

    public LoadTest(){
        counts = new long[1];
    }

    public static void main(String[] args){
        LoadTest test = new LoadTest();
        for(int i = 0; i < 1; i++){
            Client client = new Client(i, test);
            client.run();
        }

        long start_time = System.currentTimeMillis();

        while (true){
            if(System.currentTimeMillis() - start_time >= 1000){
                long c = 0;
                for(int i = 0; i < test.counts.length; i++){
                    c += test.counts[i];
                }

                System.out.println(c);
                start_time = System.currentTimeMillis();
            }
        }
    }
}
