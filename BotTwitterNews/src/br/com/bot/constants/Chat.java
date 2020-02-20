package br.com.bot.constants;

/**
 * Interface para definição do chat_state e das mensagens a serem apresentadas pelo bot
 * @author ADM
 *
 */
public interface Chat {
    
	/**
	 * CHAT_STATES
	 */
	String CHAT_STATES = "CHAT_STATES";
    
	/**
	 * Descrição do comando /start
	 */
	String START_DESCRIPTION = "Inicia o Chatbot.";
    
	/**
	 * Mensagem de retorno do comando /start
	 */
	String START_REPLY = "Bem vindo! Meu nome é Harvey e espero que minhas informações sejam úteis à você! "
			+ "Por favor pressione /commands e veja como posso te auxiliar!";
    
	/**
	 * Descrição do comando /twitter
	 */
	String TWITTER_DESCRIPTION = "Uma lista dos últimos cinco tweets do Infomoney será exibida.";
    
	/**
	 * Mensagem anterior ao processamento do comando /twitter
	 */
	String TWITTER_REPLY = "Buscando os últimos cinco tweets do Infomoney!";
	
	/**
	 * Mensagem de retorno do comando /twitter
	 */
	String TWITTER_CONCLUSION = ", Espero ter sido útil! Caso queira informações "
			+ "sobre cotação de moedas em tempo real, pressione /moedas";
    
	/**
	 * Descrição do comando /moedas
	 */
	String CURRENCY_QUOTES_DESCRIPTION = "Informações em tempo real de cotação das moedas serão exibidas.";
    
	/**
	 * Mensagem anterior ao processamento do comando /moedas
	 */
	String CURRENCY_QUOTES_REPLY = "Buscando informações...";
    
	/**
	 * Mensagem de retorno do comando /moedas
	 */
	String CURRENCY_QUOTES_CONCLUSION = ", Espero ter sido útil! Caso queira as últimas notícias "
			+ "do mercado financeiro via Infomoney, pressione /twitter";
}
