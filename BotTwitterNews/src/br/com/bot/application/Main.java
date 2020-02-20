package br.com.bot.application;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.bot.manager.BotManager;

/**
 * Classe Main, responsável pela inicialização do bot
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class Main {
    
	public static void main(String[] args) {

		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			BotManager bot = new BotManager();
			botsApi.registerBot(bot);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
