#maven新建sso-server项目

1. 在src/main/resources中增加aplication.properties
  
  #don't use HTTPS
  server.ssl.enabled=false

2. 在src/main/resources中增加log4j2.xml

3. pom.xml :

  <dependencies>
		<dependency>
			<groupId>org.apereo.cas</groupId>
			<artifactId>cas-server-webapp-tomcat</artifactId>
			<version>${cas.version}</version>
			<type>war</type>
			<scope>runtime</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.apereo.cas</groupId>
				<artifactId>cas-server-support-bom</artifactId>
				<version>${cas.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
