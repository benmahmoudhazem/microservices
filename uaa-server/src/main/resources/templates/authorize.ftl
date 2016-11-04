<html>
<head>
        <meta charset="utf-8" />
       <meta http-equiv="X-UA-Compatible" content="IE=edge" />
       <title>Authentication Confirmation</title>
       <meta name="description" content="" />
       <meta name="viewport" content="width=device-width" />
    <!--   <base href="/" /> -->
      <link rel="stylesheet" type="text/css"
             href="/uaa/webjars/bootstrap/css/bootstrap.min.css" />
       <script type="text/javascript" src="/uaa/webjars/jquery/jquery.min.js"></script>
       <script type="text/javascript"
               src="/uaa/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Please Confirm</h2>

    <p>
        Do you authorize "${authorizationRequest.clientId}" at "${authorizationRequest.redirectUri}" to access your
        protected resources
        with scope ${authorizationRequest.scope?join(", ")}.
    </p>
    <form id="confirmationForm" name="confirmationForm"
          action="../oauth/authorize" method="post">
        <input name="user_oauth_approval" value="true" type="hidden"/>
        <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Approve</button>
    </form>
    <form id="denyForm" name="confirmationForm"
          action="../oauth/authorize" method="post">
        <input name="user_oauth_approval" value="false" type="hidden"/>
        <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Deny</button>
    </form>
</div>
</body>
</html>
