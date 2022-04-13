package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class WeatherReporter extends JFrame implements ActionListener {
    static String city;
    static int temperature;
    JPanel reporterPanel = new JPanel();
    static JLabel cityLabel = new JLabel();
    JTextField userInput = new JTextField(25);
    JButton reportButton = new JButton("Report");
    static String message;
    InetAddress toAddress = InetAddress.getLocalHost();
    int toPort = 55555;
    DatagramSocket socket = new DatagramSocket();

    WeatherReporter() throws UnknownHostException, SocketException {
        reporterPanel.setLayout(new FlowLayout());
        reporterPanel.add(cityLabel);
        reporterPanel.add(userInput);
        reporterPanel.add(reportButton);
        reportButton.addActionListener(this);
        add(reporterPanel);
        pack();
        setLocation(1000, 500);
        setVisible(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) throws IOException {
        city = JOptionPane.showInputDialog("Enter city name: ");
        cityLabel.setText("Temperature for " + city + ":");
        new WeatherReporter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        temperature = Integer.parseInt(userInput.getText());
        message = city + ", " + temperature;
        System.out.println(message);   // ta bort
        if (message.equals("exit") || message.equals("Exit")) {
            socket.close();
            System.exit(0);
        }
        byte[] outData = message.getBytes();
        DatagramPacket packet = new DatagramPacket(outData, outData.length, toAddress, toPort);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}
