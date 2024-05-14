-- DROP TABLE public.unlogged_cache;

-- 创建 UNLOGGED 缓存表
CREATE UNLOGGED TABLE public.unlogged_cache
(
    id          int8      NOT NULL,
    key         text      NOT NULL,
    value       jsonb     NULL,
    create_time timestamp NULL,
    UNIQUE (key),
    PRIMARY KEY (id)
);

-- 创建定时过期的存储过程
CREATE OR REPLACE PROCEDURE expire_caches(retention_period INTERVAL) AS
$$
BEGIN
    DELETE
    FROM unlogged_cache
    WHERE create_time < NOW() - retention_period;

    COMMIT;
END;
$$ LANGUAGE plpgsql;

-- 删除插入时间距离当前时间超过6小时的记录
-- CALL expire_caches('6 hours');