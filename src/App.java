import java.net.URI;
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
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[38;2;255;255;255m \u001b[48;2;42;122;228m Filme, Imagem e Classificação \u001b[m");
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
        }
    }
}
