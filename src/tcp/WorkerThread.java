package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerThread extends Thread {

    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println(
            "Processing: " + socket
        );

        try {

            InputStream is = socket.getInputStream();

            OutputStream os = socket.getOutputStream();

            while (true) {

                // Nhận dữ liệu
                int ch = is.read();

                // Client ngắt kết nối
                if (ch == -1) {
                    break;
                }

                // Gửi dữ liệu trở lại
                os.write(ch);
            }

        } catch (IOException e) {

            System.err.println(
                "Request Processing Error: " + e
            );

        } finally {

            try {

                socket.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        System.out.println(
            "Complete processing: " + socket
        );
    }
}