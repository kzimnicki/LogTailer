import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * User: kzimnick
 * Date: 11.02.12
 * Time: 16:45
 */
@Singleton
public class LogTailer extends Thread {

    public static final String READ_FLAG = "r";
    public static final int QUEUE_CAPACITY = 90;
    private LinkedList<String> queue = new LinkedList<String>();
    private RandomAccessFile br;

    private Config config;

    @Inject
    public LogTailer(Config config) {
        this.config = config;
        init();
    }


    private void init() {
        try {
            br = new RandomAccessFile(config.getFilename(), READ_FLAG);
            long l = br.length();
            br.seek(l - 30); // TODO: IOException: Negative seek offset - jezeli bedzei za duzy
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        start();
    }

    public void run() {
        String currentLine = null;
        while (true) {
            currentLine = readLine();
            if (currentLine == null) {
                sleep(400);
            } else {
                addToQueue(currentLine);
            }
        }
    }

    private void addToQueue(String currentLine) {
        queue.addFirst(currentLine);
        if (queue.size() > QUEUE_CAPACITY) {
            queue.removeLast();
        }
    }

    private void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLogs() {
        StringBuilder builder = new StringBuilder();
        for (int i = queue.size() - 1; i >= 0; i--) {
            builder.append(queue.get(i)).append("\n");
        }
        return builder.toString();
    }
}
