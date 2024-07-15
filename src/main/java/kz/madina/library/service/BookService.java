package kz.madina.library.service;

import kz.madina.library.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBooks();

    public List<Book> searchBooks(String keyword, Long userId);

    public Book findBookById(Long id);

    public void createBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(Long id);

}
