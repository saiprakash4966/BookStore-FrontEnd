import axios from 'axios';

const id=localStorage.getItem('Authorization')
const userId = JSON.parse(id);

class OrderService {
    baseUrl= "http://localhost:8080/order";

    placedOrder = (data) => {
        return axios.post(`${this.baseUrl}/placeOrder/${userId}`,data)
    }

    myOrder=()=>{
        return axios.get(`${this.baseUrl}/userOrders/${userId}`)
    }
    cancelOrder=(orderId,userId)=>{
        return axios.put(`${this.baseUrl}/cancelOrder/${orderId}/${userId}`)
    }
    
}
export default new OrderService();