package br.com.fiap.twitter.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import br.com.fiap.twitter.Auth;
import br.com.fiap.twitter.Busca;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {

	public static void main(String[] args) {
		
		String hashtag = "#java10";
		String mention = "@";
		
		Busca busca = null;
		try {
			busca = new Busca(hashtag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (busca.isEmpty()) {				
			System.out.println("Nenhum dado encontrado");				
		} else {				
			StringBuilder tweet = new StringBuilder();
			
			tweet.append(hashtag + "\n");
			tweet.append("\n");
			
			tweet.append("DIA:QTD\n");
			
			//Mapeando os Tweets
			Map<String, Long> quantidadeTweetsPorDia = busca.getQuantidadeTweetsPorDia();
			quantidadeTweetsPorDia.forEach(
				(dia, quantidade) -> tweet.append(dia + ":" + quantidade + "\n")
			);
			long totalSemana = quantidadeTweetsPorDia.values().stream()
					.mapToLong(Long::longValue)
					.sum();
			tweet.append("Tweets: " + totalSemana + "\n");
			tweet.append("\n");
			
			//Mapeando os Retweets
			Map<String, Long> quantidadeRetweetsPorDia = busca.getQuantidadeRetweetsPorDia();
			quantidadeRetweetsPorDia.forEach(
				(dia, quantidade) -> tweet.append(dia + ":" + quantidade + "\n")
			);
			totalSemana = quantidadeRetweetsPorDia.values().stream()
					.mapToLong(Long::longValue)
					.sum();
			tweet.append("RTs: " + totalSemana + "\n");
			tweet.append("\n");
			
			//Mapeando as Favoritações
			Map<String, Long> quantidadeFavoritesPorDia = busca.getQuantidadeFavoritesPorDia();
			quantidadeFavoritesPorDia.forEach(
				(dia, quantidade) -> tweet.append(dia + ":" + quantidade + "\n")
			);
			totalSemana = quantidadeFavoritesPorDia.values().stream()
					.mapToLong(Long::longValue)
					.sum();
			tweet.append("FAVs: " + totalSemana + "\n");
			tweet.append("\n");

			//Mostrando primeiro e último autor da lista
			tweet.append("1° e último autor\n");
			tweet.append(busca.getPrimeiroAutorPorNome() + "\n");
			tweet.append(busca.getUltimoAutorPorNome() + "\n");
			tweet.append("\n");
			
			//Mostrando primeira e última data da lista
			DateFormat df = new SimpleDateFormat("dd/MM");
			tweet.append("Data +/- recente\n");
			tweet.append(df.format(busca.getDataMaisRecente()) + "\n");
			tweet.append(df.format(busca.getDataMenosRecente()) + "\n");
			tweet.append("\n");
			
			tweet.append(mention);
			
			postaTweet(tweet.toString());
			
			//Somente para teste, para verificar a saída e quantidade de caracteres antes de usar o método postaTweet()
//			System.out.println(tweet);
//			System.out.println(tweet.length());
		}
	}

	public static void postaTweet(String tweet) {
		try {
			Twitter twitter = Auth.getTwitter();
			Status status = twitter.updateStatus(tweet);
			
			System.out.println("Tweet postado com sucesso!");
			System.out.println();
			System.out.println(status.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
