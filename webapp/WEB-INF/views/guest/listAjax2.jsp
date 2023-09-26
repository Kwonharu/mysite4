<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/guest/addList">일반방명록</a></li>
					<li><a href="${pageContext.request.contextPath}/api/guest/addList">ajax방명록</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestbookForm" action="" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">json 등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						
						<!-- <input type="hidden" name="action" value="add"> -->
						
					</form>
					
					<button id="btnDataSend" type="button">복잡한 데이터 전송</button>
					
					<div id="gbListArea">
					
					</div>
					
				</div>
				<!-- //guestbook -->
			
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

//json 형식으로 데이터 전송
$("#guestbookForm").on("submit", function(event){
	event.preventDefault();	//기존의 폼 형식으로 작동하면 안 된다.(파라미터로 값을 넘기면 안 된다.)
	console.log("썩션-");
	
	let name = $("#input-uname").val();
	let pw = $("#input-pass").val();
	let content = $("[name=content]").val();
	
	let guestVo = {
		name: name,
		password: pw,
		content: content
	}
	console.log(guestVo);
	
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guest/add2",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(guestVo),	
			  //js 객체 --> json으로 바꿔주는 함수

		dataType : "json",
		success : function(jsonResultVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(jsonResultVo);
			
			//success 값 출력
			console.log(jsonResultVo.result);
			
			//이름만 출력
			console.log(jsonResultVo.data.name);
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});

//복잡한 데이터 전송
$("#btnDataSend").on("click", ()=>{
	console.log("복잡한 데이터 전송 썩션-");
	
	let guestList = [];
	
	let guestVo1 = {
		name: "수아",
		password: "0314",
		content: "어여쁜 여우."
	};
	
	let guestVo2 = {
		name: "유화",
		password: "0925",
		content: "귀여운 여우."
	};
	
	let guestVo3 = {
		name: "연화",
		password: "0925",
		content: "아름다운 여우."
	};

	guestList.push(guestVo1);
	guestList.push(guestVo2);
	guestList.push(guestVo3);
	
	console.log(guestList);
	
	
	//서버로부터 guestbook 데이터만 받고 싶다
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guest/add3",		
		type : "post",
		//보낼 때
		contentType : "application/json",
		data : JSON.stringify(guestList), //js 객체를 json으로 파싱
		
		//받을 때
		dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

	
});



</script>


</html>





