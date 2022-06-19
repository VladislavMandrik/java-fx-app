package ru.gb.javafxapplesson4.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final ChatController controller;

    public ChatClient(ChatController controller) {
        this.controller = controller;
    }

    public void openConnection() throws IOException {
        socket = new Socket("localhost", 8191);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                waitAuth();
                readMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();
    }

    private void waitAuth() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.startsWith("/authok")) {
                String[] split = message.split("\\p{Blank}+");
                String nick = split[1];
                controller.setAuth(true);
                controller.addMessage("Успешная авторизация под ником " + nick);
                break;
            }
        }
    }

    private void closeConnection() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = in.readUTF();
            if ("/end".equalsIgnoreCase(message)) {
                controller.setAuth(false);
                break;
            }
            controller.addMessage(message);
            System.out.println("Сообщение от сервера: " + message);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

