<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="hibernateactivity.core.service.Service" %>
<%@ page import="hibernateactivity.core.model.Person" %>
<%@ page import="hibernateactivity.core.model.Contacts" %>
<%@ page import="hibernateactivity.core.model.Name" %>
<%@ page import="hibernateactivity.core.model.Roles" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="hibernateactivity.web.Operations" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>

<%! 
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
    Operations op = (Operations) applicationContext.getBean("operations");
%>
<html>
    <head>
    <spring:url value="/resources/css/servlets.css" var="ServletsCss" />
	<spring:url value="/resources/js/Servlets.js" var="ServletsJs" />
    <link rel="stylesheet" type="text/css" href="${ServletsCss}"/>
    <script src="${ServletsJs}"></script>

    <title>Spring Activity</title>
    </head>

    <body>
        <div id="container">
            <span style="float: right">
                <a href="?lang=en">en</a>|
                <a href="?lang=tlg">tlg</a>
            </span>
            <h1 align="center">Spring Activity</h1>
            <div id="search">
                <div id="add"><button onClick="location.href='AddPerson'">Add Person</button></div><br/>                
                 Search By: <select>
                                <option value="person" onclick="listTo('person')"> Person </option>
                                <option value="role" onclick="listTo('role')"/> Role </option>
                                </select>                                   
                    <div id="displist">
                    </div>
            </div>
            
            <table border="1"align="center">
                 <thead><tr><th colspan="6">LIST of PERSONS</th></tr>
               <tr><th>ID</th>
                    <th><spring:message code="label.name"/></th>
                    <th><spring:message code="label.datehired"/></th>
                    <th><spring:message code="label.grade"/></th>
                    <th><spring:message code="label.contact"/></th>
                    <th><spring:message code="label.action"/></th></tr>
                </tr></thead>
                <c:forEach var="user" items="${person}">
                    <tr>
                        <td>
	                        ${user.id}
                        </td>
                        <td>${user.names.first_name} ${user.names.last_name}</td>
                        <td>${user.date_hired}</td>
                        <td>${user.grade}</td>
                        <td>
                            <c:forEach var="cont" items="${user.contact}" varStatus="loop">
	                        ${cont.type} : ${cont.contact}
            				    <c:if test="${not loop.last}"><br/></c:if>
                          </c:forEach>
                        </td>
                        <td>
                            <spring:url value="/person/${user.id}/delete" var="deleteUrl" /> 
                            <spring:url value="/person/${user.id}/update" var="updateUrl" />

                            <button onclick="location.href='${updateUrl}'">Update</button>
                            <button onclick="location.href='${deleteUrl}'">Delete</button>
                        </td>
                    </tr>
			    </c:forEach>               
            </table>

        </div>
    </body>
</html>
