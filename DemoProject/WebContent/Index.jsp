<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>File Page</title>
<script type="text/javascript">
	function show() {
		var a = document.getElementById("select").value;
		if (a == "Customised") {
			document.getElementById("b").value = "one";
			document.getElementById("b").style.display = "block";
		} else {
			document.getElementById("b").style.display = "none";
		}
	}
</script>
</head>
<body>
	<form action="UploadFile" method="post" enctype="multipart/form-data">
		Select File :<input type="file" name="file"><br> <br>
		<input type="submit" value="submit"><br>
	</form>
	<br>
	<br>
	<form action="DownloadServlet" method="get">
		<div>
			<div>
				<label>Download Content</label>
			</div>
			<div>
				<select id="select" onchange="show()">
					<option>ALL</option>
					<option>Customised</option>
				</select>
			</div>
			<input type="submit" value="download sql">
		</div>
	</form>
	<form action="DateData" method="get">
			<div id="b" style="display: none;">
				<div>
					<input type="date" name="date1">
					<h5>To</h5>
					<input type="date" name="date2">
				</div>
			</div>
		<!-- 	<input type="submit" value="download sql"> -->
			</form>
	<form action="DownloadExcel" method="get">
	<input type="submit" value="Download excel">
	</form>
</body>
</html>