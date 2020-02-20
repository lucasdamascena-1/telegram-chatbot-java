package br.com.bot.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import br.com.bot.constants.Address;

/**
 * Classe respons�vel pela chamada do servi�o que retorna um JSON com as cota��es de euro, dolar e bitcoin
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class CurrencyService {
	
	private final OkHttpClient httpClient = new OkHttpClient();
    
	/**
	 * M�todo que consumir� o servi�o de retorno de valores de moedas
	 * @return Retornar� um JSON a ser armazenado na vari�vel response
	 * @throws IOException Retornar� c�digo da exception
	 */
	public String getData() throws IOException {

		Request request = new Request.Builder().url(Address.API_CURRENCY_URL).build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			return response.body().string();

		}
	}
}