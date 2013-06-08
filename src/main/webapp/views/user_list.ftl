<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
        <div class="grid span12">
        <div class="row">
			<#include "/views/menus.ftl"> 
			<div class="span9" style="margin-left:15px;">
				<h2>用户列表</h2>
				<table class="bordered">
                    <thead>
                        <tr>
                            <th style="text-align:center">编号</th>
                            <th style="text-align:center">姓名</th>
                            <th style="text-align:center">邮箱</th>
                            <th style="text-align:center">openId/openKey</th>
                            <th style="text-align:center">状态</th>
                            <th style="text-align:center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#if pageView.source?size gt 0>
	                    <#list pageView.source as user>
						    <tr>
						    	<td>${user_index+1}</td>
		                        <td class="center">${user.userName}</td>
		                        <td class="center">${user.email}</td>
		                     	<td class="center">${user.openId}<br/>${user.openKey}</td>
		                        <td class="center">
			                        <#if user.status == 1>
										试用帐号
									<#elseif user.status == 9>
										签约帐号
									<#else>
										冻结帐号
									</#if>
		                        </td>
		                        <td class="center">
		                        	<a href="/user/modify?id=${user.id?string}"><i class="icon-wrench"></i></a>
		                        	&nbsp;
		                        	<a href="javascript:void(0);" onclick="deleteUser(${user.id?string})"><i class="icon-cancel"></i></a>
		                        </td>
	                        </tr>
						</#list>
                    </#if>
                    </tbody>
                    <tfoot></tfoot>
                </table>
                <#-- 分页组件 -->
                <#include "/views/pagination.ftl">  
            </div>
       	</div>
       	</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl"> 
<script type="text/javascript">
$(function() {
	//ajax提交
	var deleteUser=function(id)
	{
		$.ajax({
		    url: "/user/delete?id="+id,
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
});
</script>
