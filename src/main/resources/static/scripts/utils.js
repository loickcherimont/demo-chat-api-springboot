export function getFormatDateTime(date) {
    return new Intl.DateTimeFormat("en-GB", {
        dateStyle: "short",
        timeStyle: "short",
    }).format(date);
}

export function redirectToAuthForm() {
    if (window.location.pathname !== "/auth-form") {
        window.location.href = "/auth-form";
    }
}

/**
 * 
 * Get all messages from API
 * Useful for history
 * 
 * @param {string} url - API url to get all messages 
 * @param {string} token - JWT token
 * @returns 
 */
export async function loadMessages(url, token) {
    const response = await fetch(url, {
        headers: {
            Authorization: `Bearer ${token}`,
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
    listMessages.forEach(listItemMessage => createMessageElement(listItemMessage.content));
}

export function createMessageElement(body) {
    const { sendBy, content, sendAt } = body;
    const tr = document.createElement("tr");
    const td = document.createElement("td");
    console.log(sendAt, getFormatDateTime(new Date(sendAt)));
    td.innerHTML = `<span>${sendBy}</span><p>${content}</p><span>${getFormatDateTime(new Date(sendAt))}</span>`;
    tr.appendChild(td);
    document.getElementById("messages").appendChild(tr);
}