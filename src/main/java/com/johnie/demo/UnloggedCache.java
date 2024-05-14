package com.johnie.demo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * @author johniecheng
 * @version 1.0
 * @date 2024/5/8 17:38
 */
@Data
@Entity(name = "unlogged_cache")
public class UnloggedCache {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private String key;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String value;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime createTime;

}
