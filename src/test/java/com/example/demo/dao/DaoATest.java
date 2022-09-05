package com.example.demo.dao;

import com.example.demo.dto.A;
import com.example.demo.dto.B;
import com.example.demo.dto.C;
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension;
import io.zonky.test.db.postgres.junit5.PreparedDbExtension;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.testing.junit5.JdbiExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;

class DaoATest {

    @RegisterExtension
    static final PreparedDbExtension PREPARE_DB_EXTENSION = EmbeddedPostgresExtension.preparedDatabase(
            ds -> new ResourceDatabasePopulator(
                    new ClassPathResource("db/migration/init.sql")
            ).execute(ds)
    );

    @RegisterExtension
    static final JdbiExtension JDBI_EXTENSION = new JdbiExtension() {

        @Override
        public String getUrl() {
            return null;
        }

        @Override
        protected DataSource createDataSource() {
            return PREPARE_DB_EXTENSION.getTestDatabase();
        }
    }.installPlugins();

    @Test
    void testSelectA(Jdbi jdbi) {
        Long id1 = 1L;
        Long id2 = 2L;

        List<A> expectedById1 = List.of(new A(
                new B(id1, "first"),
                new C(id1, "additional")
        ));

        List<A> expectedById2 = List.of(new A(
                new B(id2, "second"),
                null
        ));

        List<A> actualById1 = jdbi.withExtension(DaoA.class, dao -> dao.selectA(id1));
        List<A> actualById2 = jdbi.withExtension(DaoA.class, dao -> dao.selectA(id2));

        Assertions.assertEquals(expectedById1, actualById1);
        Assertions.assertEquals(expectedById2, actualById2);
    }
}