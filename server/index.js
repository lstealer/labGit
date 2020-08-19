const http = require('http');
const express = require('express');
const socketio = require('socket.io');
const cors = require('cors');
const axios = require('axios');
const {addUser, removeUser, getUser, getUsersInRoom} = require('./users');

const router = require('./router');
const {getSoloInRoom, addSolo, removeSoloUser,getSoloUser} = require("./solo");


const app = express();
const server = http.createServer(app);
const io = socketio(server);
let id = [];
app.use(cors());
app.use(router);
let joiner = 0;
let room = 0;
let content = {};
io.on('connect', (socket) => {
    socket.on('join', ({name, room}, callback) => {
        const {error, user} = addUser({id: socket.id, name, room});
        console.log(name + " " + room)
        console.log(socket.id)
        console.log("Added user:" + user.name + " " + user.room)
        if (error) return callback(error);

        socket.join(user.room);

        socket.emit('message', {user: 'admin', text: `${user.name}, welcome to room ${user.room}.`});
        socket.broadcast.to(user.room).emit('message', {user: 'admin', text: `${user.name} has joined!`});

        io.to(user.room).emit('roomData', {room: user.room, users: getUsersInRoom(user.room)});

        callback();
    });

    socket.on('sendMessage', (message, callback) => {
        const user = getUser(socket.id);
        io.to(user.room).emit('content', {user: user.name, text: message});
        callback();
    });

    //mine
    socket.on('sendCurrent', ({wpm, percentage}, callback) => {
      const user=getSoloUser(socket.id);

        // getSoloUser(socket.id)
        // console.log("Wpm:" + wpm + " percentage:" + percentage)
        // console.log(socket.id)
        // console.log(user)
        io.to(user.room).emit('message', {user: user.name, wpm: wpm, percentage: percentage})
        callback();
    });


    socket.on('disconnect', () => {
        const user = removeSoloUser(socket.id);
        // console.log("ok")
        // console.log(socket.id);
        if (user) {
            io.to(user.room).emit('message', {user: 'Admin', text: `${user.name} has left.`});
            io.to(user.room).emit('roomData', {room: user.room, users: getUsersInRoom(user.room)});
        }
    });

//mine for solo mode
    socket.on('solo', (name, callback) => {
        if (joiner < 4) {
            joiner++;
            id.push({socket: socket, name: name})
            // console.log(joiner);
        }
        if(joiner===4) {
            joiner = 0;
            console.log("Joiner Restart");
           room++;
            let currentRoom=room;
            console.log(id)
            id.forEach((id) => {
                console.log(id.name);
                const {error, user} = addSolo({id: id.socket.id, name: id.name, room});
                if (error) return callback(error);
                id.socket.join(user.room);
                id.socket.emit('message', {user: 'admin', text: `${user.name}, welcome to room ${user.room}.`});
                id.socket.broadcast.to(user.room).emit('message', {user: 'admin', text: `${user.name} has joined!`});
                io.to(user.room).emit('roomData', {room: user.room, users: getSoloInRoom(user.room)});
                callback();
            });
            id = [];
            axios.get("http://localhost:8080/kh-racer/v1/content").then((res) => {
                content = res.data;
                io.to(currentRoom).emit('content', {content: content});
            },);
            let countdown = 5;
            // let time=300000;
            let time = 100;
            let timer;

            let countDowning = setInterval(() => {
                countdown--;
                io.to(currentRoom).emit('countdown',{countdown:countdown});
                if (countdown === 0) {
                    clearInterval(countDowning)
                        timer = setInterval(() => {
                            time--;
                            if (time === 0)
                                clearInterval(timer)
                            io.to(currentRoom).emit('time',{time:time});
                        }, 1000);
                }
            }, 1000);
        }
    });
});

server.listen(process.env.PORT || 5000, () => console.log(`Server has started.`));