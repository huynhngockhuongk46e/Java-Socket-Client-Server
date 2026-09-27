package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastSender {

    public static final String GROUP_ADDRESS =
            "230.0.0.1";

    public static final int PORT = 8888;

    public static void main(String[] args)
            throws InterruptedException {

        DatagramSocket socket = null;

        try {

            InetAddress address =
                    InetAddress.getByName(GROUP_ADDRESS);

            socket = new DatagramSocket();

            long counter = 0;

            while (true) {

                String message =
                        "Message number " + counter;

                counter++;

                byte[] data =
                        message.getBytes();

                DatagramPacket packet =
                        new DatagramPacket(
                            data,
                            data.length,
                            address,
                            PORT
                        );

                socket.send(packet);

                System.out.println(
                    "Sent: " + message
                );

                Thread.sleep(1000);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (socket != null) {
                socket.close();
            }
        }
    }
}