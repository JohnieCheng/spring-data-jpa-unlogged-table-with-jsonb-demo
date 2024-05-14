package com.johnie.demo;

import com.johnie.demo.entity.UnloggedCache;
import com.johnie.demo.service.UnloggedCacheService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DemoApplicationTests {

    final String PRE = "TEST_";
    @Autowired
    private UnloggedCacheService unloggedCacheService;

    public Stream<Arguments> generator() {
        return Stream.of(Arguments.arguments(100000, 200000));
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("generator")
    void testUnloggedTableInsert(int start, int end) {
        List<UnloggedCache> unloggedCacheList = IntStream.range(start, end).mapToObj(operand -> {
            UnloggedCache unloggedCache = new UnloggedCache();
            unloggedCache.setKey(PRE + operand);
            unloggedCache.setValue("{\"brand\": \"Sony\",\"color\": \"blue\", \"size\": \"6 inch\", \"details\": {\"category\": \"electronics\", \"model\": \"WH-1000XM4\"}}");
            unloggedCache.setCreateTime(LocalDateTime.now());
            return unloggedCache;
        }).toList();
        List<UnloggedCache> saved = unloggedCacheService.saveAll(unloggedCacheList);
        Assertions.assertTrue(saved.stream().map(UnloggedCache::getId).allMatch(Objects::nonNull));
    }

    @Order(2)
    @Test
    void testUnloggedTableQueryByKey() {
        Optional<UnloggedCache> optional = unloggedCacheService.findByKey(PRE + "10000");
        Assertions.assertFalse(optional.isPresent());
    }

    @Order(3)
    @Test
    void testQueryJsonbByJsonbOperators() {
        List<UnloggedCache> list = unloggedCacheService.findByAttributeWithJsonbOperators("color", "blue");
        Assertions.assertFalse(list.isEmpty());
    }

    @Order(4)
    @Test
    void testQueryJsonbNestedAttributeByJsonbOperators() {
        List<UnloggedCache> list = unloggedCacheService.findByNestedAttributeWithJsonbOperators("details", "category", "electronics");
        Assertions.assertFalse(list.isEmpty());
    }

    @Order(5)
    @Test
    void testQueryJsonbByFunction() {
        /*使用 jsonb_extract_path_text 函数查询*/
        List<UnloggedCache> list = unloggedCacheService.findByAttributeWithFunction("color", "blue");
        Assertions.assertFalse(list.isEmpty());
    }

    @Order(6)
    @Test
    void testQueryJsonbNestedAttributeByFunction() {
        /*使用 jsonb_extract_path_text 函数查询*/
        List<UnloggedCache> list = unloggedCacheService.findByNestedAttributeWithFunction("details", "category", "electronics");
        Assertions.assertFalse(list.isEmpty());
    }

    @Order(7)
    @Test
    void testQueryJsonbBySpecification() {
        List<UnloggedCache> list = unloggedCacheService.findByAttributeWithSpecification("color", "blue");
        Assertions.assertFalse(list.isEmpty());
    }

}
