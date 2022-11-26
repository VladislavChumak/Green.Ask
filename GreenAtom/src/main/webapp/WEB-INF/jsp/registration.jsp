<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Регистрация</title>
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="userForm">
    <h2>Регистрация</h2>
    <div>
      <form:input type="text" path="fio" placeholder="Fio"
                  autofocus="true"></form:input>
      <form:errors path="fio"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="mail" path="mail" placeholder="Mail"></form:input>
    </div>
    <div>
      <form:input type="telephone_number" path="telephone_number" placeholder="Number"></form:input>
    </div>
    <button type="submit">Зарегистрироваться</button>
  </form:form>
  <a href="/">Главная</a>
</div>
</body>
</html>