import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class IMAPClient {

	boolean isConnected = false;	
	SSLSocket socket;
	PrintWriter writer;
	BufferedReader reader;
	Console console;
	Thread printer;

	public void connect() {
		SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
		HttpsURLConnection.setDefaultSSLSocketFactory(sf);
		try {
			socket = (SSLSocket)sf.createSocket("webmail.kth.se", 993);
			socket.startHandshake();
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
		writer.print("1 login " + username + " " + password + "\r\n");
		writer.flush();
	}

	public void logout() {
		writer.print("2 logout\r\n");
		writer.flush();
	}

	public class Printer implements Runnable {
		@Override
		public void run() {
			try {
				String s = "";
				while((s = reader.readLine()) != null)
					System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void startPrinter() {
		printer = new Thread(new Printer());
		printer.start();
	}

	public void list() {
		writer.print("3 list \"\" \"*\"\r\n");
		writer.flush();
	}

	public void select() {
		String s = console.readLine("Enter mailbox name: ");
		writer.print("4 select \"" + s + "\"\r\n");
		writer.flush();
	}

	public void fetchList() {
		String s = console.readLine("Enter list interval: ");
		writer.print("5 fetch " + s + " (BODY[HEADER.FIELDS (Subject)])\r\n");
		writer.flush();
	}

	public void fetchMail() {
		String s = console.readLine("Enter mail id: ");
		writer.print("6 fetch " + s + " RFC822\r\n");
		writer.flush();
	}

	public void close() {
		writer.print("7 close\r\n");
		writer.flush();
	}

	public void menu() {
		String s = console.readLine();
		switch(s.strip().toLowerCase()) {
			case "login":
				login();
				break;
			case "logout":
				logout();
				break;
			case "disconnect":
				disconnect();
				break;
			case "list":
				list();
				break;
			case "select":
				select();
				break;
			case "fetch list":
				fetchList();
				break;
			case "fetch mail":
				fetchMail();
				break;
			case "close":
				close();
				break;
		}
	}
	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		IMAPClient c = new IMAPClient();

		c.connect();
		c.startPrinter();
		while(c.isConnected)
			c.menu();
	}
}