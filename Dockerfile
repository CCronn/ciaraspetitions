FROM tomcat:latest

ADD target/ciaraspetitions.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]