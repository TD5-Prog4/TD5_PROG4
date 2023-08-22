package com.example.prog4.config;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    private final DataSource employeeDataSource;
    private final DataSource cnapsDataSource;

    public FlywayConfig(DataSource baseDataSource,
                                  @Qualifier("cnapsEmployeeDataSource")
                                  DataSource cnapsDataSource) {
        this.employeeDataSource = baseDataSource;
        this.cnapsDataSource = cnapsDataSource;
    }

    @PostConstruct
    public void migrate() {
        migrateDataSource(employeeDataSource, "classpath:/db/migration/employee");
        migrateDataSource(cnapsDataSource, "classpath:/db/migration/cnaps");
    }

    private void migrateDataSource(DataSource dataSource, String... locations) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(locations).load();
        flyway.migrate();
    }
}
