package com.alura.literalura.principal;

import com.alura.literalura.model.AuthorEntity;
import com.alura.literalura.model.BookEntity;
import com.alura.literalura.model.GutendexResponse;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.DataConverter;
import com.alura.literalura.service.GutendexService;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private GutendexService bookApi = new GutendexService();

    private DataConverter converter = new DataConverter();

    private final String URL_BASE = "https://gutendex.com/";

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Libros guardados
                    3 - Libros guardados por idioma
                    4 - Obtener todos los autores
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    searchBooksWeb();
                    break;

                case 2:
                    getBooksSaved();
                    break;

                case 3:
                    getBooksSavedByLanguage();
                    break;

                case 4:
                    getAuthors();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private GutendexResponse getDataByNameWeb(String bookName) {
        var json = bookApi.getData(URL_BASE + "books/?search=" + bookName.replace(" ", "+"));

        return converter.getData(json, GutendexResponse.class);
    }

    private void searchBooksWeb() {
        try {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            var bookName = teclado.nextLine();

            GutendexResponse dataSerie = getDataByNameWeb(bookName);

            if (dataSerie.results().isEmpty()) {
                System.out.println("No se encontro un libro con ese titulo");
                return;
            }
            System.out.println(dataSerie.results().getFirst());
            System.out.println("Guardando...");
            BookEntity book = new BookEntity(dataSerie.results().getFirst());
            this.bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getBooksSaved() {
        List<BookEntity> books = this.bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void getBooksSavedByLanguage() {
        System.out.println("Escribe el código del idioma para consultar. Ejemplo: \"es\" ");

        var language = teclado.nextLine();
        List<BookEntity> books = this.bookRepository.findByLanguage(language);
        books.forEach(System.out::println);
    }

    private void getAuthors() {
        List<AuthorEntity> authors = this.authorRepository.findAll();
        authors.forEach(System.out::println);
    }

}
