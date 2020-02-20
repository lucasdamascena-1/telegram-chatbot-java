package br.com.bot.constants;

/**
 * Interface para definir dados para acessar bot telegram e api do twitter
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public interface Token {

	// Telegram
	/**
	 * Username do bot - telegram
	 */
	String BOT_USERNAME = "Harveygrambot";
	
	/**
	 * Token do bot - telegram
	 */
	String BOT_TOKEN = "";
	
	/**
	 * Creator ID - telegram
	 */
	Integer CREATOR_ID = 813649649;

	// Twitter
	
	/**
	 * AUTHCONSUMERKEY - TWITTER
	 */
	String API_TWITTER_OAUTHCONSUMERKEY = "";
	
	/**
	 * AUTHCONSUMERSECRET - TWITTER
	 */
	String API_TWITTER_OAUTHCONSUMERSECRET = "";
	
	/**
	 * AUTHACCESSTOKEN - TWITTER
	 */
	String API_TWITTER_OAUTHACCESSTOKEN = "";
	
	/**
	 * AUTHACCESSTOKENSECRET - TWITTER
	 */
	String API_TWITTER_OAUTHACCESSTOKENSECRET = "";
}
