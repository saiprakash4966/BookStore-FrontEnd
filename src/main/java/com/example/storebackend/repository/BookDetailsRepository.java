package com.example.storebackend.repository;

import com.example.storebackend.model.BookDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailsRepository  extends CrudRepository<BookDetails,Integer> {
    Optional<BookDetails> findByBookId(int bookId);
    Optional<BookDetails> findByBookName(String bookName);

    @Query(value = "SELECT * FROM books order by book_price ASC", nativeQuery = true)
    List<BookDetails> sortBookAsc();

    @Query(value = "SELECT * FROM books order by book_price DESC ", nativeQuery = true)
    List<BookDetails> sortBookDesc();
}
