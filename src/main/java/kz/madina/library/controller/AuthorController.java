package kz.madina.library.controller;

import kz.madina.library.model.Author;
import kz.madina.library.model.Book;
import kz.madina.library.model.Publisher;
import kz.madina.library.service.AuthorService;
import kz.madina.library.service.BookService;
import kz.madina.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService, PublisherService publisherService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("book", new Book());
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/books";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        System.out.println("IN addBook");
        bookService.createBook(book);
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/books";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam Long id, Model model) {
        System.out.println("IN deleteBook");
        System.out.println("id: " + id);
        bookService.deleteBook(id);
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/books";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "admin/author";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@ModelAttribute("author") Author author, Model model) {
        authorService.createAuthor(author);
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "admin/author";
    }

    @PostMapping("/authors/delete")
    public String deleteAuthor(@RequestParam Long id, Model model) {
        authorService.deleteAuthor(id);
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "admin/author";
    }

    @GetMapping("/publishers")
    public String listPublishers(Model model) {
        model.addAttribute("publisher", new Publisher());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/publisher";
    }

    @PostMapping("/publishers/add")
    public String addPublisher(@ModelAttribute("publisher") Publisher publisher, Model model) {
        System.out.println("IN addPublisher");
        publisherService.createPublisher(publisher);
        model.addAttribute("publisher", new Publisher());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/publisher";
    }

    @PostMapping("/publishers/delete")
    public String deletePublisher(@RequestParam Long id, Model model) {
        System.out.println("IN deletePublisher");
        publisherService.deletePublisher(id);
        model.addAttribute("publisher", new Publisher());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "admin/publisher";
    }
}