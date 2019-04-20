<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	function check() {
		var oldText = $('#myText').val();
		$.ajax({
			type : "POST",
			url : "./textServlet",
			data : {
				oldText : oldText
			},
			success : function(result) {
				var Ca = /\+/g;
				var newText = decodeURIComponent(result.replace(Ca," "));
				
				$('#myText').val(oldText + ' : ' + newText)
			}
		});
	}
</script>
</head>
<body>
	<p>All about wschoi<p>	
	<textarea id="myText" rows="3" cols="20">
    </textarea>
	<br>
	<button onclick="check();">전송</button>
</body>
</html>