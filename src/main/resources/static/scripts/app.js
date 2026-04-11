import { connect, disconnect, sendMessage } from "./websocket.js";


(function () {
    connect();
    document.querySelectorAll("form").forEach(form => form.addEventListener("submit", (ev) => ev.preventDefault()));
    document.getElementById("disconnect").addEventListener("click", () => disconnect());
    document.getElementById("send").addEventListener("click", () => sendMessage());
})()

