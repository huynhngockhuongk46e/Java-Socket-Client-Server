package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoChatMultiServer {

    // Số lượng Thread tối đa
    public static final int NUM_OF_THREAD = 4;

    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {

        // Tạo Thread Pool gồm 4 Thread
        ExecutorService executor =
                Executors.newFixedThreadPool(NUM_OF_THREAD);

        ServerSocket serverSocket = null;

        try {

            System.out.println(
                "Binding to port " + SERVER_PORT
            );

            serverSocket = new ServerSocket(SERVER_PORT);

            System.out.println(
                "Server started: " + serverSocket
            );

            System.out.println(
                "Waiting for clients..."
            );

            while (true) {

                try {

                    // Chờ Client
                    Socket socket =
                            serverSocket.accept();

                    System.out.println(
                        "Client accepted: " + socket
                    );

                    // Tạo Worker để xử lý Client
                    WorkerThread worker =
                            new WorkerThread(socket);

                    // Đưa Worker vào Thread Pool
                    executor.execute(worker);

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

            executor.shutdown();
        }
    }
}