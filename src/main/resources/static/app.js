let jwtToken = null;

const wsProtocol = window.location.protocol === "https:" ? "wss" : "ws";
const stompClient = new StompJs.Client({
    brokerURL: `${wsProtocol}://${window.location.host}/chat-app`,
    reconnectDelay: 5000,
});

stompClient.beforeConnect = async () => {
    jwtToken = await fetchJwtToken();
    stompClient.connectHeaders = {
        Authorization: `Bearer ${jwtToken}`,
    };
};

stompClient.onConnect = async (frame) => {
    setConnected(true);
    console.log(`Connected: ${frame.command ?? "STOMP"}`);

    await loadMessages();

    stompClient.subscribe("/topic/messages", (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error("Error with websocket", error);
};

stompClient.onStompError = (frame) => {
    console.error(`Broker reported error: ${frame.headers["message"]}`);
    console.error(`Additional details: ${frame.body}`);
    redirectToAuthForm();
};

function setConnected(connected) {
    document.getElementById("connect").disabled = connected;
    document.getElementById("disconnect").disabled = !connected;
    document.getElementById("conversation").style.display = connected ? "block" : "none";
    document.getElementById("messages").innerHTML = "";
}

async function connect() {
    try {
        await ensureAuthenticated();

        if (!stompClient.active) {
            stompClient.activate();
        }
    } catch (error) {
        console.error("Connection refused", error);
        redirectToAuthForm();
    }
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const name = document.getElementById("name").value.trim();

    if (!name || !stompClient.connected) {
        return;
    }

    stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify({ name }),
    });

    document.getElementById("form").reset();
}

function showGreeting(message) {
    const tr = document.createElement("tr");
    const td = document.createElement("td");
    td.textContent = message;
    tr.appendChild(td);
    document.getElementById("messages").appendChild(tr);
}

(function () {
    document.querySelectorAll("form").forEach(form => form.addEventListener("submit", (ev) => ev.preventDefault()));
    document.getElementById("connect").addEventListener("click", () => connect());
    document.getElementById("disconnect").addEventListener("click", () => disconnect());
    document.getElementById("send").addEventListener("click", () => sendName());
    setConnected(false);
})()

async function ensureAuthenticated() {
    jwtToken = await fetchJwtToken();
    return jwtToken;
}

async function fetchJwtToken() {
    const response = await fetch("/api/auth/token");

    if (response.status === 401) {
        redirectToAuthForm();
        throw new Error("Authentication required");
    }

    if (!response.ok) {
        throw new Error("Unable to retrieve JWT token");
    }

    const payload = await response.json();
    return payload.token;
}

async function loadMessages() {
    const response = await fetch("/api/messages", {
        headers: {
            Authorization: `Bearer ${jwtToken}`,
        },
    });

    if (response.status === 401) {
        redirectToAuthForm();
        return;
    }

    if (!response.ok) {
        throw new Error("Unable to fetch chat history");
    }

    const listMessages = await response.json();
    listMessages.forEach(listItemMessage => showGreeting(listItemMessage.content));
}

function redirectToAuthForm() {
    if (window.location.pathname !== "/auth-form") {
        window.location.href = "/auth-form";
    }
}