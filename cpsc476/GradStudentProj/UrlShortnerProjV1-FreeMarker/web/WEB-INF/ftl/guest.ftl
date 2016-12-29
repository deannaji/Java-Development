<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Url Shortner</title>
</head>
<body>
    <h3>Welcome guest..</h3>
    <h3>Please <a href="login">login</a>, or use public access only.</h3>
    
    <#--This is the form used by public users to enter short URL, in order to get the full URL-->
    <form action="public" method="POST">
        <input type="hidden" name="action" value="guest" />
        <label>Enter Short URL: </label>
        <input type="text" name="shortUrl" />
        <br/>
        <br/>
        <input type="submit" value="Get Long URL" />
    </form>
</body>
</html>