import java.util.*;

public class LivroView {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Bem vindo ao Cátalogo de Livros");

        int operacao;
        do {
            System.out.println("\nDigite o número da operação que deseja realizar:\n" +
                    "\t1 - Inserir livro\n" +
                    "\t2 - Buscar livros por Autor\n" +
                    "\t3 - Buscar livros por ISBN\n" +
                    "\t4 - Buscar livros por Titulo\n" +
                    "\t5 - Listar livros\n" +
                    "\t6 - Editar livro\n" +
                    "\t7 - Excluir livro\n" +
                    "\t0 - Encerrar programa\n");

            try {
                operacao = scanner.nextInt();
            } catch (InputMismatchException e) {
                operacao = 8;
                scanner.nextLine();
            }

            switch (operacao) {
                case 0:
                    System.out.println(PrintCores.amarelo + "Encerrando o programa..." + PrintCores.padrao);
                    break;
                case 1:
                    InserirLivro();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Digite o Autor para a busca: ");
                    Optional<String> autor = Optional.of(scanner.nextLine());
                    BuscarLivros(autor, Optional.empty(), Optional.empty());
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Digite o ISBN para a busca: ");
                    Optional<String> ISBN = Optional.of(scanner.nextLine());
                    BuscarLivros(Optional.empty(), ISBN, Optional.empty());
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Digite o Titulo para a busca: ");
                    Optional<String> titulo = Optional.of(scanner.nextLine());
                    BuscarLivros(Optional.empty(), Optional.empty(), titulo);
                    break;
                case 5:
                    BuscarLivros(Optional.empty(), Optional.empty(), Optional.empty());
                    break;
                case 6:
                    EditarLivro();
                    break;
                case 7:
                    ExcluirLivro();
                    break;
                default:
                    System.out.println(PrintCores.vermelho + "Operação inválida!\n" + PrintCores.padrao);
                    break;
            }
        } while (operacao != 0);

        scanner.close();
    }

    public static void InserirLivro() {
        Livro novoLivro = new Livro();
        System.out.println("Preencha os dados do novo livro: ");
        novoLivro.setId(UUID.randomUUID().toString());
        scanner.nextLine();

        try {
            System.out.print("\tTitulo: ");
            String titulo = scanner.nextLine();
            novoLivro.setTitulo(titulo);

            System.out.print("\tAutor: ");
            String autor = scanner.nextLine();
            novoLivro.setAutor(autor);

            System.out.print("\tISBN: ");
            String ISBN = scanner.nextLine();
            novoLivro.setISBN(ISBN);

            System.out.print("\tPaginas: ");

            int paginas = scanner.nextInt();
            novoLivro.setPaginas(paginas);

            System.out.print("\tPreco: ");
            double preco = scanner.nextDouble();
            novoLivro.setPreco(preco);

            LivroController.Inserir(novoLivro);

            System.out.println(PrintCores.verde + "Livro Inserido!" + PrintCores.padrao);
        } catch (InputMismatchException e) {
            System.out.println(PrintCores.vermelho + "Valor inválido! Operação cancelada." + PrintCores.padrao);
            scanner.nextLine();
        }
    }

    public static void EditarLivro() {
        System.out.print("Digite o ID do livro a ser editado: ");
        String id = scanner.next();

        Livro livro = LivroController.BuscarPorId(id);

        if (livro == null) {
            System.out.println(PrintCores.vermelho + "Livro não encontrado." + PrintCores.padrao);
        } else {
            System.out.println("Livro atualmente:");
            ArrayList<Livro> impressao = new ArrayList<Livro>();
            impressao.add(livro);
            ImprimirLivros(impressao);

            System.out.println("\nPreencha os novos dados do livro: ");
            scanner.nextLine();

            try {
                System.out.print("\tTitulo: ");
                String titulo = scanner.nextLine();
                livro.setTitulo(titulo);

                System.out.print("\tAutor: ");
                String autor = scanner.nextLine();
                livro.setAutor(autor);

                System.out.print("\tISBN: ");
                String ISBN = scanner.nextLine();
                livro.setISBN(ISBN);

                System.out.print("\tPaginas: ");

                int paginas = scanner.nextInt();
                livro.setPaginas(paginas);

                System.out.print("\tPreco: ");
                double preco = scanner.nextDouble();
                livro.setPreco(preco);

                LivroController.Editar(livro);

                System.out.println(PrintCores.verde + "Livro Editado!" + PrintCores.padrao);
            } catch (InputMismatchException e) {
                System.out.println(PrintCores.vermelho + "Valor inválido! Operação cancelada." + PrintCores.padrao);
                scanner.nextLine();
            }
        }
    }

    public static void ExcluirLivro() {
        System.out.print("Digite o ID do livro a ser excluido: ");
        String id = scanner.next();

        boolean resultado = LivroController.Excluir(id);

        if (resultado) {
            System.out.println(PrintCores.verde + "Livro Excluido!" + PrintCores.padrao);
        } else {
            System.out.println(PrintCores.vermelho + "Livro não encontrado." + PrintCores.padrao);
        }
    }

    public static void BuscarLivros(Optional<String> autor, Optional<String> ISBN, Optional<String> titulo) {
        ArrayList<Livro> resultado = LivroController.Buscar(autor, ISBN, titulo);

        if (resultado.size() == 0) {
            System.out.println(PrintCores.vermelho + "Nenhum livro encontrado." + PrintCores.padrao);
        } else {
            System.out.println(PrintCores.verde + "Livros encontrados: " + PrintCores.padrao);
            ImprimirLivros(resultado);
        }
    }

    public static void ImprimirLivros(ArrayList<Livro> lista) {
        System.out.printf("| %-40s | %-50s | %-25s | %-15s | %-10s | %-10s\n", "ID", "TITULO", "AUTOR", "ISBN",
                "PÁGINAS", "PREÇO");

        for (Livro livro : lista) {
            System.out.printf("| %-40s | %-50s | %-25s | %-15s | %-10d | %-10.2f\n", livro.getId().toString(),
                    livro.getTitulo(),
                    livro.getAutor(), livro.getISBN(), livro.getPaginas(), livro.getPreco());
        }
    }
}