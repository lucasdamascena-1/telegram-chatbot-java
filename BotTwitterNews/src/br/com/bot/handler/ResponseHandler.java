package br.com.bot.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.bot.constants.Address;
import br.com.bot.constants.Chat;
import br.com.bot.constants.State;
import br.com.bot.services.*;
import twitter4j.Status;

/**
 * Classe criada para executar ações chamadas pela classe BotManager. Esta classe atualizará o status do chat, além de chamar as 
 * classes do pacote de serviços
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class ResponseHandler {
	private final MessageSender sender;
	private final Map<Long, State> chatStates;
	private TwitterService twitterService = new TwitterService();
    
	/**
	 * Construtor que sobrecarrega o construtor padrão da classe
	 * @param sender iniciará o sender, que enviará mensagem a tela
	 * @param db obterá o mapa para chatStates
	 */
	public ResponseHandler(MessageSender sender, DBContext db) {
		this.sender = sender;
		chatStates = db.getMap(Chat.CHAT_STATES);
	}
    
	/**
	 * Método que retornará frase de Chat.START_REPLY e alterará o chatStates
	 * @param chatId receberá chatId
	 */
	public void replyToStart(long chatId) {
		try {
			sender.execute(new SendMessage().setText(Chat.START_REPLY).setChatId(chatId));
			chatStates.put(chatId, State.START);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Método que retornará frase de Chat.TWITTER_REPLY, 5 ultimos twets da infomoney pelo método twitterService.createTweetList()
	 * e alterará o chatStates
	 * @param chatId Receberá chatId vindo da BotManager
	 */
	public void replyToTwitterNews(long chatId) {
		try {
			sender.execute(new SendMessage().setText(Chat.TWITTER_REPLY).setChatId(chatId));

			List<Status> timelineResult = twitterService.createTweetList();
			Status status;
			int amountOfTweets = 5;

			for (int i = 0; i <= amountOfTweets; i++) {
				status = timelineResult.get(i);
				sender.execute(new SendMessage().setText(status.getText()).setChatId(chatId));
			}

			chatStates.put(chatId, State.TWITTER);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Método que retornará frase de Chat.CURRENCY_QUOTES_REPLY, consumirá o serviço de retorno de valores de moedas pelo método quotesJSON()
	 * e alterará o chatStates
	 * @param chatId Receberá chatId vindo da BotManager
	 */
	public void replyToCurrencyQuotes(long chatId) {
		try {
			sender.execute(new SendMessage().setText(Chat.CURRENCY_QUOTES_REPLY).setChatId(chatId));
			sender.execute(new SendMessage().setText(quotesJSON()).setChatId(chatId));

			chatStates.put(chatId, State.CQUOTES);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Método que criará uma instância da classe CurrencyService e chamará a função getData() para consumo de serviço
	 * @return String com os dados / valores da moeda retornados na chamada getData()
	 */
	public String quotesJSON() {

		CurrencyService obj = new CurrencyService();
		String data;
		String responseStr = "";

		try {
			data = obj.getData();

			JSONObject result = new JSONObject(data);

			responseStr += "\nNome da moeda: " + result.getJSONObject("USD").get("name");
			responseStr += "\nCompra: " + result.getJSONObject("USD").get("bid");
			responseStr += "\nVenda: " + result.getJSONObject("USD").get("ask");
			responseStr += "\nMÁXIMO (DIA): " + result.getJSONObject("USD").get("high");
			responseStr += "\nMÍNIMO (DIA): " + result.getJSONObject("USD").get("low");
			responseStr += "\nVARIAÇÃO: " + result.getJSONObject("USD").get("pctChange") + "%\n";

			responseStr += "\nNome da moeda: " + result.getJSONObject("EUR").get("name");
			responseStr += "\nCompra: " + result.getJSONObject("EUR").get("bid");
			responseStr += "\nVenda: " + result.getJSONObject("EUR").get("ask");
			responseStr += "\nMÁXIMO (DIA): " + result.getJSONObject("EUR").get("high");
			responseStr += "\nMÍNIMO (DIA): " + result.getJSONObject("EUR").get("low");
			responseStr += "\nVARIAÇÃO: " + result.getJSONObject("EUR").get("pctChange") + "%\n";

			responseStr += "\nNome da moeda: " + result.getJSONObject("BTC").get("name");
			responseStr += "\nCompra: " + result.getJSONObject("BTC").get("bid");
			responseStr += "\nVenda: " + result.getJSONObject("BTC").get("ask");
			responseStr += "\nMÁXIMO (DIA): " + result.getJSONObject("BTC").get("high");
			responseStr += "\nMÍNIMO (DIA): " + result.getJSONObject("BTC").get("low");
			responseStr += "\nVARIAÇÃO: " + result.getJSONObject("BTC").get("pctChange") + "%";

			return responseStr;

		} catch (IOException e) {
			e.printStackTrace();
			return Address.API_CURRENCY_URL_ERROR;
		}
	}
}