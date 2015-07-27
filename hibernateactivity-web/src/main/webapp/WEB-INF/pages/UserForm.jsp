<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page session="true" %>

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
            <h1 align="center">Spring Activity</h1>

            <c:choose>
                <c:when test="${personForm.id == 0}">
                <h1>Add Person</h1>
                <div id="fileUpload">                
                <form:form method="post" action="/uploadForm" commandName="uploadForm" enctype="multipart/form-data">
                    <spring:message code="label.uploadfile"/> <input type="file" name="file"/><br/>
                    <spring:message code="label.name"/> <input type="text" name="name" required="true"><br />
                    <input type="submit" value="<spring:message code="label.addfile"/>"/>
                </form:form>
                </div>
                <br />
                or
                </c:when>
                <c:otherwise>
                <h1>Update Person</h1>
                </c:otherwise>
            </c:choose>
            
            <br/>
            <form:form method="POST" commandName="personForm">
                <table align="center">
                
                <spring:bind path="names">
                    <tr><td><spring:message code="label.firstname"/></td>
                        <td> <form:input path="names.first_name" required="true"/></td>
                        <td> <form:errors path="names.first_name" class="errors"/> </td>    
                    </tr>
                    <tr><td><spring:message code="label.lastname"/> </td>
                        <td><form:input path="names.last_name"required="true"/></td>
                        <td><form:errors path="names.last_name" class="errors"/></td>
                    </tr>
                </spring:bind>                

                    <tr><td><spring:message code="label.address"/></td>
                        <td><form:input path="address" id="address" placeholder="address"required="true"/></td>
                        <td><form:errors path="address" class="errors"/></td>        
                    </tr>
                    <tr><td><spring:message code="label.bday"/></td>
                        <td><form:input type="date" path="bday" name="bday" placeHolder="dd/MM/yyyy"required="true"/></td>
                        <td><form:errors path="bday" class="errors"/></td>
                    </tr>
                    <tr><td><spring:message code="label.age"/> </td>
                        <td><form:input path="age" name="age"required="true"/></td>
                        <td><form:errors path="age" class="errors"/></td>
                    </tr>
                    <tr><td><spring:message code="label.contact"/></td>
                        <td><select id="contact">
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
                    </td><td><form:errors path="contact" class="errors"/></tr></tr>
                    
                    <spring:bind path="gender">
                        <tr><td><spring:message code="label.gender"/> </td>
                            <td><form:radiobutton path="gender" name="gender" value="male"required="true"/>Male
                                <form:radiobutton path="gender" value="female"required="true"/>Female</td>
                            <td><form:errors path="gender" class="errors"/></td>
                        </tr>    
                    </spring:bind>                

                    <tr><td><spring:message code="label.grade"/> </td>
                        <td><form:input path="grade" name="grade" required="true"/></td>
                        <td><form:errors path="grade" class="errors"/></td>
                    </tr>
                    <tr><td><spring:message code="label.datehired"/> </td>
                        <td><form:input type="date" path="date_hired" name="date_hired" placeHolder="dd/MM/yyyy"required="true"/></td>
                        <td><form:errors path="date_hired" class="errors"/></td>
                    </tr>
                    
                    <spring:bind path="currently_employed">                
                        <tr><td><spring:message code="label.currentlyemployed"/> </td><td>
                            <form:radiobutton path="currently_employed" value="yes"required="true"/>Yes 
                            <form:radiobutton path="currently_employed" value="no"required="true"/>No
                            </td>
                            <td><form:errors path="currently_employed" class="errors"/></td>
                        </tr>
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
                    <tr><td></td><td>   
                    <input type="submit" value="<spring:message code="label.submit"/>"/>    
                    <spring:url value="persons" var="cancel" />
                    <input type="button" onclick="location.href='${cancel}'" value="<spring:message code="label.cancel"/> "></td><td></td></tr>
                </table>
            </form:form>
        </div>
    </body>
</html>

