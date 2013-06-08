<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
        <div class="grid span12">
        <div class="row">
			<#include "/views/menus.ftl"> 
			<div class="span7" style="margin-left:15px;">
					<h2>修改用户信息</h2>
					<div class="row">
						<div class="span1">
							<span style="font-size:15px;font-weight:bold;">openId: </span>
						</div>
				        <div class="input-control text span6">
	                        <input name="openId" id="openId" type="text" disabled="" value="${user.openId}">
	                    </div>
                    </div>
                    <div class="row">
						<div class="span1">
							<span  style="font-size:15px;font-weight:bold;">openKey: </span>
						</div>
	                    <div class="input-control text span6">
	                        <input name="openKey" id="openKey" type="text" disabled="" value="${user.openKey}">
	                    </div>
	                </div>
	                <div class="row">
						<div class="span1">
							<span  style="font-size:15px;font-weight:bold;">名称: </span>
						</div>
	                    <div class="input-control text span6">
	                        <input name="userName" id="userName" type="text" value="${user.userName}">
	                    </div>
	                </div>
	                <div class="row">
						<div class="span1">
							<span  style="font-size:15px;font-weight:bold;">邮箱: </span>
						</div>
	                    <div class="input-control text span6">
	                        <input name="email" id="email" type="text" value="${user.email}">
	                    </div>
	                </div>
	                <div class="row">
						<div class="span1">
							<span  style="font-size:15px;font-weight:bold;">点数: </span>
						</div>
	                    <div class="input-control text span6">
	                        <input name="points" id="points" type="text" value="${user.points}">
	                    </div>
	                </div>    
	                <div class="row" style="margin-bottom:15px; margin-top:15px;">
						<div class="span1">
							<span  style="font-size:15px;font-weight:bold;">状态: </span>
						</div>
	                    <div class="span6">
	                    	<span class="">冻结的</span>
		                    <#if user.status == -1>
								<input type="radio" checked="" name="status" value="-1" >
							<#else>
								<input type="radio" name="status" value="-1">
							</#if>
							&nbsp;&nbsp;
							<span class="">试用的</span>
							<#if user.status == 1>
								<input type="radio" checked="" name="status" value="1">
							<#else>
								<input type="radio" name="status" value="1">
							</#if>
							&nbsp;&nbsp;
							<span class="">签约的</span>
		                    <#if user.status == 9>
								<input type="radio" checked="" name="status" value="9">
							<#else>
								<input type="radio" name="status" value="9">
							</#if>
						</div>
					</div>	
                    <input type="button" value="Submit" name="btnSubmit" id="btnSubmit" class="bg-color-blue"/>
            </div>
       	</div>
       	</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl"> 
<input name="id" id="id" type="hidden" value="${user.id}">
<script type="text/javascript">
$(function() {
	$("#btnSubmit").click(function(){
		ajaxSubmit();
	});

	//ajax提交
	var ajaxSubmit=function()
	{
		var status = $('input:radio[name="status"]:checked').val();
		var param = new Object();
		param.id = $("#id").val();
		param.userName = $("#userName").val();
		param.points = $("#points").val();
		param.email = $("#email").val();
		param.openKey = $("#openKey").val();
		param.openId = $("#openId").val();	
		param.status = status;
			
		if($.trim($("#userName").val()) != ''
			&& $.trim($("#points").val()) != ''
			&& $.trim($("#email").val()) != '' && status != '')
		{
			$.ajax({
			    url: "/user/update",
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
