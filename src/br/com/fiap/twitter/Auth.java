package br.com.fiap.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Auth {

	private static Twitter twitter;

	public static Twitter getTwitter() {
		if (twitter == null) {
			AccessToken accessToken = loadAccessToken();
			twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer("", 
					"");
			twitter.setOAuthAccessToken(accessToken);
		}
		return twitter;
	}

	private static AccessToken loadAccessToken() {
		String token = "";
		String tokenSecret = "";
		return new AccessToken(token, tokenSecret);
	}

}
