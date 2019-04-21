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
	var trialNum = 0;

	function check() {
		$.ajax({
			type : "POST",
			url : "./JsonBuildAction",
			dataType : "json",
			success : function(data) {
				trialNum = trialNum + 1;
		        $('#myText').val(trialNum + '번째 시도' + "\n\n");
				getJson(data);
			}
		});
	}

	function getJson(str) {
			if (isArr(str) == true) {
				for(var con in str){
					getJson(str[con]);
				}
			} else if (isArr(str) == false && isJson(str) == true) {
				var don = decodeURIComponent(Object.keys(str));
				for(var con in str){
					addData("- " + decodeURIComponent(con) + " ");
					getJson(str[con]);
				}
			} else if (isArr(str) == false && isJson(str) == false) {
				addData(decodeURIComponent(": " + JSON.stringify(str)) + "\n\n");
			}
	}
	function isJson(data) {
		var oldData = data;
		var newData = JSON.stringify(data);
		if (Object.keys(oldData).length == (Object.keys(newData).length - 2)) {
			return false;
		} else {
			return true;
		}
	}

	function isArr(data) {
		if (Array.isArray(data)) {
			return true;
		} else {
			return false;
		}
	}
	
	function addData(data) {
		var oldText = $('#myText').val();
        var newText = oldText + data;
        $('#myText').val(newText);
	}
</script>
</head>
<body>
	<p>All about wschoi</p>
	<textarea id="myText" rows="10" cols="60"></textarea>
	<br>
	<button onclick="check();">불러오기</button>
</body>
</html>