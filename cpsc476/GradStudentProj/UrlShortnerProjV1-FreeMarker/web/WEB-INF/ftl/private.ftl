<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Private Page</title>
</head>
<body>
    <h1>Hello ${username}, This is you private page!</h1><br>
    <a href="login?logout" align="right">Logout</a>
    <h3>Shorten your url here!!</h3><br><br>
    <h3>Enter a long url:</h3>

    <form method="POST" action="private">
          <input type="text" name="longurl"><br/><br/>
          <input type="submit" value="Submit" />
    </form>
    
    <br><h3>Your shortened url for <#if (long)?? >${long}</#if> is : </h3>

    <#-- This is (if_Exists) freemarker's syntax. Used to check if a variable exists, before operating on it. --> 
    <#if (short)?? >
       ${short}
    </#if>
    <br>
    
    <#-- This table shows all shortened urls with their clicks numbers-->
    <table border="1" width="400"><tr>
        <th>Short url</th>
        <th>Clicks</th></tr>    
        <#if (urls)?? >
           <#list urls as item >
                <#if (item)?? >
                    <tr>
                        <td> <a href="private?action=redirect&url=<#if (item.url)?? >${item.url}"</#if> /> <#if (item.url)?? >${item.url}</#if> </td>
                        <td> <#if (item.clicks)?? >${item.clicks}</#if> </td>
                    </tr>
                </#if>
          </#list>
        </#if>  
    </table>
   
</body>
</html>