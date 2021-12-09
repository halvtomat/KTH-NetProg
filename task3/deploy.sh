javac -cp ~/Documents/apache-tomcat-9.0.56/lib/servlet-api.jar:WEB-INF/classes/ WEB-INF/classes/controller/*.java
jar -cvf app.war *
mv app.war ~/Documents/apache-tomcat-9.0.56/webapps/
