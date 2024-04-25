## Spring Boot 快速开发脚手架
使用java8，集成Spring Security

已实现的登录功能包括：  
- 用户名密码登录 /login  
- 模拟授权码登录 /login/code

登录功能通过继承AbstractAuthenticationProcessingFilter实现的
CustomUsernamePasswordAuthFilter和CodeAuthenticationFilter进行登录拦截。
