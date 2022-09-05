package com.example.demo.dao;

import com.example.demo.dto.A;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterConstructorMapper(value = A.class)

// TODO: Maybe register mappers here instead of `@Nested(value = "b")` inside A class
//@RegisterConstructorMapper(value = B.class, prefix = "b")
//@RegisterConstructorMapper(value = C.class, prefix = "c")

public interface DaoA {

    @SqlQuery("SELECT " +
            "     b.id                  AS b_id, " +
            "     b.some_column         AS b_s, " +
            "     c.id                  AS c_id, " +
            "     c.additional_column   AS c_additionalColumn " +
            " FROM table_b as b " +
            " LEFT JOIN table_c AS c " +
            " ON b.id = c.id " +
            " WHERE b.id = :id;")
    List<A> selectA(Long id);
}
