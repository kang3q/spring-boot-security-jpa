<%@ page pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<h2>Spring MVC file upload example</h2>
	<form action="${pageContext.request.contextPath}/ad/creative/fup" method="post" enctype="multipart/form-data">
		Please select a file to upload : <input type="file" name="file" /> <input type="submit" value="upload" />
	</form>
</body>
</html>
