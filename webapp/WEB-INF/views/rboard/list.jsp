<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/board/list">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/rboard/list">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="list" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>group_no</th>
									<th>order_no</th>
									<th>depth</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.rBoardList}" var="rBoardVo">
									<tr>
										<td>${rBoardVo.no}</td>
										<td class="text-left"><a href="${pageContext.request.contextPath}/board/read?no=${rBoardVo.no}">${rBoardVo.title}</a></td>
										<td>${rBoardVo.groupNo}</td>
										<td>${rBoardVo.orderNo}</td>
										<td>${rBoardVo.depth}</td>
										<c:if test="${sessionScope.authUser.no eq rBoardVo.userNo}">
											<td>
												<a href="delete?no=${rBoardVo.no}">[삭제]</a>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<br>
						
						<!-- 삭제 실패 시 -->
						<c:if test="${param.result eq 'fail'}">
							<td>
								<strong>삭제 실패</strong>
							</td>
						</c:if>
						
						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						
						<c:if test="${!empty sessionScope.authUser}">
							<a id="btn_write" href="writeForm">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>


