package com.johnie.demo.service;

import com.johnie.demo.entity.UnloggedCache;
import com.johnie.demo.specification.UnloggedCacheSpecification;
import com.johnie.demo.repository.UnloggedCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author johniecheng
 * @version 1.0
 * @date 2024/5/13 14:52
 */
@Service
@RequiredArgsConstructor
public class UnloggedCacheServiceImpl implements UnloggedCacheService {

    private final UnloggedCacheRepository unloggedCacheRepository;

    @Override
    public UnloggedCache save(UnloggedCache unloggedCache) {
        return unloggedCacheRepository.save(unloggedCache);
    }

    @Override
    public List<UnloggedCache> saveAll(List<UnloggedCache> unloggedCacheList) {
        return unloggedCacheRepository.saveAll(unloggedCacheList);
    }

    @Override
    public Optional<UnloggedCache> findByKey(String key) {
        return unloggedCacheRepository.findByKey(key);
    }

    @Override
    public List<UnloggedCache> findByAttributeWithJsonbOperators(String key, String value) {
        return unloggedCacheRepository.findByAttributeWithJsonbOperators(key, value);
    }

    @Override
    public List<UnloggedCache> findByNestedAttributeWithJsonbOperators(String key1, String key2, String value) {
        return unloggedCacheRepository.findByNestedAttributeWithJsonbOperators(key1, key2, value);
    }

    @Override
    public List<UnloggedCache> findByAttributeWithFunction(String key, String value) {
        return unloggedCacheRepository.findByAttributeWithFunction(key, value);
    }

    @Override
    public List<UnloggedCache> findByNestedAttributeWithFunction(String key1, String key2, String value) {
        return unloggedCacheRepository.findByNestedAttributeWithFunction(key1, key2, value);
    }

    @Override
    public List<UnloggedCache> findByAttributeWithSpecification(String key, String value) {
        UnloggedCacheSpecification cacheSpecification = new UnloggedCacheSpecification(key, value);
        return unloggedCacheRepository.findAll(cacheSpecification);
    }
}
