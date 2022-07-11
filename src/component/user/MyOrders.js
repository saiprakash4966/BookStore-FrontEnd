import React, {Component} from 'react';
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import image from "../../asset/Crown.jpg";
import OrderService from '../../service/OrderService';
import {Link} from "react-router-dom";
import Button from "@material-ui/core/Button";

class MyOrders extends Component {

    constructor(props) {
        super(props);
        this.state = {
            bookList: []
        }
    }

    // handleCancel = (id,userId) =>{
    //     console.log("20")
    //     OrderService.cancelOrder(id,userId).then(response =>{
    //         console.log(response.data.data)
    //         window.location.reload();
    //     })
    //
    // }

    getBooks = () => {
        OrderService.myOrder().then(response => {
            console.log(response.data.data);
            this.setState({
                bookList: response.data.data
            })
        }).catch((error) => {
            console.log(error);
        })
    }

    componentDidMount=()=>{
        this.getBooks();
    }

    render() {
        let book = this.state.bookList
        console.log(book);
        return (
            <div>
                <Grid className="order-grid" item xs={12} sm={12} md={12} lg={12} xl={12} spacing={2}>
                    <div id="orderimg">
                        {/*<img src={image} alt={"Not found"} className="order-img"/>*/}
                    </div>
                    {book.map((books)=>{
                        return(
                            <div className="order-div">

                                {/*<Typography id="order-bookname"*/}
                                {/*            component="h2">BookName{books.bookName}</Typography>*/}
                                {/*<Typography variant="body2" color="textSecondary"*/}
                                {/*            id="order-authorname">Author{books.bookAuthor}</Typography>*/}
                                {/*<Typography component="h2"*/}
                                {/*            id="order-cost">Qty. {books.quantity}</Typography>*/}
                                <Typography component="order-userId"
                                            id="h2">userId {books.userId}</Typography>
                                <Typography component="h2"
                                            id="order-cost">totalPrice RS. {books.totalPrice}</Typography>

                                {/*<Button onClick={() => this.handleCancel(books.id,books.userId)}>*/}
                                {/*        cancel*/}
                                {/*</Button>*/}
                                {/*<Typography component="h2"*/}
                                {/*            id="order-cancel">cancel {books.cancel}</Typography>*/}



                            </div>

                        )
                    })}
                </Grid>
                <Divider style={{marginLeft: "1%", marginRight: "1%"}}/>
            </div>
        );
    }
}

export default MyOrders;