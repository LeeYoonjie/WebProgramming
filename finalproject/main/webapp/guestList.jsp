<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>방명록 관리 앱</title>
</head>
   <body>
   <div class="container w-75 mt-5 mx-auto">
   <h2 style="text-align:center";>방명록 목록</h2>
   <br>
        <div class="row">
            <table class="table table-bordered" style="text-align:center; border:1px solid #dddddd">
                <thead>
                    <tr>
                        <th style="background-color:#eeeeee; text-align:center;">번호</th>
                        <th style="background-color:#eeeeee; text-align:center;">작성자</th>
                        <th style="background-color:#eeeeee; text-align:center;">이메일</th>
                        <th style="background-color:#eeeeee; text-align:center;">작성일</th>
                        <th style="background-color:#eeeeee; text-align:center;">제목</th>
                    </tr>
                </thead>
                <c:forEach var="guest" items="${guestlist}" varStatus="status">
                <tbody >
                <tr>
                    <td>${status.count}</td>
                    <td>${guest.username}</td>
                    <td>${guest.email}</td>
                    <td>${guest.date}</td>
                    <td><a href="GuestControl?action=getGuest&aid=${guest.aid}" class="text-decoration-none">${guest.title}</a></td>
                    </tr>             
                </tbody>
                </c:forEach>
           		<br>
            </table>
            <a class="btn btn-primary" href="/jwbook/finalproject/guestwrite.jsp" role="button">등록</a>
        </div>
	  </div>
</body>
</html>