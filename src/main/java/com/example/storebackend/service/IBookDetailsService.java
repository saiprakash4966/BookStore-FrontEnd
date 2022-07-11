package com.example.storebackend.service;

import com.example.storebackend.dto.BookStoreDto;
import com.example.storebackend.model.BookDetails;

import java.util.List;

public interface IBookDetailsService {

    List<BookDetails> showAllBooks();
    BookDetails addBook( BookStoreDto bookDto);
    BookDetails getBookById(int bookId);
    BookDetails getBookByName(String bookName);
    BookDetails updateBook(int bookId, BookStoreDto bookDTO);
    BookDetails updateBookByName(String bookName, BookStoreDto bookDTO);
    void deleteBook(int bookId);
    BookDetails updateBookQuantity(int bookId, int bookQuantity);
    BookDetails updateBookPrice(int bookId, int bookPrice);
    long findBookCount();

    List<BookDetails> sortBookByAsc();

    List<BookDetails> sortBookByDesc();
}
