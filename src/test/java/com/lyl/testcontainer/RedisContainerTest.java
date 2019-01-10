package com.lyl.testcontainer;

import com.lyl.test.cache.Cache;
import com.lyl.test.cache.RedisBackedCache;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import redis.clients.jedis.Jedis;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RedisContainerTest {

    //可以手动下载镜像：docker pull  redis:3.0.6
    @Rule
    public GenericContainer redis = new GenericContainer(    "redis:3.0.6")
            .withExposedPorts(6379);
    private Cache cache;

    @Before
    public void setUp() throws Exception {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));

        cache = new RedisBackedCache(jedis, "test");
    }

    @Test
    public void testFindingAnInsertedValue() {
        cache.put("foo", "FOO");
        Optional<String> foundObject = cache.get("foo", String.class);

        assertTrue("When an object in the cache is retrieved, it can be found",
                foundObject.isPresent());
        assertEquals("When we put a String in to the cache and retrieve it, the value is the same",
                "FOO",
                foundObject.get());
    }

    @Test
    public void testNotFindingAValueThatWasNotInserted() {
        Optional<String> foundObject = cache.get("bar", String.class);

        assertFalse("When an object that's not in the cache is retrieved, nothing is found",
                foundObject.isPresent());
    }
}
