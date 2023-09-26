<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<form id="formJoin" action="${pageContext.request.contextPath}/user/join" method="get">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="btnIdCheck">중복체크</button>
								<p id="checkResult"></p>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이메일 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- //나이 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" checked="checked"> 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
	
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								
								<input type="checkbox" id="chk-agree" value="yes" name="agree">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							
							<!-- 가입 실패 시	-->
							<c:if test="${param.result eq 'fail'}">
								<p>회원 가입 실패</p>
							</c:if>
							
							
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
$("#btnIdCheck").on("click", function(){
	console.log("중복 체크 클릭");
	
	let id = $("#input-uid").val();
	//console.log(id);
	
	
	$.ajax({
		url : "${pageContext.request.contextPath }/user/idCheck",		
		type : "post",	//이거 get으로 해도 어차피 안 보임
		//보낼 때
		//contentType : "application/json",
		data : {id: id}, //파라미터

		//받을 때
		dataType : "json",
		success : function(jsonResult){
			/*성공시 처리해야될 코드 작성*/
			console.log(jsonResult);
			
			
			if(jsonResult.result == "success"){ //정상적인 통신 성공
				if(jsonResult.data == true){
					$("#checkResult").text("사용 가능");	
				}else if(jsonResult.data == false){
					$("#checkResult").text("사용 불가");
				}else{
					console.log("오류");
				}
			}else if(jsonResult.result == "fail"){ //정상적인 통신 X
				console.log("통1신 오류");
			}
			
			/*
			if(result == "true"){
				$("#checkResult").text("사용 가능");	
			}else if(result == "false"){
				$("#checkResult").text("사용 불가");
			}else{
				console.log("오류");
			}
			*/
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});


//회원가입 버튼 클릭 시 (submit 버튼은 form에 클릭 이벤트)
$("#formJoin").on("submit", function(event){
	console.log("회원가입 버튼 클릭");
	
	//id 입력 안 했을 떄
	let id = $("#input-uid").val();
	if(id == "" || id == null){
		alert("id를 입력하세여");
		return false;
	}
	
	//pw 검사
	let pw = $("#input-pass").val();
	if(pw == "" || pw == null){
		alert("pw를 입력하세여");
		return false;
		
	}else if(pw.length < 3){
		alert("pw는 3자 이상이어야 합니다.");
		return false;
	}
	
	//name 검사
	let name = $("#input-name").val();
	if(name == "" || name == null){
		alert("마 니는 이름도 읎나");
		return false;
	}
	
	//약관동의
	let agree = $("#chk-agree").is(":checked");
	if(agree == false){
		alert("약관에 동의해 개인정보를 팔아넘겨 주세요. 으하핳하ㅏㅎ핳하ㅏㅏ하핳ㅎ");
		return false;
	}
	
	
	//submit의 원래 전송을 하지 않아야 할 떄 return false;
	return true;
	
	//return false; == event.preventdefault(); 랑 비슷한 효과 (form 작동을 막아줌)
});


</script>


</html>




