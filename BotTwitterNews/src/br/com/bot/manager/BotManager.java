package br.com.bot.manager;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

import java.util.Map;

import org.mapdb.HTreeMap;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.objects.User;

import br.com.bot.constants.Chat;
import br.com.bot.constants.Token;
import br.com.bot.handler.ResponseHandler;

/**
 * Classe que herda a classe AbilityBot (que implementa a classe LongPollingBot) e que orquestrar� todas as a��es e 
 * delegar� todas as mesmas para uma inst�ncia de TelegramLongPollingBot
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class BotManager extends AbilityBot {

	private final ResponseHandler responseHandler;
    
	/**
	 * Construtor default da classe, que aciona o construtor sobrescrito
	 */
	public BotManager() {
		this(Token.BOT_TOKEN, Token.BOT_USERNAME);
	}
    
	/**
	 * Construtor sobrescrito da classe, que aciona o construtor da superclasse(classe m�e) AbilityBot e cria uma inst�ncia da classe ResponseHandler
	 * @param botToken Token do bot que ser� utilizado
	 * @param botUsername Nome do bot que ser� utilizado
	 */
	public BotManager(String botToken, String botUsername) {
		super(botToken, botUsername);
		responseHandler = new ResponseHandler(sender, db);
	}
    
	@Override
	public int creatorId() {
		return Token.CREATOR_ID;
	}
	
	/**
	 * M�todo que manipula dados do usu�rio consumidor do bot (usu�rio e chave)
	 * @return Primeiro nome do usu�rio(que ser� concatenado nas mensagens). Em caso de erro, retorna Amigo(a)
	 */
	public String identifyUser() {

		HTreeMap<Integer, User> mapUsers = (HTreeMap<Integer, User>) users();

		for (Map.Entry<Integer, User> entry : mapUsers.entrySet()) {
			User user = entry.getValue();
			Integer key = entry.getKey();

			if (key != null && user != null) {
				return user.getFirstName();
			}
		}

		return "Amigo (a)";
	}
    
	/**
	 * Ability criada para definir e apresentar a��es quando o usu�rio digitar /start
	 * @return Se for digitado /commands, retornar� a frase da vari�vel Chat.START_DESCRIPTION. Se for digitado /start, 
	 * executar� a a��o responseHandler.replyToStart
	 */
	public Ability replyToStart() {
		return Ability
				.builder()
				.name("start")
				.info(Chat.START_DESCRIPTION)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> responseHandler.replyToStart(ctx.chatId()))
				.build();
	}
    
	/**
	 * Ability criada para definir e apresentar a��es quando o usu�rio digitar /twitter
	 * @return Se for digitado /commands, informar� a frase da vari�vel Chat.TWITTER_DESCRIPTION. Se for digitado /twitter, 
	 * executar� a a��o responseHandler.replyToTwitterNews e encerarr� enviando a frase da vari�vel Chat.TWITTER_CONCLUSION
	 */
	public Ability replyToTwitterNews() {
		return Ability
				.builder()
				.name("twitter")
				.info(Chat.TWITTER_DESCRIPTION)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> responseHandler.replyToTwitterNews(ctx.chatId()))
				.post(ctx -> silent.send(identifyUser() + Chat.TWITTER_CONCLUSION, ctx.chatId()))
				.build();
	}
    
	/**
	 * Ability criada para definir e apresentar a��es quando o usu�rio digitar /moedas
	 * @return Se for digitado /commands, informar� a frase da vari�vel Chat.CURRENCY_QUOTES_DESCRIPTION Se for digitado /moedas,
	 * executar� a a��o responseHandler.replyToCurrencyQuotes e encerarr� enviando a frase da vari�vel Chat.CURRENCY_QUOTES_CONCLUSION
	 */
	public Ability replyCurrencyQuotes() {
		return Ability
				.builder()
				.name("moedas")
				.info(Chat.CURRENCY_QUOTES_DESCRIPTION)
				.locality(ALL)
				.privacy(PUBLIC)
				.action(ctx -> responseHandler.replyToCurrencyQuotes(ctx.chatId()))
				.post(ctx -> silent.send(identifyUser() + Chat.CURRENCY_QUOTES_CONCLUSION, ctx.chatId()))
				.build();
	}
}