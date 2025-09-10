package login;

import java.io.*;
import java.net.*;

public class server {
    private static final int PORT = 12345;
    private static final String USER_FILE = "users.txt";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server đang chạy trên cổng " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Kết nối mới: " + socket.getInetAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String request = in.readLine();
                System.out.println("Yêu cầu từ client: " + request);

                if (request != null) {
                    String[] parts = request.split("\\|");
                    String action = parts[0];
                    String username = parts[1];
                    String password = parts[2];

                    if ("LOGIN".equalsIgnoreCase(action)) {
                        if (checkLogin(username, password)) {
                            out.println("SUCCESS");
                        } else {
                            out.println("FAIL");
                        }
                    } else if ("REGISTER".equalsIgnoreCase(action)) {
                        if (registerUser(username, password)) {
                            out.println("SUCCESS");
                        } else {
                            out.println("FAIL");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean checkLogin(String username, String password) throws IOException {
            File file = new File(USER_FILE);
            if (!file.exists()) return false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean registerUser(String username, String password) throws IOException {
            File file = new File(USER_FILE);
            if (!file.exists()) file.createNewFile();

            // Kiểm tra trùng tên
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username)) {
                        return false; // username tồn tại
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(username + "," + password);
                writer.newLine();
            }
            return true;
        }
    }
}
