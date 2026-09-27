package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoChatClient {

    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {

        Socket socket = null;

        try {

            // Kết nối đến Server
            socket = new Socket(SERVER_IP, SERVER_PORT);

            System.out.println("Connected to server: " + socket);

            // Lấy luồng nhận dữ liệu
            InputStream is = socket.getInputStream();

            // Lấy luồng gửi dữ liệu
            OutputStream os = socket.getOutputStream();

            // Gửi các số từ 0 đến 9
            for (int i = '0'; i <= '9'; i++) {

                // Gửi dữ liệu đến Server
                os.write(i);

                // Chờ Server trả kết quả
                int ch = is.read();

                // Hiển thị kết quả
                System.out.print((char) ch + " ");

                Thread.sleep(200);
            }

        } catch (IOException e) {

            System.out.println("Can't connect to server.");

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            try {

                if (socket != null) {
                    socket.close();
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}