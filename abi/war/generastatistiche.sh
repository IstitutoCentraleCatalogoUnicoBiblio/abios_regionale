#!/bin/sh

# path con le librerie (jar) di tomcat
tomcatlib=/home/marina/opt/apache-tomcat-6.0.29/lib

cwd=`pwd`
rm -rf $cwd/statistiche/*.html
rm -rf $cwd/statistiche/*.xls
rm -rf $cwd/statistiche/*.txt

# il -Dcatalina.home=/tmp serve per redirigere il file di log in /tmp
java -Dcatalina.home=/tmp -cp "./WEB-INF/classes:./WEB-INF/lib/*:$tomcatlib/*" it.inera.abi.tools.GeneraStatistiche $cwd