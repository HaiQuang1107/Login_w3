<%-- 
    Document   : search
    Created on : 13-Apr-2021, 09:46:40
    Author     : TienVM_PC
--%>

<%@page import="java.util.List"%>
<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER"); %>
        
        <h1>WellCome: <%=loginUser.getFullName() %></h1>
        <form action="MainController">
            Search: <input type="text" name="search" value="" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
        <% List<UserDTO> list = (List<UserDTO>)request.getAttribute("LIST_USER");
        if(list != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>RoleID</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (UserDTO user : list) {
                %>
                <tr>
                    <td><%= count ++%></td>
                    <td><%= user.getUserID()%></td>
                    <td><%= user.getFullName()%></td>
                    <td><%= user.getRoleID()%></td>
                    <td><%= user.getPassword()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
            }
        %>
    </body>
</html>
