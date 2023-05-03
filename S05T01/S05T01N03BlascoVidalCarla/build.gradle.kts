plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03"
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
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webflux
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.0.5")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-webflux-ui
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
