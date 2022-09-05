package com.example.demo.dto;

import lombok.Value;
import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.PropagateNull;
import org.springframework.lang.Nullable;

@Value
public class A {
    @Nested(value = "b")
    B b;

    @Nullable
    @Nested(value = "c")
    @PropagateNull(value = "id")
    C c;
}
