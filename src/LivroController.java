import java.io.*;
import java.util.*;

public class LivroController {

  private static String caminho = "biblioteca.dat";

  public static void Inserir(Livro livro) {
    ArrayList<Livro> lista = LivroController.Listar();
    if (lista.size() == 0) {
      System.out.println(PrintCores.amarelo + "Criando arquivo." + PrintCores.padrao);
    }
    lista.add(livro);

    try {
      LivroDAO.gravar(LivroController.caminho, lista);
    } catch (IOException e) {
      System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
      e.printStackTrace();
    }
  }

  public static void Editar(Livro livroEditado) {
    ArrayList<Livro> lista = LivroController.Listar();
    ListIterator<Livro> iterator = lista.listIterator();

    while (iterator.hasNext()) {
      Livro livroAtual = iterator.next();

      if (livroAtual.getId().equals(livroEditado.getId())) {
        iterator.set(livroEditado);
      }
    }
    try {
      LivroDAO.gravar(LivroController.caminho, lista);
    } catch (IOException e) {
      System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
      e.printStackTrace();
    }
  }

  public static boolean Excluir(String id) {
    ArrayList<Livro> lista = LivroController.Listar();
    Iterator<Livro> iterator = lista.iterator();

    while (iterator.hasNext()) {
      Livro livro = iterator.next();

      if (livro.getId().equals(id)) {
        iterator.remove();

        try {
          LivroDAO.gravar(LivroController.caminho, lista);
        } catch (FileNotFoundException e) {
          System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
        } catch (IOException e) {
          System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
        }
        return true;
      }
    }

    return false;
  }

  public static ArrayList<Livro> Buscar(Optional<String> autor, Optional<String> ISBN, Optional<String> titulo) {
    ArrayList<Livro> lista = LivroController.Listar();
    Iterator<Livro> iterator = lista.iterator();

    while (iterator.hasNext()) {
      Livro livro = iterator.next();

      if (autor.isPresent() && !livro.getAutor().toLowerCase().contains(autor.get().toLowerCase())) {
        iterator.remove();
      }
      if (ISBN.isPresent() && !livro.getISBN().toLowerCase().contains(ISBN.get().toLowerCase())) {
        iterator.remove();
      }
      if (titulo.isPresent() && !livro.getTitulo().toLowerCase().contains(titulo.get().toLowerCase())) {
        iterator.remove();
      }
    }
    return lista;
  }
  
  public static Livro BuscarPorId(String id) {
    ArrayList<Livro> lista = LivroController.Listar();
    Iterator<Livro> iterator = lista.iterator();

    while (iterator.hasNext()) {
      Livro livro = iterator.next();

      if (livro.getId().equals(id)) {
        return livro;
      }
    }
    return null;
  }

  public static ArrayList<Livro> Listar() {

    ArrayList<Livro> lista = new ArrayList<Livro>();
    try {
      lista = (ArrayList<Livro>) LivroDAO.ler(caminho);
    } catch (FileNotFoundException e) {
      System.out.println(PrintCores.vermelho + "Arquivo de dados não encontrado."+ PrintCores.padrao);
    } catch (ClassNotFoundException e) {
      System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
    } catch (IOException e) {
      System.out.println(PrintCores.vermelho + "Erro: " + e.getMessage() + PrintCores.padrao);
    }
    return lista;
  }
}
