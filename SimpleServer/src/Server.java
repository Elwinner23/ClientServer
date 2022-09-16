import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Server {
    public static String phrase() throws FileNotFoundException {
        String path = "C:\\Users\\Elwinner\\IdeaProjects\\ClientServerSocket\\SimpleServer\\src\\phrases";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        int lines = 0;
        while (scanner.hasNextLine()){
            lines += 1;
            scanner.nextLine();
        }

        Random random = new Random();
        int random_num = random.nextInt(lines-1) + 1;

        Scanner new_scanner = new Scanner(file);
        String result = "";
        int i = 1;
        while (new_scanner.hasNextLine()) {
            String str = "";
            str = new_scanner.nextLine();
            if (i == random_num) {
                result = str;
                break;
            }
            ++i;
        }
        return result;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(phrase());

        ServerSocket server = new ServerSocket(8000);
        System.out.println("Server started!");

        Socket socket = server.accept();
        System.out.println("Client connected");

        OutputStream stream = socket.getOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

        BufferedWriter writer = new BufferedWriter(streamWriter);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        while (true){
            writer.write(phrase());
            writer.newLine();
            writer.flush();
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
