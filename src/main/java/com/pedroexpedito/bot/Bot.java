package com.pedroexpedito.bot;

import java.io.File;
import java.io.IOException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			 String username = update.getMessage().getChat().getUserName();

			if (update.getMessage().getText().equalsIgnoreCase("escanear")) {
				if(AllowList.contains(username)) {

				String chatId = update.getMessage().getChatId().toString();

				SendPhoto msgPhoto = new SendPhoto();
				msgPhoto.setChatId(chatId);

				SendMessage msgText = new SendMessage();
				msgText.setChatId(chatId);;
				SendMessage msgFinal = new SendMessage();
				msgFinal.setText("Pronto");
				msgFinal.setChatId(chatId);


				msgText.setText("Escaneando...");
				String photoName = PhotoName.getNewName();

				try {
					execute(msgText);
					Command.exec("hp-scan --mode=color -o /home/pedro/img/" + photoName);
					File file = new File("/home/pedro/img/" + photoName);
					msgPhoto.setPhoto(new InputFile().setMedia(file));
					execute(msgPhoto);
					execute(msgFinal);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		}
	}

	@Override
	public String getBotUsername() {
		return "Impressora";
	}

	@Override
	public String getBotToken() {
		String token = System.getenv("BOT_TOKEN");
		System.out.println(token);
		return token;
	}
}