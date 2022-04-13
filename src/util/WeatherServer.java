package util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WeatherServer extends JFrame {
    JPanel mainPanel;  // smart o lägga utanför?
    static JTextArea textArea;

    WeatherServer() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(500,400);
        textArea = new JTextArea(10, 25);
        mainPanel.add(textArea, BorderLayout.CENTER);
        add(mainPanel);

        setSize(500, 400);
        setLocation(1000, 500);
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws IOException {
        new WeatherServer();
        int myPort = 55555;
        DatagramSocket socket = new DatagramSocket(myPort);
        byte[] inData = new byte[256];

        while(true) {
            DatagramPacket packet = new DatagramPacket(inData, inData.length);
            socket.receive(packet);
            System.out.println("Message from " + packet.getAddress().getHostAddress());
            String message = new String(packet.getData(), 0, packet.getLength());
            textArea.append(message);
            textArea.append("\n");
        }
    }
}
