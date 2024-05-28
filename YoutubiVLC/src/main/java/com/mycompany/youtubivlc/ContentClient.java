/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.youtubivlc;

/**
 *
 * @author danny
 */
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class ContentClient extends JFrame {
    private final JTextArea textArea;
    private final JLabel imageLabel;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final boolean isPaused = false;
    private static final String IP_ADDRESS = "25.39.111.236";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        new NativeDiscovery().discover(); // Descubre las bibliotecas nativas de VLC
        SwingUtilities.invokeLater(() -> {
            ContentClient client = new ContentClient();
            client.setVisible(true);
        });
    }

    public ContentClient() {
        // Configurar modo nocturno
        setTitle("Cliente de Contenido");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colores para modo nocturno
        Color backgroundColor = Color.DARK_GRAY;
        Color foregroundColor = Color.WHITE;
        Color buttonColor = new Color(60, 63, 65);
        Color buttonHoverColor = new Color(75, 78, 80);
        Color buttonPressedColor = new Color(50, 53, 55);
        Color buttonTextColor = Color.WHITE;

        // Crear botones principales
        JButton musicButton = createButton("Música", buttonColor, buttonHoverColor, buttonPressedColor, buttonTextColor);
        JButton videoButton = createButton("Videos", buttonColor, buttonHoverColor, buttonPressedColor, buttonTextColor);
        JButton documentButton = createButton("Documentos", buttonColor, buttonHoverColor, buttonPressedColor, buttonTextColor);
        JButton imageButton = createButton("Imágenes", buttonColor, buttonHoverColor, buttonPressedColor, buttonTextColor);

        // Configurar eventos de botones
        musicButton.addActionListener(e -> openMusicWindow());
        videoButton.addActionListener(e -> openVideoWindow());
        documentButton.addActionListener(e -> openDocumentWindow());
        imageButton.addActionListener(e -> openImageWindow());

        // Crear el layout y añadir los elementos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(backgroundColor);
        panel.add(musicButton);
        panel.add(videoButton);
        panel.add(documentButton);
        panel.add(imageButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(foregroundColor);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        textArea.setBorder(new EmptyBorder(20, 20, 20, 20)); // Añadir márgenes

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.setBorder(new LineBorder(Color.BLACK));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout principal
        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(imageLabel, BorderLayout.EAST);

        // Inicializar VLCJ
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add(mediaPlayerComponent, BorderLayout.NORTH);
    }

    private JButton createButton(String text, Color background, Color hoverBackground, Color pressedBackground, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(Color.BLACK));
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(20, 40, 20, 40));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(background);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(hoverBackground);
            }
        });

        return button;
    }

    private void openMusicWindow() {
        JFrame musicFrame = new JFrame("Música");
        musicFrame.setSize(1280, 720);
        musicFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        musicFrame.setLocationRelativeTo(null);
        musicFrame.getContentPane().setBackground(Color.DARK_GRAY);

        // Crear botones de música
        JButton mp3Button1 = createButton("Reproducir MP3-1", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);
        JButton mp3Button2 = createButton("Reproducir MP3-2", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);

        // Configurar eventos de botones
        mp3Button1.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "Avispas.mp3", "src/imagenes/Avispas.jpg"));
        mp3Button2.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "My-or.mp3", "src/imagenes/My-or.jpg"));

        // Crear el layout y añadir los elementos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(mp3Button1);
        panel.add(mp3Button2);

        musicFrame.add(panel);
        musicFrame.setVisible(true);
    }

    private void openVideoWindow() {
        JFrame videoFrame = new JFrame("Videos");
        videoFrame.setSize(1280, 720);
        videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        videoFrame.setLocationRelativeTo(null);
        videoFrame.getContentPane().setBackground(Color.DARK_GRAY);

        // Crear botones de videos
        JButton mp4Button1 = createButton("Reproducir MP4-1", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);
        JButton mp4Button2 = createButton("Reproducir MP4-2", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);

        // Configurar eventos de botones
        mp4Button1.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "java.mp4", null));
        mp4Button2.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "JS.mp4", null));

        // Crear el layout y añadir los elementos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(mp4Button1);
        panel.add(mp4Button2);

        videoFrame.add(panel);
        videoFrame.setVisible(true);
    }

    private void openDocumentWindow() {
        JFrame documentFrame = new JFrame("Documentos");
        documentFrame.setSize(1280, 720);
        documentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        documentFrame.setLocationRelativeTo(null);
        documentFrame.getContentPane().setBackground(Color.DARK_GRAY);

        // Crear botones de documentos
        JButton txtButton1 = createButton("Mostrar TXT-1", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);
        JButton txtButton2 = createButton("Mostrar TXT-2", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);

        // Configurar eventos de botones
        txtButton1.addActionListener(e -> openTextWindow(IP_ADDRESS, PORT, "dato.txt"));
        txtButton2.addActionListener(e -> openTextWindow(IP_ADDRESS, PORT, "dato2.txt"));

        // Crear el layout y añadir los elementos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(txtButton1);
        panel.add(txtButton2);

        documentFrame.add(panel);
        documentFrame.setVisible(true);
    }

    private void openTextWindow(String ipAddress, int port, String fileName) {
        JFrame textFrame = new JFrame("Texto");
        textFrame.setSize(1280, 720);
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        textArea.setBorder(new EmptyBorder(20, 20, 20, 20)); // Añadir márgenes

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new LineBorder(Color.BLACK));

        try (Socket socket = new Socket(ipAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(fileName);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            textArea.setText(response.toString());

        } catch (IOException e) {
        }

        textFrame.add(scrollPane);
        textFrame.setVisible(true);
    }

    private void openImageWindow() {
        JFrame imageFrame = new JFrame("Imágenes");
        imageFrame.setSize(1280, 720);
        imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imageFrame.setLocationRelativeTo(null);
        imageFrame.getContentPane().setBackground(Color.DARK_GRAY);

        // Crear botones de imágenes
        JButton jpgButton1 = createButton("Mostrar JPG-1", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);
        JButton jpgButton2 = createButton("Mostrar JPG-2", new Color(60, 63, 65), new Color(75, 78, 80), new Color(50, 53, 55), Color.WHITE);

        // Configurar eventos de botones
        jpgButton1.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "dog.jpg", null));
        jpgButton2.addActionListener(e -> openContentWindow(IP_ADDRESS, PORT, "ryu.jpg", null));

        // Crear el layout y añadir los elementos
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(jpgButton1);
        panel.add(jpgButton2);

        imageFrame.add(panel);
        imageFrame.setVisible(true);
    }

    private void openContentWindow(String ipAddress, int port, String fileName, String imagePath) {
        JFrame contentFrame = new JFrame("Contenido");
        contentFrame.setSize(1280, 720);
        contentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentFrame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);

        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        contentTextArea.setFont(new Font("Serif", Font.PLAIN, 18));
        contentTextArea.setBorder(new EmptyBorder(20, 20, 20, 20)); // Añadir márgenes

        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentScrollPane.setBorder(new LineBorder(Color.BLACK));

        JLabel contentImageLabel = new JLabel();
        contentImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (imagePath != null) {
            ImageIcon icon = new ImageIcon(imagePath);
            contentImageLabel.setIcon(icon);
            contentPanel.add(contentImageLabel, BorderLayout.EAST);
        }

        try (Socket socket = new Socket(ipAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(fileName);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            contentTextArea.setText(response.toString());

        } catch (IOException e) {
        }

        contentPanel.add(contentScrollPane, BorderLayout.CENTER);
        contentFrame.add(contentPanel);
        contentFrame.setVisible(true);
    }
}
