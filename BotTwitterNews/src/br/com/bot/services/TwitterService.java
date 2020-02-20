package br.com.bot.services;

import java.util.List;

import br.com.bot.constants.Token;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Classe que consumir� um servi�o do twitter que retorna a lista de posts (timeline) de determinado usu�rio
 * @author Lucas Gabriel, Henrique, Guilherme
 *
 */
public class TwitterService {
    
	/**
	 * M�todo que criar� a lista dos tweets de determinado usu�rio no twitter
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