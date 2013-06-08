<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理程序</title>
</head>
<body>
	<div>
		文件夹目录：<input id="filePath" name="filePath" type="text"  value=""><br/>
		虚拟路径：<input id="virtualPath" name="virtualPath" type="text"  value=""><br/>
		文件代号：<input id="fileCode" name="fileCode" type="text"  value=""><br/>
		<input type="button" value="搜索" id="coolsubmit">
	</div>	
</body>
</html>
<script type="text/javascript" src="/js/assets/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    $(function() {
		$("#coolsubmit").click(function(){
			ajaxSubmit();
		});

		/**
		 * ajax查询
		 */
		var ajaxSubmit=function()
		{
			var param = new Object();
			param.filePath = $("#filePath").val();
			param.virtualPath = $("#virtualPath").val();
			param.fileCode = $("#fileCode").val();
				
			if($.trim($("#filePath").val()) != ''
				&& $.trim($("#virtualPath").val()) != ''
				&& $.trim($("#fileCode").val()) != '')
			{
				$.ajax({
				    url: "/admin/scan",
				    type: 'GET',
				    dataType: 'text',
				    data:param,
				    async:false,
				    contentType:"application/x-www-form-urlencoded; charset=utf-8",
				    success: function(data)
				    {
				    	alert(data);
				    }
				});
			}
			else
			{
				alert('Please write keyWords!');
			}
		}
    });
</script>