package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoChatSingleServer {

    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {

            System.out.println(
                "Binding to port " + SERVER_PORT + ", please wait..."
            );

            // Tạo ServerSocket
            serverSocket = new ServerSocket(SERVER_PORT);

            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client...");

            while (true) {

                try {

                    // Chờ Client kết nối
                    Socket socket = serverSocket.accept();

                    System.out.println(
                        "Client accepted: " + socket
                    );

                    // Luồng nhận dữ liệu
                    InputStream is = socket.getInputStream();

                    // Luồng gửi dữ liệu
                    OutputStream os = socket.getOutputStream();

                    while (true) {

                        // Nhận dữ liệu từ Client
                        int ch = is.read();

                        // Client đóng kết nối
                        if (ch == -1) {
                            break;
                        }

                        // Gửi lại dữ liệu cho Client
                        os.write(ch);
                    }

                    socket.close();

                    System.out.println("Client disconnected.");

                } catch (IOException e) {

                    System.err.println(
                        "Connection Error: " + e
                    );
                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (serverSocket != null) {
                    serverSocket.close();
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}