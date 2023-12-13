//package com.example.testcontainers.testcontainersdemo;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
////@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class TestcontainersDemoApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//
//
//
////  @Bean
////	@Container
////	@ServiceConnection
////	MySQLContainer<?> mysqlContainer() {
////		return new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
////	}
////
////
////
////	@Test
////	void connectionEstablished() {
////		assertThat(mysqlContainer().isCreated()).isTrue();
////		assertThat(mysqlContainer().isRunning()).isTrue();
////	}
//
//}
