#!/bin/sh
javac -cp ./WEB-INF/lib/servlet-api.jar ./WEB-INF/classes/minitienda/*/*.java
sudo rm -rf /usr/share/tomcat10/webapps/minitienda/*
sudo cp -r . /usr/share/tomcat10/webapps/minitienda
