<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">

<!-- js -->
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js" type="text/javascript"></script>

</head>

<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //해더 네비 -->

		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<div id="aside">
				<h2>갤러리</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath }/fileupload/form">첨부파일연습</a></li>
					<li><a href="${pageContext.request.contextPath }/gallery/list">갤러리</a></li>
				</ul>
			</div>
			<!-- //aside -->
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">
				
						<c:if test="${sessionScope.authUser != null}">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>
							<div class="clear"></div>
	
				
						<ul id="viewArea">
							
							<!-- 이미지반복영역 -->
							<c:forEach items="${requestScope.galleryList}" var="galleryVo">
								<li id="galleryList${galleryVo.no}">
									<div class="view" >
										<img data-no="${galleryVo.no}" class="imgItem" src="${pageContext.request.contextPath}/upload/${galleryVo.saveName}">
										<div class="imgWriter">작성자: <strong>${galleryVo.name}</strong></div>
									</div>
								</li>
							</c:forEach>
							<!-- 이미지반복영역 -->
							
							
						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->


<!-- /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// -->
<!-- 이미지등록 팝업(모달)창 -->
<div class="modal fade" id="addModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지등록</h4>
			</div>
			
			<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
				<div class="modal-body">
					<div class="form-group">
						<label class="form-text">글작성</label>
						<input id="addModalContent" type="text" name="content" value="" >
					</div>
					<div class="form-group">
						<label class="form-text">이미지선택</label>
						<input id="file" type="file" name="file" value="" >
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn" id="btnUpload">등록</button>
				</div>
			</form>
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- 이미지보기 팝업(모달)창 -->
<div class="modal fade" id="viewModal">
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">이미지보기</h4>
			</div>
			<div class="modal-body">
				
				<div class="formgroup" id="viewModalBox">
					<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				
				<div class="formgroup">
					<p id="viewModelContent"></p>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->	
<!-- /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// -->




</body>

<script type="text/javascript">

//이미지 업로드 모달창 버튼 클릭 시
$("#btnImgUpload").on("click", ()=>{
	console.log("로그인은 했겠지?");
	
	//열기
	$("#addModal").modal("show");
	
	//닫기
	//$("#addModal").modal("hide");
	
});


//이미지 클릭 시 정보 및 삭제 버튼 출력
$(".imgItem").on("click", function(){
	console.log("this가 필요할 것 같은 예감");
	
	//열기
	$("#viewModal").modal("show");
	
	let $this = $(this);
	
	let no = $this.data("no"); //게시글의 no
	console.log(no);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/post",
		type : "post",
		//보낼 때
		/* contentType : "application/json", */  //no 하나 보낼 거라 굳이?
		data : {no: no},

		//받을 때
		dataType : "json",
		success : function(galleryVo){
		
			$("#viewModal #viewModelImg").attr("src", "${pageContext.request.contextPath}/upload/"+galleryVo.saveName);
			console.log(galleryVo.filePath);
			$("#viewModal #viewModelContent").text(galleryVo.content);
			
			if("${sessionScope.authUser.no}" != galleryVo.userNo){
				$("#btnDel").hide();
			}else{
				$("#btnDel").show();
				$("#btnDel").data("no", galleryVo.no);
			}
				
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});


//삭제 버튼 눌렀을 때
$("#btnDel").on("click", function(){
	console.log("삭제해라 애송이");
	
	//해당 게시글 no
	let no = $(this).data("no");
	console.log(no);
	
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/delete",
		type : "post",	//이거 get으로 해도 어차피 안 보임
		//보낼 때
		/* contentType : "application/json", */
		data : {no: no},

		//받을 때
		dataType : "json",
		success : function(count){
			console.log(count);
			
			if(count != -1){
				alert("삭제 성공");
				$("#galleryList"+no).remove();
				$("#viewModal").modal("hide");
			}else{
				alert("오류");
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
		
	
});




</script>


</html>





