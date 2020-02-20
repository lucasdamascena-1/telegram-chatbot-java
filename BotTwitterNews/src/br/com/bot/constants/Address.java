package br.com.bot.constants;

/**
 * Interface para defini��o da url a ser utilizada e mensagem de erro 
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public interface Address {
	// API Comercial Quotes (Dolar, Real, BitCoins)
	
	/**
	 * URL da api que retorna valores das moedas
	 */
	String API_CURRENCY_URL = "https://economia.awesomeapi.com.br/all/USD-BRL,EUR-BRL,BTC-BRL";
	
	/**
	 * Mensagem de erro em caso de falha
	 */
	String API_CURRENCY_URL_ERROR = "N�o foi poss�vel efetuar comunica��o com o servidor. "
			+ "Tente novamente mais tarde.";
}
