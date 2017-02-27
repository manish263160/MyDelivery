<input type="hidden" value="${TI_CTX}" id="ti_ctx">
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" >

<script src="${TI_CTX}/static/js/searchMasterAPI.js"></script>
<link href="${TI_CTX}/static/css/header-footer.css" rel="stylesheet">
<%-- <link href="${TI_CTX}/static/lib/sendbird/sample-chat.css" rel="stylesheet"> --%>
<link href="${TI_CTX}/static/lib/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
<link href="${TI_CTX}/static/lib/select2/flag-icon.min.css"
	rel="stylesheet" type="text/css">
	<link href="${TI_CTX}/static/lib/css/bootstrap-social.css"
	rel="stylesheet" type="text/css">
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.userName"
		var="loggedInUserId" />
	<security:authentication property="principal.name"
		var="loggedInUserName" />
	<security:authentication property="principal.APPID" var="APPID" />
	<security:authentication property="principal.auth" var="auth" />
	<security:authentication property="principal.userId" var="userId" />
	<security:authentication property="principal.countryName"
		var="countryname" />
	<input type="hidden" id="userName" name="userName"
		value="${loggedInUserId }" />
	<input type="hidden" id="APPID" value="${APPID }" />
	<input type="hidden" id="auth" value="${auth }" />
	<input type="hidden" id="currentUserID" value="${userId }" />
	<input type="hidden" id="currentUserCountry" value="${countryname }" />
</security:authorize>
<header class="header fullrow">
<div id="socialLoader" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; z-index:9999; background:rgba(0, 0, 0, 0.2);color: white; text-align: center;">
<div style="width:90px;height:89px;position:absolute;top:50%;left:50%;padding:2px;z-index:10; margin:-45px 0 0 -35px">
<img src='${TI_CTX}/static/images/ajax-loader.gif' /><br>Please wait..</div>
</div>
	<div class="header-top fullrow ">
		<div class="container">

			<security:authorize access="!isAuthenticated()">
				<a href="${TI_CTX}/home.htm" class="logo"> <img
					class="img-responsive" src="${TI_CTX}/static/images/logo.png"
					alt="">
				</a>
				<button type="button" class="btn btn-primary logiin-btn visible-xs"
					data-toggle="collapse" data-target="#loginForm"
					aria-expanded="false" aria-controls="loginForm">
					<i class="fa fa-sign-in" aria-hidden="true"></i> Login
				</button>
				<%@ include file="login.jsp"%>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<a href="${TI_CTX}/dashboard.htm" class="logo"><img
					class="img-responsive" src="${TI_CTX}/static/images/logo.png"
					alt=""> </a>
				<div class="user-links">
					<ul>
						<li>
							<%-- <a class="active request" href="${TI_CTX}/dashboard.htm"> --%>
							<i class="icon" id="notificationBell" type="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
							<span class="bubble notificationCount"></span>
							<div class="dropdown-menu dropdown-menu-right"
								id="notification_div" aria-labelledby="notificationBell">
								<div class="user-list-box">
									<ul>

									</ul>
								</div>
							</div>
						</li>
						<li><a class="message"
							href="${TI_CTX}/messaging.htm?type=inbox"><i
								class="icon icon2 "></i><span class="bubble messageCount"></span></a></li>
						<li class="dropdown"><a aria-expanded="false"
							aria-haspopup="true" role="button" data-toggle="dropdown"
							class="dropdown-toggle" href="#"> <i class="icon icon3"></i></a>
							<ul class="dropdown-menu dropdown-menu-right">
								<li><a href="${TI_CTX}/account_settings.htm">Account
										Settings</a></li>
								<li><a href="${TI_CTX}/privacy_settings.htm">Privacy
										Settings</a></li>
								<li ${activeTab eq 'invite'?'class="active"':'' }><a
										href="${TI_CTX}/invite.htm">Invite via gmail</a></li>	
								<li><a href="${TI_CTX}/logout.htm" id="logout-button">Log
										Out</a></li>
							</ul></li>
						<security:authentication property="principal.profileImageUrl"
							var="profileUrl" />
							<security:authentication property="principal.userLoginProvider"
							var="loginProvider" />
						<input type="hidden" id="userHeaderImage" value="${profileUrl }" />
						<input type="hidden" id="loginprovider" value="${loginProvider}" />
						<li class="user-info">
							<div class="dropdown">
								<a href="${TI_CTX}/profile.htm" class="dropdown-toggle"> <% java.util.Date dt = new java.util.Date(); %>
									<c:choose>
										<c:when test="${ not empty profileUrl}">
											<img id="profile_header" class="img-responsive" src=""
												onerror="this.src='${TI_CTX}/static/images/profile.png?<%=dt.getTime ()%>'"
												alt="">
										</c:when>
										<c:otherwise>
											<img id="profile_header" class="img-responsive"
												src="${TI_CTX}/static/images/profile.png?<%=dt.getTime ()%>"
												alt="">
										</c:otherwise>
									</c:choose>
								</a>
								<%-- <ul class="dropdown-menu">
									<li><a href="${TI_CTX}/logout.htm">Log Out</a></li>
								</ul> --%>
							</div>
						</li>
					</ul>
				</div>
				<!-- include here search jsp -->
				<c:if test="${!fn:endsWith(pageContext.request.requestURI, 'LandingPage.jsp')}">
					<jsp:include page="search.jsp" />
				</c:if>
			</security:authorize>
		</div>
	</div>
	<security:authorize access="isAuthenticated()">
		<nav class="navbar fullrow">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li ${activeTab eq 'home'?'class="active"':'' }><a
							href="${TI_CTX}/dashboard.htm">Dashboard</a></li>
						<li ${activeTab eq 'profile'?'class="active"':'' }><a
							href="${TI_CTX}/profile.htm">Profile</a></li>
						<li	${activeTab eq 'pending request' or activeTab eq 'connections' or activeTab eq 'sent request' ?'class="dropdown active"':'class="dropdown"' }>
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">My Network<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="${TI_CTX}/network.htm">Connections</a></li>
								<li><a href="${TI_CTX}/network-request.htm">Pending Requests</a></li>
								<li><a href="${TI_CTX}/sent-request.htm">Sent Requests</a></li>
							</ul>
						</li>
						<li ${activeTab eq 'feed'?'class="active"':'' }><a
							href="${TI_CTX}/feed.htm">Feed</a></li>
						<li ${activeTab eq 'messaging'?'class="active"':'' }><a
							href="${TI_CTX}/messaging.htm?type=inbox">Messaging</a></li>
						<li ${activeTab eq 'createEvent'?'class="active"':'' }><a
							href="${TI_CTX}/addEvent.htm">Create Event</a></li>
						<li ${activeTab eq 'hunt'?'class="active"':'' }><a
							href="${TI_CTX}/hunt.htm">Hunt</a></li>
						<li><a href="http://52.220.39.119/blog?logIn=true">Blog</a></li>
						<li>
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Chapters<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="http://www.tomorrowsindia.com/">Tomorrow's India</a></li>
							</ul>
						</li>
						<security:authorize access="isAuthenticated()">
							<%-- <li class="pull-right"><a href="${TI_CTX}/logout.htm">Log Out</a></li> --%>
						</security:authorize>
					</ul>
				</div>
				<!--/.navbar-collapse -->
			</div>
		</nav>
	</security:authorize>
	<security:authorize access="!isAuthenticated()">
		<nav class="navbar fullrow" id="profile-navbar">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li ${activeTab eq 'home'?'class="active"':'' }><a
							href="${TI_CTX}/home.htm">Home</a></li>
						<li ${activeTab eq 'hunt'?'class="active"':'' }><a
							href="${TI_CTX}/hunt.htm">Hunt</a></li>
						<li ${activeTab eq 'business'?'class="active"':'' }><a
							href="#about-us">About Us</a></li>
						<li ${activeTab eq 'knowledge'?'class="active"':'' }><a
							href="#contact-us">Contact Us</a></li>
						<li ${activeTab eq 'register'?'class="active"':'' }><a
							href="${TI_CTX}/registration.htm">Register</a></li>
						<li><a href="http://52.220.39.119/blog?logIn=false">Blog</a></li>
						<li>
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Chapters<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="http://www.tomorrowsindia.com/">Tomorrow's India</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!--/.navbar-collapse -->
			</div>
		</nav>
	</security:authorize>
</header>
<!-- forgot password modal start here -->
<div class="modal fade" tabindex="-1" role="dialog" id="forgotPwdModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Enter your registered email</h4>
			</div>
			<form action="${TI_CTX}/forgotPassword.htm" method="get">
				<div class="modal-body">
					<div class="form-group">
						<input type="text" name="email" placeholder=" Enter email"
							class="form-username form-control">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Send
						Password</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- Modal -->
  <div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Social Sign Up</h4>
        </div>
        <div class="modal-body ">
       
          <div class="row  social-buttons">
    	    <div class="col-xs-8 col-sm-8 col-sm-offset-2 col-xs-offset-2">
    	    <a class="btn btn-block btn-social btn-lg btn-facebook" onclick="testAPI();">
            <span class="fa fa-facebook"></span> Sign in with Facebook
          </a>  
	        </div></div>
	        <div class="row  social-buttons">
	        <div class="col-xs-8  col-sm-8 col-sm-offset-2 col-xs-offset-2">
	        <a class="btn btn-block btn-social btn-lg btn-twitter" href="${TI_CTX}/signinTwitter.htm">
            <span class="fa fa-twitter"></span> Sign in with Twitter
          </a>
	        </div>
	        </div>
	        <div class="row  social-buttons">
	        <div class="col-xs-8  col-sm-8 col-sm-offset-2 col-xs-offset-2">
	        <a class="btn btn-block btn-social btn-lg btn-google" onclick="loginGooglePlus();">
            <span class="fa fa-google"></span> Sign in with Google +
          	</a>
	        </div>
	        </div>
      </div>
    </div>
  </div></div>
<security:authorize access="!isAuthenticated()">
	<jsp:include page="loginModal.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			var url = window.location.href;
			/* if(!(url.indexOf('belongs.htm') >0 || url.indexOf('registration.htm') >0 || url.indexOf('login.htm') >0 ||
					url.indexOf('insertUserNeedsOfferings.htm') >0 || url.indexOf('userRegistration.htm') >0
					|| url.indexOf('#Business') >0	|| url.indexOf('#Knowledge') >0	|| url.indexOf('#Culture') >0
					|| url.indexOf('user-verification') >0 || url.indexOf('activateUser') >0
			) ) { 
				$("#login-modal"). modal('show');
			}  */
			if (url.indexOf('home') > 0
					|| url.indexOf('hunt') > 0
					|| url.indexOf('search-result') > 0) {
				//$("#login-modal"). modal('show');
			}
			var message = '${message}';
			if (message == 'Please check your email to activate registration'
					|| message == 'You are activated please login'
					|| message == 'You are already activated please login'
					|| message == 'Password sent to your registered email please check'
					|| message == 'Email is not registered with us') {
				$("#login-modal").modal('hide');
				$("#email-check-modal #modal-message").html(message)
				$("#email-check-modal").modal('show');
			}
		});
	</script>
</security:authorize>

<script type="text/javascript">
	$(document).ready(function() {
		var url = window.location.href;
		// Add smooth scrolling on all links inside the navbar
		$("#navbar a").on('click',function(event) {
			// Make sure this.hash has a value before overriding default behavior
			var hash = this.hash;
			if (url.indexOf('home.htm') > 0	&& hash != undefined && hash !== "" && hash.length) {
				event.preventDefault();
				// Store hash
				if (hash == '#about-us'	|| hash == '#contact-us') {
					$('html, body').animate({
						scrollTop : $(hash).offset().top - 150
					},
					800,
					function() {
						window.location.hash = hash;
					});
				}
			} else if (hash != undefined && hash.length) {
				if (hash == '#about-us'	|| hash == '#contact-us') {
					window.location.href = '${TI_CTX}/home.htm'+ hash;
				}
			}

		});
		/* ------end click */

		setTimeout(function() {
			$('.error_message').slideUp();
		}, 4000)

		if ($("#userName").val() != undefined
				&& $("#userName").val() != ""
				&& $("#userName").val() != 0) {
			$.ajax({
				url : "${TI_CTX}/getPendingRequestsList.json",
				type : "get",
				success : function(response) {
					if (response.length != 0)
						$(".notificationCount").text(
								response.length);
				},
				error : function(e) {
					alert("error");
				}
			});

			$.ajax({
				url : "${TI_CTX}/unreadCount.json",
				type : "POST",
				success : function(response) {
					if (response.data != 0)
						$(".messageCount").text(response.data);
				},
				error : function(e) {
					alert("error");
				}
			});
		}
	});

	//for updating header profile image
	if ($("#userHeaderImage").val() != undefined && $("#userHeaderImage").val() != "") {
	   if ($("#loginprovider").val() != undefined && $("#loginprovider").val() == 'local') {
			var posheader = ($("#userHeaderImage").val()).lastIndexOf('.');
			var headerNewPath = ($("#userHeaderImage").val()).substring(0,posheader)
					+ "_HEADER."
					+ ($("#userHeaderImage").val()).substring(posheader + 1);
			$("#profile_header").attr("src", headerNewPath);
		}else{
			$("#profile_header").attr("src", $("#userHeaderImage").val());
		}
	}

	//clear local storage when clicked on logout button
	$("#logout-button").click(function() {
		localStorage.clear();
	});

	function showUserNeedOffer(val) {
		var tag;
		val = val.trim();
		if (val == 'need') {
			tag = $('a:contains("Needs")').parent().find(
					'a:contains("Products")');

		} else if (val == 'offer') {
			tag = $('a').filter(function(index) {
				return $(this).attr("fridentify") === "ofr_for_click";
			}).parent().find('a:contains("Products")');

		} else if (val == 'Skills') {
			tag = $('a').filter(function(index) {
				return $(this).text() === "Skills";
			});

		} else if (val == 'Experience') {
			tag = $('a:contains("Employment Details")');

		} else if (val == 'Education') {
			tag = $('a').filter(function(index) {
				return $(this).text() === "Education";
			});

		}
		//console.log("tag----" + tag);
		if (!tag.closest('ul').parent().hasClass("active")) {
			$("li").removeClass("active");
			$("li").find("ul").slideUp();

		} else {
		}
		tag.closest('ul').parent().addClass('active');

		if (tag.closest('ul').css('display') == 'none') {
			tag.closest('ul').slideToggle();
		}
		//tag.closest('ul').slideToggle();
		tag.addClass('current');
		tag.click();
		tag.focus();
	}
	//header notification handling
	$("#notificationBell")
			.click(
					function() {
						var element = $(this);
						$
								.ajax({
									url : "${TI_CTX}/getPendingRequestsList.json",
									type : "get",
									success : function(response) {
										element.next().next('.dropdown-menu')
												.find("ul").html("");
										var defaultProfile = "\""
												+ "${TI_CTX}/static/images/profile.png"
												+ "\"";
										$
												.each(
														response,
														function(index, value) {
															if (value.userName != null) {
																if (value.profileImageUrl != null
																		&& value.profileImageUrl.length == 0)
																	value.profileImageUrl = defaultProfile;
																var li = "<a href=\"${TI_CTX}/network-request.htm\" style='text-decoration:none;color:#333;'><li><img width=\"20\" height=\"20\" src="
								+ value.profileImageUrl
								+ " onerror='this.src="
								+ defaultProfile
								+ "'> You have pending request from "
																		+ value.userName
																		+ "</li></a>";
																element
																		.next()
																		.next(
																				'.dropdown-menu')
																		.find(
																				"ul")
																		.append(
																				li);
																element
																		.next()
																		.next(
																				'.dropdown-menu')
																		.find(
																				"ul")
																		.append(
																				"</a>");
															}

														});
										if (response != undefined
												&& response.length == 0) {
											var span = "<span text-align='center'>No new notifications</span>";
											element.next().next(
													'.dropdown-menu')
													.find("ul").append(span);
										}
									},
									error : function(e) {
										alert("Error occured while reading notification");
									}
								})
					});
</script>

<!-- do not remove this, used as global for india's cities -->
<script type="text/javascript">
var cityListForIndia; 
$(document).ready(function() {
	if (cityListForIndia ==undefined) {
		$.ajax({
	  		url: context+"/getIndiaCities.json",
	  		type:"post",
	  		success:function(cities){
	  			cityListForIndia = cities;
	  		},
	  		error:function(e){
	//			    alert("error");		
	  		}
	  	});
	}
});

function locationhref(href,type,needoffer){
	//   console.log(href+" "+type+" "+needoffer);
	switch (type.toLowerCase()) {
	case 'onlinecourse':
		type='_online_courses';
		break;
		
	case 'parttimeeducation':
		type='_part_time_education';
		break;
	case 'fulltimeeducation':
		type='_full_time_education';
		break;
	case 'other':
		type='_other';
		break;
	default:
		break;
	}
	var appenfStr="";
	if(needoffer==0 && type != 'event'){
		
		appenfStr="#"+"offer"+type;
	}else if(needoffer==0 && type == 'event'){
		appenfStr="#"+"listed_"+type;
	}
	
	else if(needoffer==1){
		
		appenfStr="#"+"need"+type;
	}
	
	href +=appenfStr;
	window.open(href, '_blank');
	
}
</script>
<script src="${TI_CTX}/static/js/social_login.js" type="text/javascript"></script>
