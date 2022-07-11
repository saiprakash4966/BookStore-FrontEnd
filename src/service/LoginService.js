import axios from 'axios';

const id=localStorage.getItem('Authorization')
const userId = JSON.parse(id);
class LoginService {
    baseUrl= "http://localhost:8080/userregistrationservice";

    login(data){
        return axios.post(`${this.baseUrl}/userlogin`,data);
    }

    register(data){
        return axios.post(`${this.baseUrl}/register`,data);
    }

    userDetails(){
        return axios.get(`${this.baseUrl}/get/${userId}`);
    }

}
export default new LoginService();