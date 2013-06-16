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
                            <th style="text-align:center">采集站点名称</th>
                            <th style="text-align:center">采集站点URL</th>
                            <th style="text-align:center">状态</th>
                            <th style="text-align:center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#if pageView.source?size gt 0>
	                    <#list pageView.source as rule>
						    <tr>
						    	<td>${rule_index+1}</td>
		                        <td class="center">${rule.src}</td>
		                        <td class="center">${rule.url}</td>
		                        <td class="center">
		                        	<#if rule.status == 0>
										正常
									<#else>
										已删除
									</#if>
		                        </td>
		                        <td class="center">
		                        	<a href="/spiderUed/update?id=${rule.id?string}"><i class="icon-wrench"></i></a>
		                        	&nbsp;
		                        	<a href="/spiderUed/delete?id=${rule.id?string}"><i class="icon-cancel"></i></a>
		                        	&nbsp;
		                        	<#if rule.status == 0>
                                    <a href="javascript:void(0);" class="settingOpt" id="${rule.id?string}"><i class="icon-cog"></i></a>
                                    </#if>
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
<script type="text/javascript" src="/js/apihome/admin_spider_list.js"></script>