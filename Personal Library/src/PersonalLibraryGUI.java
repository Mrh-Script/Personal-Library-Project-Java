import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PersonalLibraryGUI extends JFrame {
    private LibraryManager manager = new LibraryManager();
    private JLabel totalLabel;
    private JLabel remainingLabel;

    public PersonalLibraryGUI() {
        setTitle("Personal Book Library");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(Color.decode("#000000"));

        JLabel welcomeLabel = new JLabel("Riad's Book Corner", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Cambria", Font.PLAIN, 40));
        welcomeLabel.setForeground(Color.decode("#F4CBC6"));

        JButton islamicBtn = new JButton("Islamic Corner");
        islamicBtn.setFont(new Font("Cambria", Font.PLAIN, 30));
        islamicBtn.setForeground(Color.decode("#FFFF00"));
        JButton historyBtn = new JButton("History Corner");
        historyBtn.setFont(new Font("Cambria", Font.PLAIN, 30));
        historyBtn.setForeground(Color.decode("#FFFFFF"));
        JButton othersBtn = new JButton("Edu & Others Corner");
        othersBtn.setFont(new Font("Cambria", Font.PLAIN, 30));
        othersBtn.setForeground(Color.decode("#000000"));

        islamicBtn.setBackground(Color.decode("#3F704D"));
        historyBtn.setBackground(Color.decode("#550816"));
        othersBtn.setBackground(Color.decode("#76BA9D"));

        islamicBtn.addActionListener(e -> openSection("Islamic"));
        historyBtn.addActionListener(e -> openSection("History"));
        othersBtn.addActionListener(e -> openSection("Others"));

        totalLabel = new JLabel("Total Books: 0", SwingConstants.LEFT);
        totalLabel.setBackground(Color.BLACK);
        totalLabel.setOpaque(true);
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Cambria", Font.PLAIN, 14));

        remainingLabel = new JLabel("Remaining Books: 0", SwingConstants.LEFT);
        remainingLabel.setBackground(Color.BLACK);
        remainingLabel.setOpaque(true);
        remainingLabel.setForeground(Color.WHITE);
        remainingLabel.setFont(new Font("Cambria", Font.PLAIN, 14));


        panel.add(welcomeLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 5));
        buttonPanel.add(islamicBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(othersBtn);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        infoPanel.add(totalLabel);
        infoPanel.add(remainingLabel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.SOUTH);

        panel.add(centerPanel, BorderLayout.CENTER);

        JLabel creditLabel = new JLabel("Powered by : Mrh-Script", SwingConstants.CENTER);
        creditLabel.setFont(new Font("Cambria", Font.PLAIN, 12));
        creditLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(creditLabel, BorderLayout.SOUTH);

        add(panel);
        updateLabels();
    }

    private void updateLabels() {
        totalLabel.setText("Total Books: " + manager.getTotalBooks());
        remainingLabel.setText("Remaining Books: " + manager.getRemainingBooks());
    }

    private void openSection(String section) {
        JFrame frame = new JFrame(section + " Section");
        frame.setSize(550, 450);
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2, 3, 3, 3));
        topPanel.setBackground(Color.decode("#1A4645"));

        JButton addBtn = new JButton("Add Book");
        addBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        addBtn.setForeground(Color.decode("#000000"));
        addBtn.setBackground(Color.decode("#94DEA5"));

        JButton giveBtn = new JButton("Give Book");
        giveBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        giveBtn.setForeground(Color.decode("#000000"));
        giveBtn.setBackground(Color.decode("#94DEA5"));

        JButton returnBtn = new JButton("Return Book");
        returnBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        returnBtn.setForeground(Color.decode("#000000"));
        returnBtn.setBackground(Color.decode("#94DEA5"));

        JButton listBtn = new JButton("List Books");
        listBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        listBtn.setForeground(Color.decode("#000000"));
        listBtn.setBackground(Color.decode("#94DEA5"));

        JButton removeBtn = new JButton("Remove Book");
        removeBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        removeBtn.setForeground(Color.decode("#000000"));
        removeBtn.setBackground(Color.decode("#94DEA5"));

        JButton homeBtn = new JButton("Home");
        homeBtn.setFont(new Font("Cambria", Font.PLAIN, 15));
        homeBtn.setForeground(Color.decode("#000000"));
        homeBtn.setBackground(Color.decode("#94DEA5"));


        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Cambria", Font.PLAIN, 15));
        area.setForeground(Color.WHITE);
        area.setBackground(Color.decode("#000000"));
        JScrollPane scrollPane = new JScrollPane(area);

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Enter Book Name:");
            if (name == null || name.trim().isEmpty()) return;
            String writer = JOptionPane.showInputDialog(frame, "Enter Writer's Name:");
            if (writer == null || writer.trim().isEmpty()) return;
            manager.addBook(section, name.trim(), writer.trim());
            updateLabels();
        });

        giveBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(frame, "Enter Serial Number:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int id = Integer.parseInt(idStr.trim());
            String person = JOptionPane.showInputDialog(frame, "Enter Person's Name:");
            if (person == null || person.trim().isEmpty()) return;
            boolean success = manager.giveBook(section, id, person);
            JOptionPane.showMessageDialog(frame, success ? "Book given." : "Not found or already given.");
            updateLabels();
        });

        returnBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(frame, "Enter Serial Number to Return:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int id = Integer.parseInt(idStr.trim());
            boolean success = manager.returnBook(section, id);
            JOptionPane.showMessageDialog(frame, success ? "Book returned." : "Not found or not given.");
            updateLabels();
        });

        removeBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(frame, "Enter Serial Number to Remove:");
            if (idStr == null || idStr.trim().isEmpty()) return;
            int id = Integer.parseInt(idStr.trim());
            boolean success = manager.removeBook(section, id);
            JOptionPane.showMessageDialog(frame, success ? "Book removed permanently." : "Book not found.");
            updateLabels();
        });

        listBtn.addActionListener(e -> {
            area.setText("");
            for (Book book : manager.getBooks(section)) {
                area.append(book + "\n");
            }
        });

        homeBtn.addActionListener(e -> frame.dispose());

        topPanel.add(addBtn);
        topPanel.add(giveBtn);
        topPanel.add(returnBtn);
        topPanel.add(listBtn);
        topPanel.add(removeBtn);
        topPanel.add(homeBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonalLibraryGUI().setVisible(true));
    }
}