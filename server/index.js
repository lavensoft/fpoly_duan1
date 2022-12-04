const http = require('http');
const express = require('express');
const bodyParser = require('body-parser');
const Momo = require('./Momo');

//*HTTP SERVER
let port = process.env.PORT || 9004;
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

const server = http.createServer(app).listen(port, ip, function(){
    console.log('IO SERVER STARTED ON %s:%s', ip, port);
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
    })

    socket.on('/products/dimension/add', data => {
        socket.emit('/products/dimension/add', data);
        socket.broadcast.emit('/products/dimension/add', data);
    })

    socket.on('disconnect', () => { /* … */ });
});

//* API
app.post("/create_pay", async (req, res) => {
    let body = req.body;

    let result = await Momo.createEWallet(body.amount, body.extraData);

    res.send(JSON.stringify(result.data));
});

app.post("/complete_order", async (req, res) => {
    let body = req.body;

    res.status(204).send();
});