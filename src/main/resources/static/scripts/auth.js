/**
 * Send user data to backend JWT API
 * Get token and store it into localstorage
 * Redirect after authentication to main chat app
 */

import { redirectToAuthForm } from "./utils.js";

document
    .getElementById("authForm")
    .addEventListener("submit", handleSubmit)

async function handleSubmit(ev) {
    ev.preventDefault();

    const formData = new FormData(ev.target);

    const requestDto = { username: formData.get("username"), password: formData.get("password") };

    const token = await fetchJwtToken("/api/auth/signin", requestDto);

    if (!token) {
        console.error("Token is missing");
        ev.target.reset();
        return;
    }

    localStorage.setItem("CUSTOM_JWT_TOKEN", token);
    

    window.location.href = "/";
}

async function fetchJwtToken(url, requestBody) {
    const response = await fetch(url, {
        method: "POST",
        body: JSON.stringify(requestBody),
        headers: {
            "Content-Type": "application/json",
        },
    });

    if (response.status === 401) {
        redirectToAuthForm();
        const ex = await response.json();
        alert(ex.message)
        console.error(ex);
        return;
    }

    if (!response.ok) {
        const ex = await response.json();
        alert(ex.message)
        console.error(ex);
        throw new Error("Unable to retrieve JWT token");
    }

    const payload = await response.json();
    return payload.token;
}