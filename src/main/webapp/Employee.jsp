<div style=" text-align:center; width:30%; height:100pt; padding:20pt; border:dotted; background:yellow; margin:50pt">



<h1>Employee Dashboard</h1>

<jsp:useBean id="login" class="com.coforge.model.Login" scope="session"/>

 <div>user id :<jsp:getProperty property="id"

 name ="login"/></div>

 <div>User Name :<jsp:getProperty property="username"

 name ="login"/></div>


</div>