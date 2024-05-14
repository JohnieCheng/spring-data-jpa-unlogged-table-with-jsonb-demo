package com.johnie.demo.service;

import com.johnie.demo.UnloggedCache;

import java.util.List;
import java.util.Optional;

/**
 * @author johniecheng
 * @version 1.0
 * @date 2024/5/13 14:52
 */
public interface UnloggedCacheService {

    UnloggedCache save(UnloggedCache unloggedCache);

    List<UnloggedCache> saveAll(List<UnloggedCache> unloggedCacheList);

    Optional<UnloggedCache> findByKey(String key);

    List<UnloggedCache> findByAttributeWithFunction(String key, String value);

    List<UnloggedCache> findByAttributeWithJsonbOperators(String key, String value);

    List<UnloggedCache> findByNestedAttributeWithJsonbOperators(String key1, String key2, String value);

    List<UnloggedCache> findByNestedAttributeWithFunction(String key1, String key2, String value);

    List<UnloggedCache> findByAttributeWithSpecification(String key, String value);
}
