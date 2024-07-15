package kz.madina.library.service.impl;

import kz.madina.library.service.AuthorService;
import kz.madina.library.model.Author;
import kz.madina.library.repository.AuthorRepository;
import org.hibernate.Hibernate;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @AllowNonPortable
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            Hibernate.initialize(author.getBooks());
        }
        return authors;
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void createAuthor(Author author) {
        Author newAuthor = new Author.Builder()
                .name(author.getName())
                .description(author.getDescription())
                .build();
        authorRepository.save(newAuthor);
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
