import { connect, disconnect, sendMessage } from "./websocket.js";

// Verify if token is available before load chat app
const token = localStorage.getItem("CUSTOM_JWT_TOKEN");

if (!token) {
    window.location.href = "/auth-form";
}

(function () {
    connect();
    document.getElementById("form").addEventListener("submit", (ev) => ev.preventDefault());
    document.getElementById("disconnect").addEventListener("click", () => disconnect());
    document.getElementById("send").addEventListener("click", () => sendMessage());
})();

