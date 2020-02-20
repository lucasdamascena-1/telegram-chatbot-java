package br.com.bot.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import br.com.bot.constants.Address;

/**
 * Classe responsável pela chamada do serviço que retorna um JSON com as cotações de euro, dolar e bitcoin
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class CurrencyService {
	
	private final OkHttpClient httpClient = new OkHttpClient();
    
	/**
	 * Método que consumirá o serviço de retorno de valores de moedas
	 * @return Retornará um JSON a ser armazenado na variável response
	 * @throws IOException Retornará código da exception
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