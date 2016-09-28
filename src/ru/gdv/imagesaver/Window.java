package ru.gdv.imagesaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Window extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTabbedPane tabbedPane;
    private JTextArea textArea;
    private JButton btnImage;
    private JButton btnFile;
    private Choice choice;
    private JLabel lFormat;
    private JLabel imageLabel;
    private JButton btnView;
    private JMenuBar menuBar;
    private JMenu jMenu_1;
    private JMenu jMenu_2;
    private JMenuItem menuItem_1;
    private JMenuItem menuItem_2;
    private JScrollPane scrollPane;

    public Window(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(635, 475);
        getContentPane().setLayout(null);

        panel1 = new JPanel(null);
        panel2 = new JPanel(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 80, 610, 340);
        getContentPane().add(tabbedPane);
        setResizable(false);
        tabbedPane.addTab("Основное меню", panel1);

        textArea = new JTextArea();
        textArea.setText("Пожалуйста, введите URL");
        textArea.setBounds(10, 40, 580, 330);
        panel1.add(textArea);

        //выкидывает exception если строка адреса выходит за пределы textArea
        btnImage = new JButton("Загрузить картинку");
        btnImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.setImage(new URL(textArea.getText()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Неверная ссылка.");
                }
            }
        });
        btnImage.setBounds(10, 10, 150, 25);

        btnFile = new JButton("Загрузить файл");
        btnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int ch = chooser.showOpenDialog(null);
                if(ch == JFileChooser.APPROVE_OPTION){
                    Main.setImage(chooser.getSelectedFile());
                }
            }
        });
        btnFile.setBounds(430, 10, 150, 25);

        panel1.add(btnImage);
        panel1.add(btnFile);


        tabbedPane.addTab("Загрузить файл", panel2);
        choice = new Choice();
        choice.setBounds(510, 10, 60, 20);
        choice.add("png");
        choice.add("jpg");

        lFormat = new JLabel("Выбрать формат: ");
        lFormat.setBounds(400, 10, 120, 20);
        btnView = new JButton("Просмотр:");
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.getImage()==null){return;}
                imageLabel.setIcon(new ImageIcon(Main.getImage()));
                imageLabel.updateUI();
            }
        });

        btnView.setBounds(10, 15, 150, 30);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 50, 580, 260);


        panel3 = new JPanel();
        scrollPane.setViewportView(panel3);
        panel3.setLayout(new BorderLayout(0, 0));

        imageLabel = new JLabel("");
        panel3.add(imageLabel, BorderLayout.CENTER);

        panel2.add(choice);
        panel2.add(lFormat);
        panel2.add(btnView);
        panel2.add(scrollPane);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 635, 21);
        getContentPane().add(menuBar);

        jMenu_1 = new JMenu("Файл");
        jMenu_2 = new JMenu("Опции");
        menuItem_1 = new JMenuItem("Сохранить");
        menuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int ch = chooser.showSaveDialog(null);
                if(ch == JFileChooser.APPROVE_OPTION){
                    Main.saveImage(chooser.getSelectedFile(), choice.getSelectedItem());
                }
            }
        });
        menuItem_2 = new JMenuItem("Выйти");
        menuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenu_1.add(menuItem_1);
        jMenu_2.add(menuItem_2);
        menuBar.add(jMenu_1);
        menuBar.add(jMenu_2);

        setVisible(true);

    }
}
