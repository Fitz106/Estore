<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="screen" />
</head>

<body>
	<br/>
	<div align="center">
		<font size="5">订单信息</font>
	</div>
	<br/>
	<table align="center" border="1">
		<tr>
			<td>订单编号</td>
			<td>订单信息</td>
			<td>订单状态</td>
			<td>订单用户</td>
			<td>订单操作</td>
		</tr>



			<tr>
				<td>${orders.id }</td>
				<td>
					<table align="center" border="1">
						<tr>
							<td>商品名称</td>
							<td>商品单价</td>
							<td>购买数量</td>
						</tr>

						<c:forEach items="${orderitem}" var="item">
							<tr>
								<td>${item.name }</td>
								<td>${item.price }</td>
								<td>${item.buynum }</td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="3" align="right">总价:${orders.money}</td>
						</tr>

					</table>
			</td>
			<td><c:if test="${orders.paystate==0}">
					<a
						href='${pageContext.request.contextPath}/pay?method=paypage&id=${orders.id}&money=${orders.money}'>未支付</a>
				</c:if> <c:if test="${order.paystate==1}">
						已支付
					</c:if></td>
			<td>${user.username}</td>

			<td><a
				href="${pageContext.request.contextPath}/order?method=del&id=${orders.id}">取消订单</a>
			</td>
			</tr>


	</table>

</body>
</html>
