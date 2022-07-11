import React, {Component} from 'react';
import CbHeader from "../utils/CbHeader";
import CbFooter from "../utils/CbFooter";
import '../../css/MyOrders.css'
import Grid from "@material-ui/core/Grid";
import MyOrders from "./MyOrders";
import Typography from "@material-ui/core/Typography";
import OrderService from '../../service/OrderService';
import Button from "@material-ui/core/Button";

class MyOrdersList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            orderList: []
        }
    }

    handleCancel = (id,userId) =>{
        console.log("21")
        OrderService.cancelOrder(id,userId).then(response =>{
            console.log(response.data.data)


            {
                alert("Order successfully cancelled");
            }

            window.location.reload();
        })


    }


    getPlacedOrder = () => {
        OrderService.myOrder().then(response => {
            this.setState({
                orderList: response.data.data
            })
        }).catch((error) => {
            console.log(error)
        })
    }

    componentDidMount() {
        this.getPlacedOrder();
    }


    render() {
        let order = this.state.orderList
        console.log(order);
        return (
            <div>
                <div className="main-order">
                    <Grid container className="ordercontainer">
                        <CbHeader/>
                        <ul className="myorderbreadcrumb">
                            <li><a href="/">Home</a></li>
                            <li>My Orders</li>
                        </ul>

                        <div>
                            {order.reverse().map((book) => {
                                return (
                                    <div>

                                        {order.slice(0,1).map((books)=>{
                                            return (
                                                <div className="myorderdiv">
                                                    <Typography component="h2" id="orderdate">Ordered
                                                        Date: {books.orderDate}</Typography>
                                                    <Typography id="orderid">Order
                                                        ID: {books.id}</Typography>
                                                    <Button onClick={() => this.handleCancel(books.id,books.userId)}>
                                                        cancel
                                                    </Button>

                                                </div>
                                            )
                                        })}

                                        <div
                                            className={book.length === 1 ? "order-block" : book.length === 2 ? "orderblock" : "orderblock1"}>
                                            {order.slice(0,1).map((abc, ind) => {
                                                return (
                                                    <div className="order-list">
                                                        <MyOrders data={abc}/>
                                                    </div>
                                                )
                                            })}
                                        </div>

                                    </div>)
                            })}
                        </div>
                    </Grid>
                </div>
                <CbFooter/>
            </div>
        );
    }
}

export default MyOrdersList;