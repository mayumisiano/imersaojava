import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{
    
    public List<Conteudo> extraiConteudos(String json){
        //Extrair os dados que interessam (título, poster, classificação etc.)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos= parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //Popular a lista de conteúdos
        for (Map<String, String> atributos : listaDeAtributos) {
            String nomeFilme = atributos.get("title");
            String urlImagem = atributos.get("image")
                    .replaceAll("(@+)(.*).jpg$","$1.jpg");
            var conteudo = new Conteudo(nomeFilme, urlImagem);

            conteudos.add(conteudo);
          }
    
    return conteudos;
    }
}

