<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.12.4.js"></script>

<title>Insert title here</title>

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
					<form action="insert" method="get">
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
								
								<!-- 등록 실패 시 -->
								<c:if test="${param.result eq 'fail'}">
									<tr>
										<td>
											<strong>등록 실패</strong>
										</td>
									</tr>
								</c:if>
								
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>
					
					<button id="btnGetData">데이터 가져오기</button>
					
					<div id="gbListArea">
					
					</div>
					
					<%-- 
					<c:forEach items="${requestScope.guestList}" var="GuestVo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${GuestVo.no}</td>
								<td>${GuestVo.name}</td>
								<td>${GuestVo.regDate}</td>
								<td><a href="delete?no=${GuestVo.no}">[삭제]</a></td>
								<c:if test="${param.result eq 'deletefail'}">
									<tr>
										<td>
											<strong>삭제 실패</strong>
										</td>
									</tr>
								</c:if>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${GuestVo.content}</td>
							</tr>
						</table>
					</c:forEach>	
					<!-- //guestRead -->
					 --%>
					 
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

//DOM이 완성되었을 때 --> 그리기 직전
$(document).ready(()=>{
	console.log("ready()");
	fetchList();
	console.log("ready() 요청 후");
});


//화면 그리고 난 후
$(window).load(()=>{
	console.log("load()");
//	fetchList();
	console.log("load()요청 후");
});


//버튼 클릭 시
$("#btnGetData").on("click", ()=>{
	console.log("무요");
	
	//fetchList();
});	

	
//ajax 통신을 이용해 데이터를 요청하고 + 그린다(render())
function fetchList(){
	
	console.log("fetchList() 실행");
	
	//서버로부터 guestbook 데이터만 받고 싶다
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guest/list",		
		type : "get",
		//보낼 때
		//contentType : "application/json",
		//data : {name: "귀여워서 사망"},

		//받을 때
		dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			//리스트 받기
			console.log(guestList);

			for(let i = 0; i<guestList.length; i++){
				render(guestList[i]); //그리는 함수
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
};
	

//방명록 내용을 1개씩 그린다.
function render(guestVo){
	
	let str = '';
	str += '<table class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>'+guestVo.no+'</td>';
	str += '		<td>'+guestVo.name+'</td>';
	str += '		<td>'+guestVo.regDate+'</td>';
	str += '		<td><a href="">[삭제]</a></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestVo.content+'</td>';
	str += '	</tr>';
	str += '</table>';
	
	$("#gbListArea").append(str);
};
	


</script>


</html>


