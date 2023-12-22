package com.example.testcontainers.testcontainersdemo;

import static com.example.testcontainers.testcontainersdemo.QueryUtil.performQuery;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

@Slf4j
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OracleDemoApplicationTests {

	@Container
	@ServiceConnection
	static OracleContainer container =
			new OracleContainer("gvenzl/oracle-free:latest");


	@Test
	@DisplayName("Oracle Test Container 가 정상 실행 됩니다.")
	void GivenOracleTestContainerRunThenisRunningisTrue() {
		assertThat(container.isCreated()).isTrue();
		assertThat(container.isRunning()).isTrue();

		log.info("!!! getMappedPort: " + container.getMappedPort(1521));
		log.info("!!! getExposedPorts: " + container.getExposedPorts());
		log.info("!!! getHost: " + container.getHost());
		log.info("!!! getContainerId: " + container.getContainerId());
		log.info("!!! getContainerInfo: " + container.getContainerInfo());
	}

	@Test
	@DisplayName("Oracle Test Container 에서 sysdate 가 test 쿼리를 가져옵니다.")
	public void GivenOracleTestContainerThenABasicSelectQuerySucceeds() throws SQLException{

		ResultSet resultSet = performQuery(container, "SELECT 1 FROM dual");
		int resultSetInt = resultSet.getInt(1);
		assertThat(resultSetInt).as("A basic SELECT query succeeds").isEqualTo(1);

		ResultSet resultSet2 = performQuery(container, "SELECT SYSDATE FROM dual");
		java.sql.Date sysDate = resultSet2.getDate(1);
		assertEquals(LocalDateTime.now().getMonth(), sysDate.toLocalDate().getMonth());

		String containerLogs = container.getLogs();
		log.info("!!! getLogs: " + containerLogs);
		log.info("!!! log ends");

		assertTrue(containerLogs.contains("DATABASE IS READY TO USE!"));

	}




}
