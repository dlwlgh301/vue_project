/*
 User API 예시
 */
import axios from "axios";
const requestLogin = (data,callback,errorCallback) => {
    axios
    .post('http://localhost:8080/account/login?email='+JSON.stringify(data["email"])+'&password='+JSON.stringify(data["password"]))
    .then(res => {
    console.log(res);
    })


}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
    
}

export default UserApi
