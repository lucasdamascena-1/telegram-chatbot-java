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
 * Classe que herda a classe AbilityBot (que implementa a classe LongPollingBot) e que orquestrará todas as ações e 
 * delegará todas as mesmas para uma instância de TelegramLongPollingBot
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
	 * Construtor sobrescrito da classe, que aciona o construtor da superclasse(classe mãe) AbilityBot e cria uma instância da classe ResponseHandler
	 * @param botToken Token do bot que será utilizado
	 * @param botUsername Nome do bot que será utilizado
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
	 * Método que manipula dados do usuário consumidor do bot (usuário e chave)
	 * @return Primeiro nome do usuário(que será concatenado nas mensagens). Em caso de erro, retorna Amigo(a)
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
	 * Ability criada para definir e apresentar ações quando o usuário digitar /start
	 * @return Se for digitado /commands, retornará a frase da variável Chat.START_DESCRIPTION. Se for digitado /start, 
	 * executará a ação responseHandler.replyToStart
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
	 * Ability criada para definir e apresentar ações quando o usuário digitar /twitter
	 * @return Se for digitado /commands, informará a frase da variável Chat.TWITTER_DESCRIPTION. Se for digitado /twitter, 
	 * executará a ação responseHandler.replyToTwitterNews e encerarrá enviando a frase da variável Chat.TWITTER_CONCLUSION
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
	 * Ability criada para definir e apresentar ações quando o usuário digitar /moedas
	 * @return Se for digitado /commands, informará a frase da variável Chat.CURRENCY_QUOTES_DESCRIPTION Se for digitado /moedas,
	 * executará a ação responseHandler.replyToCurrencyQuotes e encerarrá enviando a frase da variável Chat.CURRENCY_QUOTES_CONCLUSION
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