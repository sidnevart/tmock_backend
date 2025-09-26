plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.tmock"
version = "0.0.1-SNAPSHOT"
description = "auth-service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")

	// Kafka/Config/Eureka/Feign — как было
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")


	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
	implementation("org.springframework.security:spring-security-oauth2-jose") // Nimbus JOSE
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	implementation("com.nimbusds:nimbus-jose-jwt:9.37")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
}


dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
