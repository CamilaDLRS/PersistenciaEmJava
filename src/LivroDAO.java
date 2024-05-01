import java.io.*;

public class LivroDAO {
      public static void gravar(String caminho, Object objeto) throws FileNotFoundException, IOException{
        // Cria um arquivo no caminho passado por parâmetro
        FileOutputStream outFile = new FileOutputStream(caminho);
        // Recebe como parâmetro o arquivo que irá armazenar objetos serializáveis
        ObjectOutputStream s = new ObjectOutputStream(outFile);
        // Grava os bytes referentes ao objeto serializável no arquivo.
        s.writeObject(objeto);
        s.close();
    }

    public static Object ler(String caminho) throws FileNotFoundException, IOException, ClassNotFoundException{
        // Abre o arquivo que está no caminho passado por parâmetro
        FileInputStream inFile = new FileInputStream(caminho);
        // Recebe como parâmetro o arquivo que irá armazenar objetos serializáveis
        ObjectInputStream s = new ObjectInputStream(inFile);
        // Lê os bytes referentes ao objeto serializável e retorna o objeto.
        Object objeto = s.readObject();
        s.close();
        return objeto;
    }
}
