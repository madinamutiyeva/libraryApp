package kz.madina.library.service.impl;

import kz.madina.library.service.BookService;
import kz.madina.library.model.Book;
import kz.madina.library.model.User;
import kz.madina.library.repository.BookRepository;
import kz.madina.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookService bookService, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }


    @Transactional
    public void addBook(Long bookId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookService.findBookById(bookId);
        List<Book> books = user.getBooks();
        books.add(book);
        user.setBooks(books);
        book.setUser(user);
        userRepository.saveAndFlush(user);
        bookRepository.saveAndFlush(book);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteBook(Long bookId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookService.findBookById(bookId);
        List<Book> books = user.getBooks();
        books.remove(book);
        user.setBooks(books);
        book.setUser(null);
        userRepository.saveAndFlush(user);
        bookRepository.saveAndFlush(book);
    }
}
