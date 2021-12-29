import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class EmailClient {

	public void getMail() {
		SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
		HttpsURLConnection.setDefaultSSLSocketFactory(sf);
		String host = "www.kth.se";
		try {
			SSLSocket socket = (SSLSocket)sf.createSocket("webmail.kth.se", 993);
			String[] cipher = {"TLS_RSA_WITH_AES_128_CBC_SHA"};
			//socket.setEnabledCipherSuites(cipher);
			socket.startHandshake();
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Console console = System.console();
			String username = console.readLine("Enter username: ");
			String password = new String(console.readPassword("Enter password: "));
			writer.println("a001 login " + username + " " + password);
			writer.flush();
			writer.println("a002 list \"\" \"*\"");
			writer.flush();
			String s = ""; 
			while((s = reader.readLine()) != null)
				System.out.println(s);
			writer.println("a005 logout");
			writer.flush();
			writer.close();
			reader.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		EmailClient ec = new EmailClient();
		ec.getMail();
	}
}