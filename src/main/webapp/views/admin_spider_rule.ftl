<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
	        <div class="grid span12">
		        <div class="row">
                    <#include "/views/menus.ftl"> 
                    <div class="span5" style="margin-left:15px;">
                        <h2>新增采集站点和规则</h2>
                        <form name="addForm" id="addForm" action="/spiderUed/addRule" method="post">  
			                <div class="input-control text">
			                    <input name="src" id="src" type="text" required="required"  placeholder="请填写采集站点模块名称">
			                </div>
			                <div class="input-control text">
			                    <input name="url" id="url" type="url" required="required"  placeholder="请填写采集站点列表URL">
			                </div>
			                <div class="row">
    			                <div class="span2">
                                    <div class="input-control as-block text">
                                        <input name="pageFrom" id="pageFrom" type="number" placeholder="起始位置"/>
                                    </div>
                                </div>  
                                <div class="span2">
                                    <div class="input-control as-block text">
                                        <input name="pageTo" id="pageTo" type="number" placeholder="结束位置"/>
                                    </div>  
                                </div>  
                            </div> 
                            <div class="input-control textarea">
                                <textarea name="tags" id="tags" placeholder="请填写相关标签"></textarea>
                            </div>
                            <div class="input-control textarea">
                                <textarea name="filters" id="filters" placeholder="请填写过滤规则"></textarea>
                            </div>	
                            <div class="input-control textarea">
                                <textarea name="rules" id="rules" placeholder="请填写提取规则"></textarea>
                            </div>		                
			                <input type="submit" value="Submit" class="bg-color-blue"/>
							<input type="reset" value="Reset"/>
				        </form>
                    </div>
		       	</div>
	       	</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl">