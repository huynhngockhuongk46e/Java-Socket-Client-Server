package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {

    public static final String SERVER_IP =
            "127.0.0.1";

    public static final int SERVER_PORT = 7778;

    public static final byte[] BUFFER =
            new byte[4096];

    public static void main(String[] args) {

        DatagramSocket ds = null;

        try {

            // Tạo UDP Socket
            ds = new DatagramSocket();

            System.out.println(
                "UDP Client started."
            );

            InetAddress server =
                    InetAddress.getByName(SERVER_IP);

            BufferedReader br =
                    new BufferedReader(
                        new InputStreamReader(System.in)
                    );

            while (true) {

                System.out.print(
                    "Enter your message: "
                );

                String message =
                        br.readLine();

                // Nếu gõ exit thì thoát
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                byte[] data =
                        message.getBytes();

                // Tạo packet gửi
                DatagramPacket dp =
                        new DatagramPacket(
                            data,
                            data.length,
                            server,
                            SERVER_PORT
                        );

                // Gửi đến Server
                ds.send(dp);

                // Tạo packet nhận
                DatagramPacket incoming =
                        new DatagramPacket(
                            BUFFER,
                            BUFFER.length
                        );

                // Chờ Server trả lời
                ds.receive(incoming);

                String response =
                        new String(
                            incoming.getData(),
                            0,
                            incoming.getLength()
                        );

                System.out.println(
                    "Received: " + response
                );
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