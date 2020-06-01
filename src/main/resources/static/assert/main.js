
// 连接socket
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame)
        stompClient.subscribe('/topic/greetings', function (greeting) {
            console.log('greetings: ' + greeting);
            return JSON.parse(greeting.body).content
        })
        sendMsg("hello", "lbp");
    })
}

// 发送消息
function sendMsg(path, message) {
    stompClient.send("/msg/" + path, {}, JSON.stringify({'name': message}));
}