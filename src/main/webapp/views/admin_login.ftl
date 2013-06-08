<#include "/views/header.ftl"> 
<div class="page" id="page-index">
    <div class="page-region">
        <div class="page-region-content">
        	<div class="span5">
        		<h2>登录窗口</h2>
				<form method="post" action="/login" class="loginForm">
				    <div class="input-control text">
					    <input type="text" name="userName" id="userName" placeholder="Enter user name"/>
				    </div>
					<div class="input-control password">
					    <input type="password" name="pwd" id="pwd" placeholder="Enter password"/>
				    </div>
				    <input type="submit" value="Submit"/>
					<input type="reset" value="Reset"/>
				</form>
			</div>
        </div>
    </div>
</div>
<#include "/views/footer.ftl"> 