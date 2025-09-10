package login;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class client extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public client() {
        setTitle("Login System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(e -> sendRequest("LOGIN"));
        registerButton.addActionListener(e -> sendRequest("REGISTER"));
    }

    private void sendRequest(String action) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(action + "|" + username + "|" + password);
            String response = in.readLine();

            if ("SUCCESS".equalsIgnoreCase(response)) {
                JOptionPane.showMessageDialog(this, action + " thành công!");
            } else {
                JOptionPane.showMessageDialog(this, action + " thất bại!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối đến server.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new client().setVisible(true));
    }
}
