<html>
<body>
   <h1>Hello, ${name} <#if name=="Dean">, the root user of this computer!</#if> </h1>
   <h3>Your full name is: ${fullname}</h3>
   <h3>Your date of birth is: ${dob}</h3>
   
    <h3>Classes you are taking this semester:</h3>
    <#list classes as class>
      <p>- ${class}</p>
    </#list> 

</body>
<html>