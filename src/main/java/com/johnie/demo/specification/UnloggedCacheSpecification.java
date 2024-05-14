package com.johnie.demo.specification;

import com.johnie.demo.entity.UnloggedCache;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author johniecheng
 * @version 1.0
 * @date 2024/5/14 15:21
 */
public record UnloggedCacheSpecification(String key, String value) implements Specification<UnloggedCache> {

    @Override
    public Predicate toPredicate(Root<UnloggedCache> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.equal(
                cb.function("jsonb_extract_path_text", String.class, root.get("value"), cb.literal(key)),
                value
        );
    }
}
