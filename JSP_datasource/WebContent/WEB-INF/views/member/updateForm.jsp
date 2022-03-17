<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">

<title></title>
</head>
<body>
	
	<div class="card card-info">
	  <div class="card-header">
	    <h3 class="card-title">회원 정보 수정</h3>
	  </div>
	  <!-- /.card-header -->
	  <!-- form start -->
	  <form class="form-horizontal" action="updateMember" method="post">
	    <div class="card-body" style="padding:15px 15px 0px 20px">
	    
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">아이디</label>
	        <div class="col-sm-10">
	          <input type="hidden" class="form-control" id="inputPassword3" placeholder="ID" name="id" value="${member.id }">
	          <input type="text" class="form-control" id="inputPassword3" placeholder="ID" value="${member.id }" disabled>
	        </div>
	      </div>
	    
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">비밀번호</label>
	        <div class="col-sm-10">
	          <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="pwd">
	        </div>
	      </div>
	      
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">비밀번호 확인</label>
	        <div class="col-sm-10">
	          <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="pwd2">
	        </div>
	      </div>
	      
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">이름</label>
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="inputPassword3" placeholder="ID" name="name" value="${member.name }">
	        </div>
	      </div>
	      
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">전화번호</label>
	        <div class="col-sm-10">
	          <input type="tel" class="form-control" id="inputPassword3" placeholder="ID"  name="phone" value="${member.phone }">
	        </div>
	      </div>
	      
	      <div class="form-group row">
	        <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
	        <div class="col-sm-10">
	          <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email" value="${member.email }">
	        </div>
	      </div>
	      
	      <div class="form-group row">
	        <label for="inputPassword3" class="col-sm-2 col-form-label">주소</label>
	        <div class="col-sm-10">
	          <input type="text" class="form-control" id="inputPassword3" placeholder="ID" name="address" value="${member.address }">
	        </div>
	      </div>
	      
	    </div>
	    <!-- /.card-body -->
	    <div class="card-footer">
	      <button type="submit" class="btn btn-info">수정</button>
	      <button type="submit" class="btn btn-default float-right" onclick="window.close()">Cancel</button>
	    </div>
	    <!-- /.card-footer -->
	  </form>
	</div>


<!-- <body class="register-page" style="min-height: 570.8px;"> -->
<!-- <div class="register-box"> -->
<!--   <div class="register-logo"> -->
<!--     <a href="../../index2.html"><b><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">회원 정보 수정</font></font></b><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"></font></font></a> -->
<!--   </div> -->

<!--   <div class="card"> -->
<!--     <div class="card-body register-card-body"> -->
<!-- <!--       <p class="login-box-msg"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">회원 등록</font></font></p> -->

<!--       <form action="updateMember" method="post"> -->
      
<!--         <div class="input-group mb-3"> -->
<%--           <input type="hidden" class="form-control" placeholder="아이디" name="id" value="${member.id }"> --%>
<%--           <input type="text" class="form-control" id="chese" placeholder="아이디" value="${member.id }" disabled> --%>
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-user"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="input-group mb-3"> -->
<!--           <input type="password" class="form-control" placeholder="비밀번호" name="pwd"> -->
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-lock"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="input-group mb-3"> -->
<!--           <input type="password" class="form-control" placeholder="비밀번호 재입력" name="pwd2"> -->
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-lock"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="input-group mb-3"> -->
<%--           <input type="text" class="form-control" placeholder="이름" name="name" value="${member.name }"> --%>
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-user"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="input-group mb-3"> -->
<%--           <input type="tel" class="form-control" placeholder="전화번호" name="phone" value="${member.phone }"> --%>
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-user"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->

<!--         <div class="input-group mb-3"> -->
<%--           <input type="email" class="form-control" placeholder="이메일" name="email" value="${member.email }"> --%>
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-envelope"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="input-group mb-3"> -->
<%--           <input type="text" class="form-control" placeholder="주소" name="address" value="${member.address }"> --%>
<!--           <div class="input-group-append"> -->
<!--             <div class="input-group-text"> -->
<!--               <span class="fas fa-user"></span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
        
<!--         <div class="row"> -->
<!--           <div class="col-8" > -->
<!--             <div style="width: 210px"> -->
<!--               <div style="width: 100px; float: left" > -->
<!--                 <button type="submit" class="btn btn-primary btn-block"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">수정</font></font></button> -->
<!--               </div> -->
<!--               <div style="width: 100px; float: right">   -->
<!-- 		        <button type="submit" class="btn btn-primary btn-block" onclick="window.close()"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">취소</font></font></button> -->
<!-- 		      </div>   -->
<!--             </div> -->
            
<!--           </div> -->
<!--           /.col -->
<!--         </div> -->
<!--       </form> -->

<!--     </div> -->
<!--    	<div style="width: 100px"> -->
<!--     </div> -->
<!--     /.form-box -->
<!--   </div>/.card -->
<!-- </div> -->
<!-- <!-- /.register-box -->
	
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	
</body>
</html>