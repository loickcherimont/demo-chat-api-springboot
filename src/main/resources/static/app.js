const stompClient = new StompJs.Client({
    brokerURL: "ws://localhost:8080/chat-app",
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log(`Connected: ${frame}`);


    // Fetch messages
    fetch("http://localhost:8080/api/messages")
        .then(response => response.json())
        .then(listMessages => listMessages.forEach(listItemMessage => showGreeting(listItemMessage.content)));

    stompClient.subscribe("/topic/messages", (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    })
}

stompClient.onWebSocketError = (error) => {
    console.error("Error with websocket", error);
}

stompClient.onStompError = (frame) => {
    console.error(`Broker reported error: ${frame.headers["message"]}`);
    console.error(`Additional details: ${frame.body}`);
}

function setConnected(connected) {

    document.getElementById("connect").disabled = connected;
    document.getElementById("disconnect").disabled = !connected;
    
    document.getElementById("conversation").style.display = connected ? "block" : "none";

    document.getElementById("messages").innerHTML = "";
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify({ 'name': document.getElementById("name").value}),
    });
}

function showGreeting(message) {
    const tr = document.createElement("tr");
    const td = document.createElement("td");
    td.textContent = message;
    tr.appendChild(td);
    document.getElementById("messages").appendChild(tr);
}

(function() {
    document.querySelectorAll("form").forEach(form => form.addEventListener("submit", (ev) => ev.preventDefault()));
    document.getElementById("connect").addEventListener("click", () => connect());
    document.getElementById("disconnect").addEventListener("click", () => disconnect());
    document.getElementById("send").addEventListener("click", () => sendName());
})()