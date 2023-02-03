package org.example.service;

import java.util.List;
import java.util.Map;
import org.example.domain.entity.Book;
import org.example.domain.repository.BookRepository;
import org.example.error.book.BookNotFoundException;
import org.example.error.book.BookUnSupportedFieldPatchException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book newBook(@RequestBody Book newBook) {
    return bookRepository.save(newBook);
  }

  public Book findOne(@PathVariable Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
  }

  public Book saveOrUpdate(@PathVariable Long id, @RequestBody Book newBook) {
    return bookRepository
        .findById(id)
        .map(
            existingBook -> {
              existingBook.setName(newBook.getName());
              existingBook.setAuthor(newBook.getAuthor());
              existingBook.setPrice(newBook.getPrice());
              return bookRepository.save(existingBook);
            })
        .orElseGet(
            () -> {
              newBook.setId(id);
              return bookRepository.save(newBook);
            });
  }

  public Book patch(@PathVariable Long id, @RequestBody Map<String, String> update) {
    return bookRepository
        .findById(id)
        .map(
            existingBook -> {
              String author = update.get("author");
              if (!StringUtils.isEmpty(author)) {
                existingBook.setAuthor(author);
                // Better create a custom method to update a value = :newValue where id = :id
                return bookRepository.save(existingBook);
              } else {
                throw new BookUnSupportedFieldPatchException(update.keySet());
              }
            })
        .orElseThrow(() -> new BookNotFoundException(id));
  }

  public void deleteBook(@PathVariable Long id) {
    bookRepository.deleteById(id);
  }
}
