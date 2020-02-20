package br.com.bot.services;

import java.util.List;

import br.com.bot.constants.Token;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Classe que consumirá um serviço do twitter que retorna a lista de posts (timeline) de determinado usuário
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class TwitterService {
    
	/**
	 * Método que criará a lista dos tweets de determinado usuário no twitter
	 * @return Lista com posts (status)
	 */
	public List<Status> createTweetList() {

		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true).setOAuthConsumerKey(Token.API_TWITTER_OAUTHCONSUMERKEY)
				.setOAuthConsumerSecret(Token.API_TWITTER_OAUTHCONSUMERSECRET)
				.setOAuthAccessToken(Token.API_TWITTER_OAUTHACCESSTOKEN)
				.setOAuthAccessTokenSecret(Token.API_TWITTER_OAUTHACCESSTOKENSECRET);

		TwitterFactory tf = new TwitterFactory(cb.build());

		Twitter twitter = tf.getInstance();

		List<Status> statusList = null;

		try {
			statusList = twitter.getUserTimeline("@infomoney");
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return statusList;
	}
}