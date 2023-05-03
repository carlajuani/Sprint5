plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.modelmapper:modelmapper:3.1.1")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
