<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/27 0027
  Time: 下午 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<object id="test" visible="true" classid="CLSID:B6FCC215-D303-11D1-BC6C-0000C078797F" type="application/x-oleobject"
        width="800" height="600">
    <param  name="SRC" value="http://localhost:8080/sys/attach/file/detail?fileId=<%=request.getParameter("id") %>&token=<%=request.getParameter("token") %>">
</object>
</body>
<script>
</script>
</html>
