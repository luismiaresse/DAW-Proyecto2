#!/bin/sh
javac -cp /usr/share/tomcat9/lib/servlet-api.jar ./WEB-INF/classes/minitienda/*/*.java
sudo rm -rf /usr/share/tomcat9/webapps/minitienda/*
sudo cp -r . /usr/share/tomcat9/webapps/minitienda
