<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
        <div class="grid span12">
        <div class="row">
			<#include "/views/menus.ftl"> 
			<div class="span5" style="margin-left:15px;">
				<h2>新增后台用户</h2>
                <div class="input-control text">
                    <input name="userName" id="userName" type="text" placeholder="Enter user name">
                </div>
                <div class="input-control password">
                    <input name="pwd" id="pwd" type="password" placeholder="Enter password">
                </div>
                <div class="input-control text">
                    <input name="email" id="email" type="text" placeholder="Enter user email">
                </div>
                <input type="button" value="Submit" name="btnSubmit" id="btnSubmit" class="bg-color-blue"/>
				<input type="reset" value="Reset"/>
            </div>
       	</div>
       	</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl"> 
<script type="text/javascript">
$(function() {
	$("#btnSubmit").click(function(){
		ajaxSubmit();
	});

	//ajax提交
	var ajaxSubmit=function()
	{
		var param = new Object();
		param.userName = $("#userName").val();
		param.pwd = $("#pwd").val();
		param.email = $("#email").val();
			
		if($.trim($("#userName").val()) != ''
			&& $.trim($("#pwd").val()) != ''
			&& $.trim($("#email").val()) != '')
		{
			$.ajax({
			    url: "/admin/add",
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
