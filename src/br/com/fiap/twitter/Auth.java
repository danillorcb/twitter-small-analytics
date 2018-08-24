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
			twitter.setOAuthConsumer("DOeGDBUn94FsjVJCYFIvWhV3X", 
					"ArpL2Bs2zoKgczDXBDyzxTNXnRgJfsayOJFOI2I5Ew3hMukdk1");
			twitter.setOAuthAccessToken(accessToken);
		}
		return twitter;
	}

	private static AccessToken loadAccessToken() {
		String token = "146149556-vOZlvXHjKCGwMCBN0RmLRzEyaZE9mmOp0639Aua1";
		String tokenSecret = "xhYP9v1ofbcCjIo1E6mJBIHM4aIkKdBPU3Z4NOGzRsLCE";
		return new AccessToken(token, tokenSecret);
	}

}
