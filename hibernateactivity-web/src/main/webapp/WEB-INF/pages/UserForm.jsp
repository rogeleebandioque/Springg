<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

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

            <c:choose>
                <c:when test="${personForm.id == 0}">
                <h1>Add Person</h1>
                <div id="fileUpload">                
                <form method="post" action="/UploadForm" enctype="multipart/form-data">
                    Upload File: <input type="file" name="file"/>
                    <input type="submit" value="Add File"/>
                </form>
                </div>
                <br />
                or
                </c:when>
                <c:otherwise>
                <h1>Update Person</h1>
                </c:otherwise>
            </c:choose>
            
            <br/>
            <form:form action="SaveUpdate" method="POST" nid="formtry" modelAttribute="personForm">
                <table align="center">
                <form:hidden path="id" value="${userForm.id}"/>
                
                <spring:bind path="names">
                    <tr><td><spring:message code="label.firstname"/></td><td> <form:input path="names.first_name" required="true"/></td></tr>
                    <tr><td><spring:message code="label.lastname"/> </td><td><form:input path="names.last_name"required="true"/></td></tr>
                </spring:bind>                

                    <tr><td><spring:message code="label.address"/></td><td><form:input path="address" id="address" placeholder="address"required="true"/></td></tr>
                    <tr><td><spring:message code="label.bday"/></td><td><form:input path="bday" name="bday" placeHolder="yyyy-MM-dd"required="true"/></td></tr>
                    <tr><td><spring:message code="label.age"/> </td><td><form:input path="age" name="age"required="true"/></td></tr>
                    <tr><td><spring:message code="label.contact"/> </td>
                        <td>

                    <select id="contact">
                        <option value="email" id="e-mail">E-mail</option>
                        <option value="cellphone" id="cellphone">Cellphone#</option>
                        <option value="telephone" id="telephone">Telephone#</option>
                    </select>

                        <div id="contactNumber">                
                            <c:forEach var="contacts" items="${contact}">
                                <div> 
                                ${contacts.type}: <input type="text" name="contactDetail" size="10" value="${contacts.contact}"required="true"/>
                                <input type="button" value="Remove" class="remove_field" />  
                                <input type="hidden" name="contactType" value="${contacts.type}"/> <br/>
                                </div>
                            </c:forEach>
                        </div>                        
                    </td></tr>
                    
                    <spring:bind path="gender">
                        <tr><td><spring:message code="label.gender"/> </td><td><form:radiobutton path="gender" name="gender" value="male"required="true"/>Male
                        <form:radiobutton path="gender" value="female"required="true"/>Female</td></tr>    
                    </spring:bind>                

                    <tr><td><spring:message code="label.grade"/> </td><td><form:input path="grade" name="grade" required="true"/></td></tr>
                    <tr><td><spring:message code="label.datehired"/> </td><td><form:input path="date_hired" name="date_hired" placeHolder="yyyy-MM-dd"required="true"/></td></tr>
                    
                    <spring:bind path="currently_employed">                
                        <tr><td><spring:message code="label.currentlyemployed"/> </td><td>
                            <form:radiobutton path="currently_employed" value="yes"required="true"/>Yes 
                            <form:radiobutton path="currently_employed" value="no"required="true"/>No
                        </td></tr>
                    </spring:bind>

                    <tr><td><spring:message code="label.roles"/> </td><td>

                    <c:forEach var="rId" items="${roleId}">
                    <input type="checkbox" name="r" value="${rId}"
                        <c:forEach var="role" items="${roles}" varStatus="loop">                        
                            <c:if test="${role.roleName == rId}">
                                checked 
                            </c:if>
                        </c:forEach>
                        />${rId}
                        <br/>
                    </c:forEach>       
                    <tr><td></td><td colspan="2">   
                    <input type="submit" value="Submit"/>    
                    <spring:url value="Persons" var="cancel" />
                    <input type="button" onclick="location.href='${cancel}'" value="Cancel"></td></tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
