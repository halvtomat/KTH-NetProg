import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class SMTPClient {
	boolean isConnected = false;
	Socket unencrypted;
	SSLSocket socket;
	PrintWriter writer;
	BufferedReader reader;
	Console console;
	Thread printer;

	public void connect() {

		try {
			unencrypted = new Socket("smtp.kth.se", 587);
			writer = new PrintWriter(unencrypted.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(unencrypted.getInputStream()));
			console = System.console();
			isConnected = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			isConnected = false;
			printer.interrupt();
			writer.close();
			reader.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login() {
		String username = console.readLine("Enter username: ");
		String password = new String(console.readPassword("Enter password: "));
		writer.print("AUTH LOGIN\r\n");
		writer.flush();
		writer.print(Base64.getEncoder().encodeToString(username.getBytes()) + "\r\n");
		writer.flush();
		writer.print(Base64.getEncoder().encodeToString(password.getBytes()) + "\r\n");
		writer.flush();
	}

	public void starttls() {
		SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
		HttpsURLConnection.setDefaultSSLSocketFactory(sf);
		writer.print("STARTTLS\r\n");
		writer.flush();
		try {
			socket = (SSLSocket)sf.createSocket(unencrypted, unencrypted.getInetAddress().getHostAddress(), unencrypted.getPort(), true);
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket.startHandshake();
			startPrinter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public class Printer implements Runnable {
		@Override
		public void run() {
			try {
				String s;
				while((s = reader.readLine()) != null) {
					System.out.println(s);
					if(s.contains("220 2.0.0"))
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void startPrinter() {
		printer = new Thread(new Printer());
		printer.start();
	}

	public void sendMail() {
		try {
			String from = console.readLine("Enter from address: ");
			String to = console.readLine("Enter to address: ");
			String data = console.readLine("Enter message: ");
			writer.print("MAIL FROM:<" + from + ">\r\n");
			writer.flush();
			Thread.sleep(5000);
			writer.print("RCPT TO:<" + to + ">\r\n");
			writer.flush();
			Thread.sleep(5000);
			writer.print("DATA\n");
			writer.flush();
			Thread.sleep(5000);
			writer.print(data + "\r\n");
			writer.flush();
			Thread.sleep(5000);
			writer.print(".\r\n");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void man() {
		String s = console.readLine();
		writer.print(s + "\r\n");
		writer.flush();
	}

	public void reset() {
		writer.print("RSET\r\n");
		writer.flush();
	}

	public void end() {
		try {
			isConnected = false;
			printer.interrupt();
			writer.print("END\r\n");
			writer.flush();
			writer.close();
			reader.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void helo() {
		writer.print("HELO smtp.kth.se\n\r");
		writer.flush();
	}
	
	public void ehlo() {
		writer.print("EHLO smtp.kth.se\n\r");
		writer.flush();
	}

	public void menu() {
		String s = console.readLine();
		switch(s.strip().toLowerCase()) {
			case "login":
				login();
				break;
			case "disconnect":
				disconnect();
				break;
			case "send mail":
				sendMail();
				break;
			case "helo":
				helo();
				break;
			case "ehlo":
				ehlo();
				break;
			case "start tls":
				starttls();
				break;
			case "end":
				end();
				break;
			case "reset":
				reset();
				break;
			case "man":
				man();
				break;
		}
	}
	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		SMTPClient c = new SMTPClient();

		c.connect();
		c.startPrinter();
		while(c.isConnected)
			c.menu();
	}
}
