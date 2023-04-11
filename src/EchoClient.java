import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {

        String hostName = "127.0.0.1";
        int portNumber = 6666;

        try {

            Socket socket = new Socket(hostName, portNumber);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());

            String run = "yes";
            while (!run.equals("no")) {
                System.out.println("Ingrese la expresion matematica a evaluar: ");

                // The scanner reads the user input and
                Scanner user_input = new Scanner(System.in);
                dout.writeUTF(user_input.nextLine());

                // The client reads the response of the server and prints it out
                String str = din.readUTF();
                System.out.println("El resultado es: " + str);

                System.out.println("Desea realizar otro calculo? [si/no]: ");
                run = user_input.nextLine();

                // If the user wants to close th connection it exits the loop
                if (run.equals("no")) {
                    System.out.println("Cerrando conexion con el servidor...");
                    dout.writeUTF(run);
                }
            }

            // Closes the connection to the server

            dout.close();
            din.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
