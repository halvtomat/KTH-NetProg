javac -cp ~/Documents/apache-tomcat-9.0.56/lib/servlet-api.jar:WEB-INF/classes/ WEB-INF/classes/controller/*.java
jar -cvf project.war *
mv project.war ~/Documents/apache-tomcat-9.0.56/webapps/
