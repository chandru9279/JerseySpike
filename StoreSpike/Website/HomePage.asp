<%@  language="VBSCRIPT" %>
<%
    Option Explicit
%>

<script language="JScript" runat="server" src="json2.js"></script>

<%
    Dim req

    Set req = Server.CreateObject("MSXML2.ServerXMLHTTP")
    req.open "GET", "http://ip.jsontest.com", False
    req.send

    dim myJSON
    set myJSON = JSON.parse(req.responseText)

%> 

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
    IP is : <%response.write(myJSON.ip)%>
</body>
</html>

<%
    Set req = Nothing
%>
