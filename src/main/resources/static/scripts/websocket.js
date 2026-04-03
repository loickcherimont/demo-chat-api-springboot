import { createMessageElement, loadMessages, redirectToAuthForm } from "./utils.js";

let jwtToken = null;

const wsProtocol = window.location.protocol === "https:" ? "wss" : "ws";
const stompClient = new StompJs.Client({
    brokerURL: `${wsProtocol}://${window.location.host}/chat-app`,
    reconnectDelay: 5000,
});

stompClient.beforeConnect = async () => {
    jwtToken = localStorage.getItem("CUSTOM_JWT_TOKEN");
    stompClient.connectHeaders = {
        Authorization: `Bearer ${jwtToken}`,
    };
};

stompClient.onConnect = async () => {
    await loadMessages("/api/messages", jwtToken);
    stompClient.subscribe("/topic/messages", (message) => {
        createMessageElement(JSON.parse(message.body));
    });
};

// Handle errors on STOMP environment
stompClient.onWebSocketError = (error) => {
    console.error("Error with websocket", error);
};

stompClient.onStompError = (frame) => {
    console.error(`Broker reported error: ${frame.headers["message"]}`);
    console.error(`Additional details: ${frame.body}`);
    redirectToAuthForm();
};

export async function connect() {
    try {
        if (!stompClient.active) {
            stompClient.activate();
        }
    } catch (error) {
        console.error("Connection refused", error);
        redirectToAuthForm();
    }
}

export function disconnect() {
    stompClient.connectHeaders = {
        Authorization: null,
    };
    localStorage.removeItem("CUSTOM_JWT_TOKEN");
    stompClient.deactivate();
    redirectToAuthForm();
}

export function sendMessage() {
    const content = document.getElementById("content").value.trim();

    if (!content || !stompClient.connected) {
        return;
    }

    stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify({ content }),
    });

    document.getElementById("form").reset();
}