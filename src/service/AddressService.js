import axios from 'axios';


const id=localStorage.getItem('Authorization')
const userId = JSON.parse(id);
class AddressService {
    baseUrl= "http://localhost:8080/address";


    getDetails(data){
        return axios.post(`${this.baseUrl}/userDetails`, data);
    }
    UserDetails(){
        return axios.get(`${this.baseUrl}/userAddress/${userId}`);
    }

}
export default new AddressService();