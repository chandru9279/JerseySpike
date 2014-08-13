JerseySpike
===========

Jersey based webservice with embedded jetty built by Maven


Add the libs to local maven repository

* mvn install:install-file -Dfile=lib/sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

Run

> mvn dependecy:resolve

> mvn package

> java -Dsettings="d:\Work\Repos\JerseySpike\notionalstock-services\settings.properties" -jar target/notionalstock-services.war

will start the service in localhost:10000.
Change the settings path according to local dev box/environment.
Hit http://localhost:10000/webapi/openingstock to get sample response



TODO:

Connect to SQL Server, sample query.