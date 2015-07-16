<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <h1>Add Person</h1>
            <form:form action="AddPerson" method="POST" name="forms" modelAttribute="personForm">
            <table align="center">

                <spring:bind path="names">
                    <tr><td><spring:message code="label.firstname"/></td><td> <form:input path="names.first_name" name="first_name"/></td></tr>
                    <tr><td><spring:message code="label.lastname"/> </td><td><form:input path="names.last_name" name="last_name"/></td></tr>
                </spring:bind>                
                
                <tr><td><spring:message code="label.address"/></td><td><form:input path="address" id="address" placeholder="address"/></td></tr>
                <tr><td><spring:message code="label.bday"/></td><td><form:input path="bday" name="bday" placeHolder="yyyy-MM-dd"/></td></tr>
                <tr><td><spring:message code="label.age"/> </td><td><form:input path="age" name="age"/></td></tr>
                <tr><td><spring:message code="label.contact"/> </td>
                    <td id="contactNumber"><select id="contact">
                            <option value="email" onclick="createField('E-mail')">E-mail</option>
                            <option value="cellphone" onclick="createField('Cellphone')">Cellphone#</option>
                            <option value="telephone"onclick="createField('Telephone')">Telephone#</option>
                        </select>
                    </td></tr>
                <spring:bind path="gender">
                <tr><td><spring:message code="label.gender"/> </td><td><form:radiobutton path="gender" name="gender" value="male"/>Male
                                <form:radiobutton path="gender" value="female"/>Female</td></tr>    
                </spring:bind>                
                <tr><td><spring:message code="label.grade"/> </td><td><form:input path="grade" name="grade" /></td></tr>
                <tr><td><spring:message code="label.datehired"/> </td><td><form:input path="date_hired" name="date_hired" placeHolder="yyyy-MM-dd"/></td></tr>
                <spring:bind path="currently_employed">                
                <tr><td><spring:message code="label.currentlyemployed"/> </td><td>
                                        <form:radiobutton path="currently_employed" value="yes"/>Yes 
                                        <form:radiobutton path="currently_employed" value="no"/>No
                </td></tr>
                </spring:bind>
                <spring:bind path="role">
                <tr><td><spring:message code="label.roles"/> </td><td><form:checkbox path="role" value="police"/>Police <br/>
                                        <form:checkbox path="role" value="soldier"/>Soldier <br/>
                                        <form:checkbox path="role" value="politician"/>Politician<br/>
                                        <form:checkbox path="role" value="celebrity"/>Celebrity <br/>
                                        <form:checkbox path="role" value="worker"/>Worker</td></tr>
                </spring:bind>
                <tr><td></td><td><input type="submit" value="Add Person">
                <input type="button" value="Cancel" onClick="location.href='Persons'"/>
            </table>
            </form:form>
            
        </div>
    </body>
</html>
