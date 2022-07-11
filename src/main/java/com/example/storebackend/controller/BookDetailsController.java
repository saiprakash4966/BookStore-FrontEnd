package com.example.storebackend.controller;


import com.example.storebackend.dto.BookStoreDto;
import com.example.storebackend.dto.ResponseDto;
import com.example.storebackend.model.BookDetails;
import com.example.storebackend.service.IBookDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
@RequestMapping("/home")
@Slf4j
public class BookDetailsController {

    @Autowired
    public IBookDetailsService bookService;

    @GetMapping("/getbooks")
    public ResponseEntity<ResponseDto> getAllBooks() {
        List<BookDetails> allBooks = bookService.showAllBooks();
        ResponseDto dto = new ResponseDto("All Books Retrieved successfully:", allBooks);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getBookByName/{bookName}")
    public ResponseEntity<ResponseDto> getOneBookByName(@PathVariable String bookName)
    {
        BookDetails getOneBook = bookService.getBookByName(bookName);
        ResponseDto dto = new ResponseDto("Book retrieved successfully"+bookName, getOneBook);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/getCount")
    public ResponseEntity<ResponseDto> getCountByBook()
    {
        long getCount = bookService.findBookCount();
        ResponseDto dto = new ResponseDto("Number of books in store:", getCount);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getBooksByPriceAsc")
    public ResponseEntity<ResponseDto> sortBookByPriceAsc() {
        List<BookDetails> sortBookByPriceAsc = bookService.sortBookByAsc();
        if (!sortBookByPriceAsc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Books Found", HttpStatus.OK.value(), sortBookByPriceAsc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto("No Books Found", HttpStatus.NOT_FOUND.value()));
    }

    @GetMapping("/getBooksByPriceDesc")
    public ResponseEntity<ResponseDto> sortBookByPriceDesc() {
        List<BookDetails> sortBookByPriceDesc = bookService.sortBookByDesc();
        if (!sortBookByPriceDesc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Books Found", HttpStatus.OK.value(), sortBookByPriceDesc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto("No Books Found", HttpStatus.NOT_FOUND.value()));
    }


    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<ResponseDto> getOneBook(@PathVariable int bookId)
    {
        BookDetails getOneBook = bookService.getBookById(bookId);
        ResponseDto dto = new ResponseDto("Book retrieved successfully"+bookId, getOneBook);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookStoreDto bookDto) {
        BookDetails addBook = bookService.addBook(bookDto);
        log.debug("Data"+addBook);
        ResponseDto dto = new ResponseDto("Book Added Successfully",addBook.getBookId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }



    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDto> updateBookData(@PathVariable("bookId") int bookId,
                                                      @Valid @RequestBody BookStoreDto bookDTO) {
        BookDetails updateBook = bookService.updateBook(bookId, bookDTO);
        log.debug(" After Update " + updateBook.toString());
        ResponseDto response = new ResponseDto("Updated  for" + bookId, updateBook);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

    }

    @PutMapping("/updateByName/{bookName}")
    public ResponseEntity<ResponseDto> updateBookDataByName(@PathVariable("bookName") String bookName,
                                                            @Valid @RequestBody BookStoreDto bookDTO) {
        BookDetails updateBook = bookService.updateBookByName(bookName, bookDTO);
        log.debug(" After Update " + updateBook.toString());
        ResponseDto response = new ResponseDto("Updated  for Data " + bookName, updateBook);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
        ResponseDto response = new ResponseDto("Delete call success for id ", "deleted id:" + bookId);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

    }

    @PutMapping("/updatequantity/{bookId}/{bookQuantity}")
    public ResponseEntity<ResponseDto> updateBookQuantity(@PathVariable int bookId, @PathVariable int bookQuantity) {
        BookDetails updateBookQuantity = bookService.updateBookQuantity(bookId, bookQuantity);
        ResponseDto response = new ResponseDto("Book Quantity Update is success for id " + bookId, updateBookQuantity);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }


    @PutMapping("/updateprice/{bookId}/{bookPrice}")
    public ResponseEntity<ResponseDto> updateBookPrice(@PathVariable int bookId, @PathVariable int bookPrice) {
        BookDetails updateBookPrice = bookService.updateBookPrice(bookId, bookPrice);
        ResponseDto response = new ResponseDto("Book Price Update is success for id " + bookId, updateBookPrice);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }
}
