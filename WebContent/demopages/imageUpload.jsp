<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Image Upload</title>
	</head>

	<body>
		<form action="../addImage.action" method="post" enctype="multipart/form-data">
			<input type="file" name="image" multiple="multiple"/><br />
			<input type="submit" value="upload" />
		</form>
	</body>

</html>