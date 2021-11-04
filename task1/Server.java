import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {

	private ServerSocket serverSocket;
	private ArrayList<Socket> clients;
	private Object lock;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.clients = new ArrayList<Socket>();
		this.lock = new Object();
	}

	public void acceptClients() throws IOException {
		while(true) {
			Socket client = serverSocket.accept();
			Thread t = new Thread(new ClientConnection(client, clients, lock));
			synchronized(lock) {
				clients.add(client);
			}
			t.start();
		}
	}

	class ClientConnection implements Runnable {

		Socket client;
		ArrayList<Socket> clients;
		Object lock;
		byte[] buffer;
		InputStream inStream;

		public ClientConnection(Socket client, ArrayList<Socket> clients, Object lock) throws IOException {
			this.client = client;
			this.clients = clients;
			this.lock = lock;
			this.buffer = new byte[1024];
			this.inStream = client.getInputStream();
		}

		@Override
		public void run() {
			int read;
			try {
				while((read = inStream.read(buffer)) != -1) {
					String out = new String(buffer, 0, read);
					System.out.print(out);
					System.out.flush();
					printToClients(out.getBytes());
				}
				synchronized(lock) {
					clients.remove(client);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		private void printToClients(byte[] bytes) throws IOException {
			synchronized(lock) {
				for(Socket s : clients) {
					if(s != client) {
						s.getOutputStream().write(bytes);
						s.getOutputStream().flush();	
					}
				}
			}
		}
	}	
	
	public static void main(String[] args) throws IOException {
		int port = 1337;
		if(args.length > 0)
			port = Integer.parseInt(args[0]);
		Server server = new Server(port);

		server.acceptClients();
	}
}
