package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

    public static final int SERVER_PORT = 7778;

    public static final byte[] BUFFER =
            new byte[4096];

    public static void main(String[] args) {

        DatagramSocket ds = null;

        try {

            System.out.println(
                "Binding to port " + SERVER_PORT
            );

            // Tạo UDP Socket
            ds = new DatagramSocket(SERVER_PORT);

            System.out.println(
                "UDP Server started."
            );

            System.out.println(
                "Waiting for messages..."
            );

            while (true) {

                // Tạo packet để nhận
                DatagramPacket incoming =
                        new DatagramPacket(
                            BUFFER,
                            BUFFER.length
                        );

                // Chờ nhận dữ liệu
                ds.receive(incoming);

                // Chuyển byte thành String
                String message =
                        new String(
                            incoming.getData(),
                            0,
                            incoming.getLength()
                        );

                System.out.println(
                    "Received: " + message
                );

                // Tạo packet trả về
                byte[] data =
                        message.getBytes();

                DatagramPacket outgoing =
                        new DatagramPacket(
                            data,
                            data.length,
                            incoming.getAddress(),
                            incoming.getPort()
                        );

                // Gửi lại Client
                ds.send(outgoing);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (ds != null) {
                ds.close();
            }
        }
    }
}