package com.example.storebackend.service;

import com.example.storebackend.dto.BookStoreDto;
import com.example.storebackend.exception.UserRegistrationException;
import com.example.storebackend.model.BookDetails;
import com.example.storebackend.repository.BookDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookDetailsService implements IBookDetailsService {

    @Autowired
    private BookDetailsRepository bookRepo;

    @Override
    public List<BookDetails> showAllBooks() {
        List<BookDetails> allBooks = (List<BookDetails>) bookRepo.findAll();
        System.out.println("AllBook" + allBooks);
        return allBooks;
    }

    @Override
    public long findBookCount() {
        return bookRepo.count();
    }

    @Override
    public List<BookDetails> sortBookByAsc() {
        return bookRepo.sortBookAsc();
    }

    @Override
    public List<BookDetails> sortBookByDesc() {
        return bookRepo.sortBookDesc();
    }

    @Override
    public BookDetails addBook(BookStoreDto bookDto) {
        BookDetails bookAdded = new BookDetails(bookDto);
        System.out.println(bookAdded);
        return bookRepo.save(bookAdded);
    }

    @Override
    public BookDetails getBookById(int bookId) {
        return bookRepo.findByBookId(bookId)
                .orElseThrow(() -> new UserRegistrationException("Book  with id " + bookId + " does not exist in database..!"));

    }

    @Override
    public BookDetails getBookByName(String bookName) {
        return bookRepo.findByBookName(bookName)
                .orElseThrow(() -> new UserRegistrationException("Book does not exist in database..!"));

    }

    @Override
    public BookDetails updateBook(int bookId, BookStoreDto bookDTO) {
        BookDetails bookData = this.getBookById(bookId);
        bookData.updateBook(bookDTO);
        return bookRepo.save(bookData);
    }

    @Override
    public BookDetails updateBookByName(String bookName, BookStoreDto bookDTO) {
        BookDetails bookData = this.getBookByName(bookName);
        bookData.updateBook(bookDTO);
        return bookRepo.save(bookData);
    }

    @Override
    public void deleteBook(int bookId) {
        BookDetails isBookPresent = this.getBookById(bookId);
        bookRepo.delete(isBookPresent);
    }

    @Override
    public BookDetails updateBookQuantity(int bookId, int bookQuantity) {
        BookDetails book = this.getBookById(bookId);
        book.setBookQuantity(bookQuantity);
        return bookRepo.save(book);
    }

    @Override
    public BookDetails updateBookPrice(int bookId, int bookPrice) {
        BookDetails book = this.getBookById(bookId);
        book.setBookPrice(bookPrice);
        return bookRepo.save(book);
    }

}
