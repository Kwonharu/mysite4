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
								
								<!-- 등록 실패 시 -->
								<c:if test="${param.result eq 'fail'}">
									<tr>
										<td>
											<strong>등록 실패</strong>
										</td>
									</tr>
								</c:if>
								
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>
					
					<!-- <button id="btnGetData">데이터 가져오기</button> -->
					
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


//등록 버튼을 클릭했을 떼
//form의 submit 버튼의 경우, form id에 submit 이벤트를 지정해줘야 한다.
$("#guestbookForm").on("submit", (e)=>{
	console.log("등록 버튼 클릭");
	e.preventDefault(); //원래 기능을 작동하지 않게 함
	
	//데이터 수집
	let name = $("#input-uname").val();
	let pw = $("#input-pass").val();
	let content = $('[name="content"]').val(); 
	
	let guestVo = {
		name: name,
		password: pw,
		content: content
	};
	
	console.log(guestVo);
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guest/add",		
		type : "post",
		/*contentType : "application/json",*/
		data : guestVo,

		dataType : "json",
		success : function(guestVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestVo);
			
			//그리기
			render(guestVo, "down");
			
			//초기화
			name = $("#input-uname").val("");
			pw = $("#input-pass").val("");
			content = $('[name="content"]').val(""); 
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

	
});


//삭제 버튼 눌렀을 때 (위임)
$("#gbListArea").on("click", ".btnDelForm", function(){	//this 사용 시 화살표 함수 쓰지 말 것
	console.log("삭제 버튼 클릭");
	
	let password = window.prompt("비밀번호를 썩션♂하세요");
	
	if(password == null){ //취소일때
		return false;
	
	}else if(password == ""){ //확인 비빔번호 없을때
		alert("비밀번호를 입력하세요ㅕ");
		
	}else { //정상일때

		console.log(password);
		
		//password, no
		let $this = $(this);
		let no = parseInt($this.data("no"));
		console.log(no);
		
		//ajax 요청 db 삭제
		let guestVo = {
			no: no,
			password: password
		};
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guest/delete",
			type : "post",	//이거 get으로 해도 어차피 안 보임
			//보낼 때
			contentType : "application/json",
			data : JSON.stringify(guestVo),

			//받을 때
			dataType : "json",
			success : function(jsonResultVo){
				/*성공시 처리해야될 코드 작성*/

				//화면에서 지운다 - jsonResultVo.data가 true일 때
				if(jsonResultVo.data == true){
					alert("삭제 성공");
					$("#t"+no).remove();
				}else if(jsonResultVo.data == false){
					alert("패스워드 불일치");
				}else{
					alert("오류");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	}	
	
});
	
	
//ajax 통신을 이용해 데이터를 요청하고 + 그린다(render())
function fetchList(){
	
	console.log("fetchList() 실행");
	
	//서버로부터 guestbook 데이터만 받고 싶다
	$.ajax({
		url : "${pageContext.request.contextPath }/api/guest/list",		
		type : "get",	//이거 get으로 해도 어차피 안 보임
		//보낼 때
		//contentType : "application/json",
		//data : {name: "아린이 왔다감"},

		//받을 때
		dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			//리스트 받기
			console.log(guestList);

			for(let i = 0; i<guestList.length; i++){
				render(guestList[i], "up"); //그리는 함수
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
};
	

//방명록 내용을 1개씩 그린다.
function render(guestVo, dir){
	
	let str = '';
	str += '<table id=t'+guestVo.no+' class="guestRead">';
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
	str += '		<td><button class="btnDelForm" data-no='+ guestVo.no +'>삭제</button></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">'+guestVo.content+'</td>';
	str += '	</tr>';
	str += '</table>';
	
	if(dir == "up"){
		$("#gbListArea").append(str);
	}else if(dir == "down"){
		$("#gbListArea").prepend(str);
	}else{
		console.log("무요!!!");
	}
	
};
	


</script>


</html>





