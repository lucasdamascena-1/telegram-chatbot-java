package br.com.bot.constants;

/**
 * Interface para definição da url a ser utilizada e mensagem de erro 
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
	String API_CURRENCY_URL_ERROR = "Não foi possível efetuar comunicação com o servidor. "
			+ "Tente novamente mais tarde.";
}
