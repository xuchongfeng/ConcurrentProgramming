package Day1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		class ConnectionHandler implements Runnable {
			InputStream in;
			OutputStream out;
			public ConnectionHandler(Socket socket) throws IOException {
				in = socket.getInputStream();
				out = socket.getOutputStream();
			}
			public void run() {
				int n;
				try {
					byte[] buffer = new byte[1024];
					while((n = in.read(buffer)) != -1){
						out.write(buffer, 0, n);
						out.flush();
					}
				} catch (IOException e) {
					
				}
			}
		}
		
		ServerSocket server = new ServerSocket(2345);
		while(true) {
			Socket client;
			client = server.accept();
			Thread handler = new Thread(new ConnectionHandler(client));
			handler.start();
		}
	}

}
