const http = require('http');

//*HTTP SERVER
let port = process.env.PORT || 8080;
let ip = process.env.IP || '127.0.0.1';

const server = http.createServer().listen(port, ip, function(){
    console.log('IO SERVER STARTED ON %s:%s', ip, port);
});

//*SOCKET IO
const io = require('socket.io')(server);

var run = function(socket){
    //Test
    socket.emit('greeting', 'Hello from Socket.IO');
    
    //*HANDLERS
    socket.on('user-join', function(data){
        console.log('User %s have joined', data);
    })
}

io.sockets.on('connection', run);