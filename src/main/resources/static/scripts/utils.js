/** SERVICES */
export const isInvalidDate = (date) => isNaN(new Date(date));

/**
 * 
 * Returns date in human readable format.
 * 
 * @param {Date} date 
 * @param {string} local - Human language used to format date/time 
 * @returns {Intl.DateTimeFormat} - Date/time in format MM/DD/YYYY, HH:MM
 */
export function getFormatDateTime(date, local="fr") {
    return new Intl.DateTimeFormat(local, {
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
 * Get all messages from database when user is authenticated
 * Returns an array of Message (object)
 * 
 * @param {string} url - API url to get all messages 
 * @param {string} token - JWT token
 * @return {object[]}
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

    return await response.json();
}


/** UI */
/**
 * 
 * @param {object} body - Message object to format in UI component for chat 
 */
export function createMessageElement(body) {
    const { sendBy, content, sendAt } = body;
    const tr = document.createElement("tr");
    const td = document.createElement("td");
    td.innerHTML = `<span>${sendBy}</span><p>${content}</p><span>${isInvalidDate(sendAt) ? "Date inconnue" : getFormatDateTime(new Date(sendAt))}</span>`;
    tr.appendChild(td);
    document.getElementById("messages").appendChild(tr);
}