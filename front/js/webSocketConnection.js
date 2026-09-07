function socketConnection() {
    socket = new WebSocket("ws://localhost:8080/ws");

    socket.onopen = () => {
        console.log("Conectado ao servidor")

    };

    socket.onmessage = (event) =>{
        console.log("Server: ", event.data)
    }

    socket.onclose = (event) => {
        console.log("Conexão fechada")
        console.log("Codigo: ", event.code)
        console.log("Mensagem: ", event.reason)
        console.log("Fechamento: ", event.wasClean)
    };

    socket.onerror = (erro) => {
        console.log("ERROR", erro)
    }

}