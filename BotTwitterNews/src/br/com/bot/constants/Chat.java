package br.com.bot.constants;

/**
 * Interface para defini��o do chat_state e das mensagens a serem apresentadas pelo bot
 * @author ADM
 *
 */
public interface Chat {
    
	/**
	 * CHAT_STATES
	 */
	String CHAT_STATES = "CHAT_STATES";
    
	/**
	 * Descri��o do comando /start
	 */
	String START_DESCRIPTION = "Inicia o Chatbot.";
    
	/**
	 * Mensagem de retorno do comando /start
	 */
	String START_REPLY = "Bem vindo! Meu nome � Harvey e espero que minhas informa��es sejam �teis � voc�! "
			+ "Por favor pressione /commands e veja como posso te auxiliar!";
    
	/**
	 * Descri��o do comando /twitter
	 */
	String TWITTER_DESCRIPTION = "Uma lista dos �ltimos cinco tweets do Infomoney ser� exibida.";
    
	/**
	 * Mensagem anterior ao processamento do comando /twitter
	 */
	String TWITTER_REPLY = "Buscando os �ltimos cinco tweets do Infomoney!";
	
	/**
	 * Mensagem de retorno do comando /twitter
	 */
	String TWITTER_CONCLUSION = ", Espero ter sido �til! Caso queira informa��es "
			+ "sobre cota��o de moedas em tempo real, pressione /moedas";
    
	/**
	 * Descri��o do comando /moedas
	 */
	String CURRENCY_QUOTES_DESCRIPTION = "Informa��es em tempo real de cota��o das moedas ser�o exibidas.";
    
	/**
	 * Mensagem anterior ao processamento do comando /moedas
	 */
	String CURRENCY_QUOTES_REPLY = "Buscando informa��es...";
    
	/**
	 * Mensagem de retorno do comando /moedas
	 */
	String CURRENCY_QUOTES_CONCLUSION = ", Espero ter sido �til! Caso queira as �ltimas not�cias "
			+ "do mercado financeiro via Infomoney, pressione /twitter";
}
