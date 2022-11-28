const http = require('http');

//*HTTP SERVER
let port = process.env.PORT || 9004;
let ip = process.env.IP || 'localhost';

const server = http.createServer().listen(port, ip, function(){
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

    socket.on('disconnect', () => { /* … */ });
});