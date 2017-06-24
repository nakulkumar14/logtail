<html>
<head>
	<title>Log Tailer app</title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<body>
	<h1>Log Tailer App</h1>
	<button id="file1" onclick="getLogFileContent(this)">File1</button>
	<button id="file2" onclick="getLogFileContent(this)">File2</button>
	<button id="file3" onclick="getLogFileContent(this)">File3</button>
	<hr>

	<div id="feedback"></div>
</body>
<script>
	function getLogFileContent(ele){
		console.log("pressed element : "+ele.id)
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/getLogFileContent/"+ele.id,
			dataType : 'text',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data,ele,"success");

				console.log("Getting logs again.")
				setTimeout(function() {
					getLogFileContent(ele);
				}, 1000);
			},
			error : function(data) {
				console.log("ERROR: ", data);
				display(data,ele,"error");
			}
		});
	}

	function display(data,file,msg) {
		console.log("Rendering response for file :"+JSON.stringify(data)+" msg : "+msg);
		if(msg) {
			if("success" == msg) {
				var json = "<p>Response for log file : " + JSON.stringify(data).replace(/\n/g,"<br>") + "</p>";
				$('#feedback').html(json);
			}else{
				var error = "<h4 style='color: red;'>Some error occurred!</h4>";
				$('#feedback').html(error);
			}
		}
	}
</script>
</html>