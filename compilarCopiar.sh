#!/bin/sh
javac ./WEB-INF/classes/minitienda/*.java -cp ./WEB-INF/lib/*
rm -rf /usr/share/tomcat9/webapps/minitienda
cp -r . /usr/share/tomcat9/webapps/minitienda
