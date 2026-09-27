package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {

    public static final byte[] BUFFER =
            new byte[4096];

    public static void main(String[] args) {

        MulticastSocket socket = null;

        try {

            InetAddress group =
                    InetAddress.getByName(
                        MulticastSender.GROUP_ADDRESS
                    );

            socket =
                    new MulticastSocket(
                        MulticastSender.PORT
                    );

            // Tham gia group
            socket.joinGroup(group);

            System.out.println(
                "Receiver joined multicast group."
            );

            while (true) {

                DatagramPacket packet =
                        new DatagramPacket(
                            BUFFER,
                            BUFFER.length
                        );

                // Chờ nhận message
                socket.receive(packet);

                String message =
                        new String(
                            packet.getData(),
                            0,
                            packet.getLength()
                        );

                System.out.println(
                    "Received: " + message
                );
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