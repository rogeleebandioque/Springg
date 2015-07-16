<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="hibernateactivity.web.Operations" %>

<%! 
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
    Operations op = (Operations) applicationContext.getBean("operations");
%>

<html>
    <head>
    <spring:url value="/resources/css/servlets.css" var="ServletsCss" />
	<spring:url value="/resources/js/Servlets.js" var="ServletsJs" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <spring:url value="/resources/js/jq.js" var="JqJs" />
    <link rel="stylesheet" type="text/css" href="${ServletsCss}"/>
    <script src="${ServletsJs}"></script>
    <script src="${JqJs}"></script>

    <title>Spring Activity</title>
    </head>

    <body>
        <div id="container">
            <span style="float: right">
                <a href="?lang=en">en</a>|
                <a href="?lang=tlg">tlg</a>
            </span>
            <h1 align="center">Spring Activity</h1>
            <form:form action="AddContact" method="POST" modelAttribute="personForm">
            Enter Contact Detail:<br/>
                <%--<input type="radio" name="contactType" value="e-mail">E-mail
                <input type="radio" name="contactType"value="cellphone">Cellphone#
                <input type="radio" name="contactType"value="telephone">Telephone#

                <br/>
                <input type="text" name="contactDetail" /> <br/>    
                --%>
                <select id="contact">
                            <option value="email" id="e-mail">E-mail</option>
                            <option value="cellphone" id="cellphone">Cellphone#</option>
                            <option value="telephone" id="telephone">Telephone#</option>
                </select>
                <div id="contactNumber"></div>
                <br/>
                <input type="submit" value="Add Contact"/> 
                <form:hidden path="id" value="${personForm.id}"/>
                <spring:bind path="names">
                    <form:hidden path="names.first_name"/>
                    <form:hidden path="names.last_name"/>
                </spring:bind>                
                               
                <form:hidden path="address"/>
                <form:hidden path="bday" />
                <form:hidden path="age"/>

                    <c:forEach var="contacts" items="${contact}">
                            <input type="hidden" name="contactDetail" size="10" value="${contacts.contact}"/>
                            <input type="hidden" name="contactType" value="${contacts.type}"/> <br/>
                    </c:forEach>
                        <div id="contactNumber">
                        </div>                        
                    </td></tr>
                    <form:hidden path="gender"/>
                <form:hidden path="grade"/>
                <form:hidden path="date_hired"/>
                    <form:hidden path="currently_employed"/> 
                <c:forEach var="rId" items="${roleId}">
                   
                    <c:forEach var="role" items="${roles}" varStatus="loop">                        
                        <c:if test="${role.roleName == rId}">
                          <input type="hidden" name="r" value="${rId}"/>
                        </c:if>
                    </c:forEach>
                </c:forEach>       
            </form:form>                
    
        </div>
    </body>
</html>
