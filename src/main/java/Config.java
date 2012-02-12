import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * User: kzimnick
 * Date: 12.02.12
 * Time: 12:15
 */
public class Config {
    private int port = 1234;
    private String filename = "C://temp//test.txt";

    @Inject
    public Config(@Named("port") int port, @Named("filename") String filename) {
        this.port = port;
        this.filename = filename;
    }


    public String getFilename() {
        return filename;
    }

    public int getPort() {
        return port;
    }

}
