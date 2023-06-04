<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>방명록 관리 앱</title>
</head>
<body>
 <body>
   <div class="container w-75 mt-5 mx-auto" data-bs-target="#addForm" aria-controls="addForm">
   <h2 style="text-align:center">방명록 목록</h2>
   <br>
   
        <div class="container" id="addForm">       
            <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
                <form method="post" action="/jwbook/GuestControl?action=addGuest">
                 <thead>
                    <tr>
                    <th><label class="form-label">작성자</label>
                    <td><input type="text" name="username" class="form-control" required></td>
                    </tr>
                    <tr >
                    <th><label class="form-label">이메일</label>
                    <td><input type="text" name="email" class="form-control" required></td>
                    </tr>
                    <tr>
                    <th><label class="form-label">제목</label>
                    <td><input type="text" name="title" class="form-control" required></td>
                    </tr>
                    <tr>
                    <th><label class="form-label">비밀번호</label>
                    <td><input type="text" name="password" class="form-control" required></td>
                    </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan='2'><textarea cols="50" rows="5" name="content" class="form-control"></textarea></td>
                    </tr>
                </tbody>
                
            </table>
            </div>     
           </div>
           <div style="text-align:center">
               <button type="submit" class="btn btn-primary"  >입력</button>
                <button type="reset"  class="btn btn-primary">취소</button>
                <button type="button"  onclick="location.href='http://localhost:8080/jwbook/GuestControl?action=listGuest'" class="btn btn-primary">목록</button>
		</div></form>
		
</body>
</html>
    