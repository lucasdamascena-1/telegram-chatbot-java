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
 * Classe criada para executar a��es chamadas pela classe BotManager. Esta classe atualizar� o status do chat, al�m de chamar as 
 * classes do pacote de servi�os
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class ResponseHandler {
	private final MessageSender sender;
	private final Map<Long, State> chatStates;
	private TwitterService twitterService = new TwitterService();
    
	/**
	 * Construtor que sobrecarrega o construtor padr�o da classe
	 * @param sender iniciar� o sender, que enviar� mensagem a tela
	 * @param db obter� o mapa para chatStates
	 */
	public ResponseHandler(MessageSender sender, DBContext db) {
		this.sender = sender;
		chatStates = db.getMap(Chat.CHAT_STATES);
	}
    
	/**
	 * M�todo que retornar� frase de Chat.START_REPLY e alterar� o chatStates
	 * @param chatId receber� chatId
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
	 * M�todo que retornar� frase de Chat.TWITTER_REPLY, 5 ultimos twets da infomoney pelo m�todo twitterService.createTweetList()
	 * e alterar� o chatStates
	 * @param chatId Receber� chatId vindo da BotManager
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
	 * M�todo que retornar� frase de Chat.CURRENCY_QUOTES_REPLY, consumir� o servi�o de retorno de valores de moedas pelo m�todo quotesJSON()
	 * e alterar� o chatStates
	 * @param chatId Receber� chatId vindo da BotManager
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
	 * M�todo que criar� uma inst�ncia da classe CurrencyService e chamar� a fun��o getData() para consumo de servi�o
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
			responseStr += "\nM�XIMO (DIA): " + result.getJSONObject("USD").get("high");
			responseStr += "\nM�NIMO (DIA): " + result.getJSONObject("USD").get("low");
			responseStr += "\nVARIA��O: " + result.getJSONObject("USD").get("pctChange") + "%\n";

			responseStr += "\nNome da moeda: " + result.getJSONObject("EUR").get("name");
			responseStr += "\nCompra: " + result.getJSONObject("EUR").get("bid");
			responseStr += "\nVenda: " + result.getJSONObject("EUR").get("ask");
			responseStr += "\nM�XIMO (DIA): " + result.getJSONObject("EUR").get("high");
			responseStr += "\nM�NIMO (DIA): " + result.getJSONObject("EUR").get("low");
			responseStr += "\nVARIA��O: " + result.getJSONObject("EUR").get("pctChange") + "%\n";

			responseStr += "\nNome da moeda: " + result.getJSONObject("BTC").get("name");
			responseStr += "\nCompra: " + result.getJSONObject("BTC").get("bid");
			responseStr += "\nVenda: " + result.getJSONObject("BTC").get("ask");
			responseStr += "\nM�XIMO (DIA): " + result.getJSONObject("BTC").get("high");
			responseStr += "\nM�NIMO (DIA): " + result.getJSONObject("BTC").get("low");
			responseStr += "\nVARIA��O: " + result.getJSONObject("BTC").get("pctChange") + "%";

			return responseStr;

		} catch (IOException e) {
			e.printStackTrace();
			return Address.API_CURRENCY_URL_ERROR;
		}
	}
}