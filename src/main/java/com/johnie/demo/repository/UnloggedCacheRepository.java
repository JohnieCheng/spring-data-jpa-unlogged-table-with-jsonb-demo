package com.johnie.demo.repository;

import com.johnie.demo.entity.UnloggedCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author johniecheng
 * @version 1.0
 * @date 2024/5/13 14:52
 */
public interface UnloggedCacheRepository extends JpaRepository<UnloggedCache, Long>, JpaSpecificationExecutor<UnloggedCache> {
    Optional<UnloggedCache> findByKey(String key);

    @Query(value = "select uc.* from unlogged_cache uc where uc.value ->> ?1 = ?2 ", nativeQuery = true)
    List<UnloggedCache> findByAttributeWithJsonbOperators(String key, String value);

    @Query(value = "select uc.* from unlogged_cache uc where uc.value -> ?1 ->> ?2 = ?3 ", nativeQuery = true)
    List<UnloggedCache> findByNestedAttributeWithJsonbOperators(String key1, String key2, String value);

    @Query(value = "select uc.* from unlogged_cache uc where jsonb_extract_path_text(uc.value, ?1) = ?2 ", nativeQuery = true)
    List<UnloggedCache> findByAttributeWithFunction(String key, String value);

    @Query(value = "select uc.* from unlogged_cache uc where jsonb_extract_path_text(uc.value, ?1 , ?2) = ?3 ", nativeQuery = true)
    List<UnloggedCache> findByNestedAttributeWithFunction(String key1, String key2, String value);
}
