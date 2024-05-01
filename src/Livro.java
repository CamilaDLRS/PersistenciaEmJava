import java.io.Serializable;

public class Livro implements Serializable {
  private String id;
  private String titulo;
  private String autor;
  private String ISBN;
  private int paginas;
  private double preco;

  public Livro(String _id, String _titulo, String _autor, String _ISBN, int _paginas, double _preco) {
    this.id = _id;
    this.titulo = _titulo;
    this.autor = _autor;
    ISBN = _ISBN;
    this.paginas = _paginas;
    this.preco = _preco;
  }

  public Livro() {
  }

  public String getId() {
    return this.id;
  }

  public void setId(String _id) {
    this.id = _id;
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String _titulo) {
    this.titulo = _titulo;
  }

  public String getAutor() {
    return this.autor;
  }

  public void setAutor(String _autor) {
    this.autor = _autor;
  }

  public String getISBN() {
    return this.ISBN;
  }

  public void setISBN(String _ISBN) {
    ISBN = _ISBN;
  }

  public int getPaginas() {
    return this.paginas;
  }

  public void setPaginas(int _paginas) {
    this.paginas = _paginas;
  }

  public double getPreco() {
    return this.preco;
  }

  public void setPreco(double _preco) {
    this.preco = _preco;
  }
}