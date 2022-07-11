import axios from 'axios';

class BookService {
    baseUrl= "http://localhost:8080/home";

    displaybook(){
        return axios.get(`${this.baseUrl}/getbooks`);
    }

    addbook(data){
        return axios.post(`${this.baseUrl}/addBook`,data);
    }

    getCount(){
        return axios.get(`${this.baseUrl}/getCount`);
    }

    uploadFile(data){
        return axios.post(`${this.baseUrl}/addImage`,data);
    }

    getBooksByPriceAsc(){
        return axios.get(`${this.baseUrl}/getBooksByPriceAsc`);
    }

    getBooksByPriceDesc(){
        return axios.get(`${this.baseUrl}/getBooksByPriceDesc`);
    }

    searchAndFilter(bookName){
        return axios.get(`${this.baseUrl}/getBookByName/${bookName}`);
    }
}
export default new BookService();