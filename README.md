# 💬 Real-Time Chat Backend API

## 🌱 About

This is a secure **Real-Time Chat Backend API** based on JWT authentication made with Spring Boot and JavaScript.

## 🖥️ Tech stack

- **Backend**  : Java 21, Spring Boot, Spring Security, Websocket / STOMP
- **Frontend** : JavaScript ES6, StompJS, Bootstrap 5

### Prerequisites

Before project running, you'll need to create 2 files at the root :

- `.env`
- `.env.properties`

Each files must have as content : 

```bash
SECRET_KEY=<VALUE>
```

> [!IMPORTANT]
> Generate a random key using `openssl rand -base64 64`.
> Replace `<VALUE>` with the result of the previous command.

## 🚀 Setup

1. Clone the repository using :

```bash
git clone https://www.github.com/loickcherimont/springboot-jwt-secure-chat-api.git
```

2. Go in the projet and run it :

```bash
cd springboot-jwt-secure-chat-api
./mvnw clean spring-boot:run
```

3. Here you are on the real-time secure chat application

![Preview](./github/preview.png)

## 👤 Test Accounts

Test accounts are available to make evaluation easier:

### User 1
- Email: john.doe  
- Password: test123  

### User 2
- Email: jane.doe  
- Password: user123

> [!IMPORTANT]
> The application is hosted on a free Render environment.
> On the first request after a period of inactivity, startup may take up to **30–50 seconds**.
> Please be patient if the API takes a few moments to respond initially.

Live API on : https://springboot-jwt-secure-chat-api.onrender.com/

## 🔑 License

This application is powered by **Loick CHERIMONT**

---

<div align="center">&copy; 2026 x Loick CHERIMONT</div>
