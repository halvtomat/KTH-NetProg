import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class HTTPServer {

	private static void handleRequest(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getRawPath();
		System.out.println(method + " " + path);
		OutputStream os = exchange.getResponseBody();

		if(path.equals("/favicon.ico")) {
			File favicon = new File("./favicon.ico");
			exchange.sendResponseHeaders(200, favicon.length());
			exchange.getResponseHeaders().add("Content-Type", "image/gif");
			Files.copy(favicon.toPath(), os);
		} else {
			String response = "Hello world!";
			exchange.sendResponseHeaders(200, response.getBytes().length);
			os.write(response.getBytes());
		}
		os.close();
	}

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(1337), 0);
		server.createContext("/", HTTPServer::handleRequest);
		server.setExecutor(null);
		server.start();
	}
}