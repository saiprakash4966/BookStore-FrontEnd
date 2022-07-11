import React, {Component} from 'react';
import TextField from "@material-ui/core/TextField";
import {createTheme, ThemeProvider} from "@material-ui/core/styles";
import {withRouter} from 'react-router';
import LoginService from '../../service/LoginService';
import CustomSnackBar from "../utils/CustomSnackBar";

class SignIn extends Component {

    constructor(props){
        super(props);
        this.state={
            emailId:' ',
            passWord:' ',
            emailError:'',
            passwordError:'',
            emailID:"",
            password:"",
            error:'',
            err:false,
        }
    }

    emailValidation=(event,error)=>{
        let emailPattern="^([a-zA-Z]{3,}([.|_|+|-]?[a-zA-Z0-9]+)?[@][a-zA-Z0-9]+[.][a-zA-Z]{2,3}([.]?[a-zA-Z]{2,3})?)$"
        if(!event.target.value.match(emailPattern)){
            this.setState({
                [event.target.id]: "Enter valid email id",
                [error]: `Invalid ${event.target.name}`,
                err: true,
            })
        }
        else {
            this.setState({
                [event.target.id]: " ",
                [error]:"",
                err: false,
            })
        }
    }

    passwordValidation=(event,error)=>{
        let passwordPattern="^((?=[^@|#|&|%|$]*[@|&|#|%|$][^@|#|&|%|$]*$)*(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9#@$?]{8,})$"
        if(!event.target.value.match(passwordPattern)){
            this.setState({
                [event.target.id]: "Enter valid password",
                [error]: `Invalid ${event.target.name}`,
                err: true,
            })
        }
        else {
            this.setState({
                [event.target.id]: " ",
                [error]:"",
                err: false,
            })
        }
    }

    changeState = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        })
    }

    userLogin=()=>{
        const loginData={
            emailId:this.state.emailID,
            password:this.state.password,
        }
        console.log(loginData)
        const loginOrLogout =  window.location.href.includes('/user/login')
        console.log(loginOrLogout);

        LoginService.login(loginData).then(response=>{
            console.log(response)
            let severity=response.data.message==="LOGIN SUCCESSFUL" ? "success" : "error";
            console.log(severity);
            severity === "success" ? this.props.snack(response.data.message, severity) : this.props.snack(response.data.message, severity);
            console.log(severity);
            severity === "success"?localStorage.setItem('Authorization',response.data.data.userId) : localStorage.setItem('Authorization',"null")
            severity === "success"?localStorage.setItem('Name',response.data.data.fullName) : localStorage.setItem('Name',"null")
            console.log(severity);
            console.log(response.data.data.userId)
            console.log(response.data.data.fullName)
            console.log(response.headers)
            this.clear(severity)
            // loginOrLogout ? window.location.reload(true) : this.clear(severity)
        }).catch(error=>{
            console.log(error)
        })
    }

    clear=(severity)=>{
        if(severity === "success")
        {
            setTimeout(() => {
                this.setState({
                    emailId:' ',
                    passWord:' ',
                    emailError:'',
                    passwordError:'',
                    emailID:"",
                    password:"",
                    error:'',
                    err:false,
                    snackFlag: false, snackMessage: "",severity:'success'
                },()=>this.props.history.push(`/`))
            }, 2000);
        }
        if(severity === "error"){
            setTimeout(() => {
                this.setState({
                    snackFlag: false, snackMessage: "",severity:'error'
                })
            }, 2000);
        }
    }


    render() {

        const theme = createTheme({
            palette: {
                primary: {
                    main: '#a52a2a',
                },
            },
        });

        return (
            <div>
                <div className="sign-in-htm">
                    <ThemeProvider theme={theme}>
                        <div className="group1">
                            <TextField id="emailId"
                                       name="emailID"
                                       label="Email Id"
                                       variant="outlined"
                                       value={this.state.emailID}
                                       fullWidth required autoComplete="off"
                                       onChange={this.changeState}
                                       error={this.state.emailError}
                                       onBlur={(e)=>this.emailValidation(e,"emailError")}
                                       helperText={this.state.emailId}
                            />
                        </div>
                        <div className="group1">
                            <TextField id="passWord"
                                       name="password"
                                       label="Password"
                                       type="password"
                                       value={this.state.password}
                                       variant="outlined"
                                       fullWidth required autoComplete="off"
                                       onChange={this.changeState}
                                       error={this.state.passwordError}
                                       onBlur={(e)=>this.passwordValidation(e,"passwordError")}
                                       helperText={this.state.passWord}
                            />
                        </div>
                        <div className="foot-lnk">
                            <a href="/forgot/password">Forgot Password?</a>
                        </div>
                        <div className="group1">
                            <button className="login-button" onClick={this.userLogin}>Login</button>
                        </div>
                    </ThemeProvider>
                </div>
                {this.state.snackFlag &&
                    <CustomSnackBar message={this.state.snackMessage} severity={this.state.severity} />
                }
            </div>
        );
    }
}
export default withRouter(SignIn);