<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
        <div class="grid span12">
        <div class="row">
			<#include "/views/menus.ftl"> 
			<div class="span9" style="margin-left:15px;">
				<table class="bordered">
                    <thead>
                        <tr>
                            <th style="text-align:center">编号</th>
                            <th style="text-align:center">姓名</th>
                            <th style="text-align:center">邮箱</th>
                            <th style="text-align:center">状态</th>
                            <th style="text-align:center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#if pageView.source?size gt 0>
	                    <#list pageView.source as admin>
						    <tr>
						    	<td>${admin_index+1}</td>
		                        <td class="center">${admin.userName}</td>
		                        <td class="center">${admin.email}</td>
		                        <td class="center">
		                        	<#if admin.status == 1>
										正常
									<#else>
										冻结
									</#if>
		                        </td>
		                        <td class="center">
		                        	<a href="/admin/update?id=${admin.id?string}"><i class="icon-wrench"></i></a>
		                        	&nbsp;
		                        	<a href="/admin/delete?id=${admin.id?string}"><i class="icon-cancel"></i></a>
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