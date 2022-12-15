const http = require('http');
const express = require('express');
const bodyParser = require('body-parser');
const Momo = require('./Momo');
const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const Config = require('./config');

//* DATABASE
mongoose.connect(Config.DATABASE);

//* DATABASE SCHEMA
const Orders = mongoose.model('orders', new Schema({
    author: String,
    customer: String,
    description: String,
    dateCreated: String,
    paymentMethod: String,
    paymentStatus: String,
    paymentOrderId: String
}))

//*HTTP SERVER
let port = Config.DEBUG ? 3006 : 9004;
let ip = process.env.IP || 'localhost';

const app = express();

app.use(bodyParser.json());
app.use(express.json());
app.use(express.static('public'));

//Add CORS
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', '*');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

const server = http.createServer(app).listen(port, function(){
    console.log('SERVER STARTED ON HOST:%s', port);
});

//*SOCKET IO
const io = require('socket.io')(server);

io.on('connection', socket => {
    console.log("CONNECT")
    console.log(socket.id);

    socket.on('user-join', data => {
        socket.broadcast.emit("hello", {"name": "Test A", "description": "a", "banner": "https://orios-server.lavenes.com/uploads/63452fa601a4792a134bf3f2/media/m43hCvhoppTFwB5XhaP8rFvQ5.jpg", "_id": {"$oid": "637bdc8196a9576db02cfe98"}});
    });

    //*PRODUCTS
    socket.on('/products/add', data => {
        socket.emit('/products/add', data);
        socket.broadcast.emit('/products/add', data);
    });

    socket.on('/products/update', data => {
        socket.emit('/products/update', data);
        socket.broadcast.emit('/products/update', data);
    });

    socket.on('/products/delete', id => {
        socket.emit('/products/delete', id);
        socket.broadcast.emit('/products/delete', id);
    });

    //* DIMENSIONS
    socket.on('/products/dimension/add', data => {
        socket.emit('/products/dimension/add', data);
        socket.broadcast.emit('/products/dimension/add', data);
    });

    socket.on('/productss/dimension/update', data => {
        socket.emit('/productss/dimension/update', data);
        socket.broadcast.emit('/productss/dimension/update', data);
    });

    socket.on('/productss/dimension/delete', id => {
        socket.emit('/productss/dimension/delete', id);
        socket.broadcast.emit('/productss/dimension/delete', id);
    });

    //* PROMOTIONS
    socket.on('/promotions/add', data => {
        socket.emit('/promotions/add', data);
        socket.broadcast.emit('/promotions/add', data);
    });

    //* USERS
    socket.on('/users/add', data => {
        socket.emit('/users/add', data);
        socket.broadcast.emit('/users/add', data);
    });

    socket.on('disconnect', () => { /* … */ });
});

//* API
app.post("/create_pay", async (req, res) => {
    let body = req.body;

    let result = await Momo.createEWallet(body.amount, body.extraData);

    res.send(JSON.stringify(result.data));
});

//* PAYMENT IPN
app.post("/complete_order", async (req, res) => {
    let body = req.body;

    await Orders.updateOne({
        paymentOrderId: body.orderId
    }, {
        paymentStatus: "success"
    });

    res.status(204).send();
});