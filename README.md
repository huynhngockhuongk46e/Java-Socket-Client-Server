# Client-Server Socket Programming in Java

## 1. Giới thiệu

Bài thực hành tìm hiểu lập trình Client-Server bằng Socket trong Java.

Nội dung thực hành dựa trên bài viết:

https://gpcoder.com/3679-xay-dung-ung-dung-client-server-voi-socket-trong-java/

## 2. Nội dung thực hiện

### TCP

* TCP Client
* TCP Server phục vụ tuần tự
* TCP Server phục vụ song song
* WorkerThread xử lý Client

### UDP

* UDP Echo Server
* UDP Echo Client

### Multicast

* Multicast Sender
* Multicast Receiver

## 3. Công nghệ sử dụng

* Java
* Java Socket
* TCP
* UDP
* Multicast
* VS Code
* Git
* GitHub

## 4. Cấu trúc project

```text
SocketClientServer
│
├── src
│   ├── tcp
│   │   ├── EchoChatClient.java
│   │   ├── EchoChatSingleServer.java
│   │   ├── EchoChatMultiServer.java
│   │   └── WorkerThread.java
│   │
│   ├── udp
│   │   ├── EchoServer.java
│   │   └── EchoClient.java
│   │
│   └── multicast
│       ├── MulticastSender.java
│       └── MulticastReceiver.java
│
├── screenshot
│
└── README.md
```

## 5. TCP

TCP sử dụng Socket và ServerSocket để thiết lập kết nối giữa Client và Server.

Client gửi dữ liệu đến Server.

Server nhận dữ liệu và gửi dữ liệu trở lại Client.

Kết quả:

```text
0 1 2 3 4 5 6 7 8 9
```

## 6. TCP Server song song

Server sử dụng Thread Pool để xử lý nhiều Client.

Mỗi Client được giao cho một WorkerThread xử lý.

## 7. UDP

UDP sử dụng:

* DatagramSocket
* DatagramPacket

Client gửi message đến Server.

Server nhận message và gửi message trở lại Client.

## 8. Multicast

Multicast cho phép nhiều Receiver tham gia cùng một group và nhận các message được Sender gửi đến group.

## 9. Kết quả

Các chương trình đã được biên dịch và chạy thử trên môi trường:

* Windows
* Java
* Visual Studio Code

Ảnh chụp quá trình thực hiện được lưu trong thư mục `screenshot`.

## 10. Tài liệu tham khảo

GP Coder - Xây dựng ứng dụng Client-Server với Socket trong Java:

https://gpcoder.com/3679-xay-dung-ung-dung-client-server-voi-socket-trong-java/
