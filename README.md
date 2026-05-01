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

### Sample data (users)

| ID  | Username | Password |
| --- | -------- | -------- |
| 1   | john.doe | test123  |
| 2   | jane.doe | user123  |


> [!IMPORTANT]  
> If you don't use the previous sample, JavaScript will enforce you to complete input fields.  

![Message and screen for username field not completed](./github/auth-form-html5-not-filled-required-inputs-message.png 'Message and screen for username field not completed | Real-Time Chat Backend API')

### Authentication Form | Invalid credentials

Actually, if you use unknown users (out of the previous table) or wrong login data, you will encounter a JavaScript error message about `invalid credentials`.  

![Message and screen for invalid credentials](./github/invalid-credentials-screen.png 'Message and screen for invalid credentials | Real-Time Chat Backend API')

### Real-Time Chat Backend API | Blank message

Actually, if you try to send blank message, you will encounter a JavaScript error message about `Complete the message field`.  

![Message and screen for blank message](./github/empty-message.png 'Message and screen for blank message | Real-Time Chat Backend API')

### Normal flow

Else, if you connect to the application using the previous 2 users, you'll have a real time exchange.

![Exchange between 2 fake users](./github/example-exchange.png 'Exchange between 2 fake users | Real-Time Chat Backend API')

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
