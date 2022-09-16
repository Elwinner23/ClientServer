import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8000);
             OutputStream stream = socket.getOutputStream();
            OutputStreamWriter  streamWriter = new OutputStreamWriter(stream);
            BufferedWriter writer  = new BufferedWriter(streamWriter);
            BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()));)
        {

            while (true) {
                String response = reader.readLine();
                System.out.println("Response:" + response);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }
}
