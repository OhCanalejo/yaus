<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="RootPath" value="${pageContext.request.contextPath}"/>

<div id="home" data-role="page">
	<header class="bg-clear">
	</header>
      
	<div class="content">
		<!-- Shorten -->
		<form action="shorten" method="GET" autocomplete="off" id="shorten_form">
			<div class="shorten_form">  
				<label for="url">type or paste here <strong>your long url</strong></label>
				<input type="text" name="url" id="url" required="required" />
				<button id="shorten" type="submit">Shorten It!</button>
				<c:if test="${shortenedURL != null}" > 
					<div>
						<label>This is the shortened url generated: <a href="${shortenedURL}">${shortenedURL}</a></label> 
					</div> 
				</c:if> 
				<c:if test="${shorten_error != null}" > 
					<div>
						<label>${shorten_error}</label> 
					</div> 
				</c:if> 
			</div>
		</form>
		
		<!-- Enlarge -->
		<form action="getLongURL" method="GET" autocomplete="off" id="enlarge_form">
			<div class="enlarge_form">  
				<label for="short_url">type or paste here <strong>your short url</strong></label>
				<input type="text" name="short_url" id="short_url" required="required" />
				<button id="enlarge" type="submit">Get long URL</button>
				<c:if test="${yausURL != null}" > 
					<div>
						<label>This is the long url: <a href="${yausURL.longURL}">${yausURL.longURL}</a></label>
					</div>
					<div> 
						<label>Clicks: ${yausURL.clicsCount}</label>
					</div>
					<div> 
						<label>Created At: ${yausURL.createdAt}</label>
					</div> 
				</c:if> 
				<c:if test="${enlarge_error != null}" > 
					<div>
						<label>${enlarge_error}</label> 
					</div> 
				</c:if> 
			</div>
		</form>
	</div>

</div>      
      