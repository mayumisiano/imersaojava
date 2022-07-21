import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {    

        //Fazer uma conexão HTTP e buscar os top 250 filmes        
        //String url = "https://api.mocki.io/v2/549a5d8b"; 

        //String url= "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);        
        
        //Exibir e manipular dados        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        for(int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);    
                        
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream(); 

            String nomeArquivo = "../saida/" + conteudo.getTitulo().replace(":", "-") + ".png";

            geradora.cria(inputStream, nomeArquivo); 
                       
            System.out.println(conteudo.getTitulo()); 
            System.out.println();                       
            }
        }
    }

