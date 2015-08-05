package de.weichert.demo.multitenancy;

import de.weichert.multitenancy.datasource.TenantDataSourceInitializer;
import de.weichert.multitenancy.model.TenantDataSourceConfigEntity;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by Jannik on 02.07.15.
 */
@Component
public class CustomDataSourceInitializer implements TenantDataSourceInitializer {

    public DataSource initializeDataSource(TenantDataSourceConfigEntity tenantDataSourceConfigEntity) {
        return
                DataSourceBuilder.create()
                .url(tenantDataSourceConfigEntity.getUrl())
                .username(tenantDataSourceConfigEntity.getUsername())
                .password(tenantDataSourceConfigEntity.getPassword())
                .build();
    }
}
