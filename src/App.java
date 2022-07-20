import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {    

        //Fazer uma conexão HTTP e buscar os top 250 filmes
        //String url= "https://imdb-api.com/en/API/Top250Movies/k_k0mmr83v";

        String url = "https://api.mocki.io/v2/549a5d8b"; 
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        //Extrair os dados que interessam (título, poster, classificação etc.)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //Exibir e manipular dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String nomeFilme = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            
            String nomeArquivo = nomeFilme.replace(":", "-")  + ".png";
            
            geradora.cria(inputStream, nomeArquivo);
            
            System.out.println(nomeFilme);
            System.out.println(filme.get("imDbRating"));
        }
    }
}
