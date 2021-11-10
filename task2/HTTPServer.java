import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class HTTPServer {
	private static GuessingGame game = new GuessingGame();

	private static void handleRequest(HttpExchange exchange) throws IOException {
		OutputStream os = exchange.getResponseBody();
		InputStream is = exchange.getRequestBody();
		String method = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getRawPath();
		if(method.equals("GET")) {
			if(path.equals("/favicon.ico")) {
				File favicon = new File("./favicon.ico");
				exchange.getResponseHeaders().add("Content-Type", "image/gif");
				exchange.sendResponseHeaders(200, favicon.length());
				Files.copy(favicon.toPath(), os);
			} else if(path.equals("/") || path.equals("/index.html")) {
				game.newGame();
				File index = new File("./index.html");
				exchange.getResponseHeaders().add("Content-Type", "text/html");
				exchange.sendResponseHeaders(200, index.length());
				Files.copy(index.toPath(), os);
			}
		} else if(method.equals("POST")) {
			String guessValue = new String(is.readAllBytes()).split("=")[1];
			if(game.getVictory() || guessValue == "NEW GAME") {
				exchange.getResponseHeaders().add("Location", "/");
				exchange.sendResponseHeaders(302, 0);
			} else {
				String response = "";
				int guess = Integer.parseInt(guessValue);
				if(game.guess(guess)) {
					File victory = new File("./victory.html");
					response = Files.readString(victory.toPath());
					response = response.replace("GUESSES", Integer.toString(game.getGuesses()));
				} else {
					File nope = new File("./nope.html");
					response = Files.readString(nope.toPath());
					response = response.replace("GUESSES", Integer.toString(game.getGuesses()));
					response = response.replace("HIGHLOW", game.getHighLow());
				}
				exchange.getResponseHeaders().add("Content-Type", "text/html");
				exchange.sendResponseHeaders(200, response.length());
				os.write(response.getBytes());
			}
		}
		is.close();
		os.close();
	}

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(1337), 0);
		server.createContext("/", HTTPServer::handleRequest);
		server.setExecutor(null);
		server.start();
	}
}