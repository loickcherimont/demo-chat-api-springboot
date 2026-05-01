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

3. When the terminal shows line *'Started DemoWebsocketSpringbootApplication in ...'*,  
the project will be available on :  

[http://localhost:8080/auth-form](http://localhost:8080/auth-form 'Link to the project')  

You will see a screen similar as the next picture.  

![Preview](./github/preview.png 'Sign In | Real-Time Chat Backend API')

## ▶️ Usage

> [!IMPORTANT]  
> Use test accounts to see quickly how to the application runs.  

### User 1
- Email: john.doe  
- Password: test123  

### User 2
- Email: jane.doe  
- Password: user123

> [!IMPORTANT]  
> If you don't use the previous sample, JavaScript will enforce you to complete input fields.  

![Username field not completed](./github/auth-form-html5-not-filled-required-inputs-message 'Message for not filled username field | Real-Time Chat Backend API')

> [!IMPORTANT]  
> The application is hosted on a free Render environment.  
> On the first request after a period of inactivity, startup may take up to **30–50 seconds**.  
> Please be patient if the API takes a few moments to respond initially.  
> We are working on the better hosting system to reduce this delay.

Render Live API on [https://springboot-jwt-secure-chat-api.onrender.com/auth-form](https://springboot-jwt-secure-chat-api.onrender.com/auth-form 'Render Live API | Real-Time Chat Backend API')

## 🔑 License

This application is powered by **Loick CHERIMONT**

---

<div align="center">&copy; 2026 x Loick CHERIMONT</div>
