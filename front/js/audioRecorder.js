navigator.mediaDevices.getUserMedia();






async function micAcess(){
    stream = await navigator.mediaDevices.getUserMedia({
        audio: true
    });
}

function startMic(){
    recorder = new MediaRecorder(stream, {mimeType: "audio/webm"})

    recorder.ondataavailable = (event) =>{
        console.log(event.data.size)
        console.log(socket.readyState)
        if(event.data.size > 0 && socket.readyState === WebSocket.OPEN)
            socket.send(event.data);

    }

    recorder.onstop = () => {
        socket.send("FIM_DO_AUDIO")
    }

    recorder.start(500)
}

function muteMic(){
    recorder.stop()
}


