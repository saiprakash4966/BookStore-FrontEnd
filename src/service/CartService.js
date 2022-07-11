import axios from 'axios';

const id=localStorage.getItem('Authorization')
const userId = JSON.parse(id);
console.log(id);
class CartService {
    baseUrl= "http://localhost:8080/cart";



    myCart(){
        return axios.get(`${this.baseUrl}/get/${userId}`)
    }

    addToCart = (data) => {
        return axios.post(`${this.baseUrl}/add`,data)
    }

    remove(cartId){
        return axios.delete(`${this.baseUrl}/remove/${cartId}`,cartId)
    }

    removeAll(){
        return axios.delete(`${this.baseUrl}/removeAll`)
    }

    updateCart(cartId,quantity,totalPrice){
        return axios.put(`${this.baseUrl}/update/${cartId}/${quantity}/${totalPrice}`,cartId,quantity,totalPrice)
    }

}
export default new CartService();