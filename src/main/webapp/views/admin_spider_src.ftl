<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
		        <div class="grid span12">
				        <div class="row">
											<#include "/views/menus.ftl"> 
											<div class="span5" style="margin-left:15px;">
														 <h2>新增采集站点</h2>
														 <form name="addForm" id="addForm" action="/spiderUed/addSpider" method="post">  
						                <div class="input-control text">
						                    <input name="src" id="src" type="text" placeholder="Enter src">
						                </div>
						                <div class="input-control text">
						                    <input name="url" id="url" type="text" placeholder="Enter url">
						                </div>
						                <input type="button" value="Submit" name="btnSubmit" id="btnSubmit" class="bg-color-blue"/>
																	<input type="reset" value="Reset"/>
															</form>
				            </div>
				       	</div>
		       	</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl">