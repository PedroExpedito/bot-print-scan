package com.pedroexpedito.bot;

import java.io.IOException;
import java.util.Scanner;

public class Command {
	public synchronized static void exec(String cmd) throws IOException {

		Process process = Runtime.getRuntime().exec(cmd);

		Thread commandLineThread = new Thread(() -> {
			Scanner leitor = new Scanner(process.getInputStream());
			while (leitor.hasNextLine()) {
				System.out.println(leitor.nextLine());
			}
		});

		commandLineThread.start();
		try {
			commandLineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
