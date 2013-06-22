<div class="span9 offset1">
	<div class="pagination">
		<ul>
		  	<#if pageView.currentPage gt 1 >
		  	<#--
		  	<li class="first"><a href="${pageView.hrefPrefix}1${pageView.hrefPostfix}" class="first"></a></li>
		  	-->
		  	<li class="previous"><a href="${pageView.hrefPrefix}${(pageView.currentPage-1)?c}${pageView.hrefPostfix}" title="上一页"><img src="/flatui/images/pager/previous.png"></a></li>
		  	</#if>
	
		  	<#if pageView.pageIndex.startIndex gt 1 >
		  	<li class="spaces"><a>...</a></li>
		  	</#if>
		  	
		  	<#if pageView.totalPage!=0>
			<#list pageView.pageIndex.startIndex..pageView.pageIndex.endIndex as index>  
				<#if pageView.currentPage == index>
					<li class="active"><a>${index?c}</a></li>
				<#else>
					<li><a href="${pageView.hrefPrefix}${index?c}${pageView.hrefPostfix}">${index?c}</a></li>
				</#if>
			</#list>
		  	</#if>
	
			<#if pageView.totalPage gt pageView.pageIndex.endIndex >
		  	<li class="spaces"><a>...</a></li>
		  	</#if>	
		  	
			<#if pageView.currentPage lt pageView.totalPage>
			<li class="next"><a href="${pageView.hrefPrefix}${(pageView.currentPage+1)?c}${pageView.hrefPostfix}" title="下一页"><img src="/flatui/images/pager/next.png"></a></li>
			<#--
			<li class="last"><a href="${pageView.hrefPrefix}${pageView.totalPage?c}${pageView.hrefPostfix}"></a></li>
			-->
			</#if>		  
		</ul>
	</div>
</div>