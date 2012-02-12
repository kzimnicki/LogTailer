import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

/**
 * User: kzimnick
 * Date: 10.02.12
 * Time: 11:10
 */
public class Main extends AbstractModule {

    public static void main(String args[]) throws Exception {
        Injector injector = Guice.createInjector(new Main());
        Server server = injector.getInstance(Server.class);
    }

    @Override
    protected void configure() {
        bind(LogTailer.class);
        bind(Server.class);
        bind(Config.class);
        Names.bindProperties(binder(), System.getProperties());
    }
}
