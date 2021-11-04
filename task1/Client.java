import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class Client {

	private Socket clientSocket;

	public Client(int port, String hostName) throws IOException {
		this.clientSocket = new Socket(hostName, port);
	}

	public void joinChat() throws IOException, InterruptedException {
		Thread t = new Thread(new ServerReader(clientSocket));
		t.start();

		byte[] b = new byte[1024];
		int read;
		while(t.isAlive()) {
			read = System.in.read(b);
			clientSocket.getOutputStream().write(b, 0, read);
			clientSocket.getOutputStream().flush();
		}
		t.join();
	}

	private class ServerReader implements Runnable {

		private InputStream inStream;

		public ServerReader(Socket server) throws IOException {
			this.inStream = server.getInputStream();
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int read;
			try {
				while((read = inStream.read(buffer)) != -1) {
					String out = new String(buffer, 0, read);
					System.out.print(out);
					System.out.flush();
				}
				System.out.println("Server disconnected, press any button to terminate");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 1337;
		String hostName = "localhost";
		if(args.length > 0)
			port = Integer.parseInt(args[0]);
		if(args.length > 1)
			hostName = args[1];

		Client client = new Client(port, hostName);

		client.joinChat();
	}
}
