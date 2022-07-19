package qa8;

import qa7.InMemoryTaskManager;
import qa8.http.Server;

import java.io.IOException;

public class QA8 {

    public static void main(String[] args) throws IOException {
        Server server = new Server(new InMemoryTaskManager());
        server.start();

//        server.stop();
    }


}
