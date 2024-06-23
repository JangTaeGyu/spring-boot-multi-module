package com.example.multimodule.infrastructure.cache;

import com.example.multimodule.core.domain.domain.post.Post;
import com.example.multimodule.core.domain.domain.post.PostCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PostCacheImpl implements PostCache {
    private static final String USER_KEY_PREFIX = "users:";

    private final RedisTemplate<String, Object> template;

    private String cacheKey(Long id) {
        return USER_KEY_PREFIX + id;
    }

    @Override
    public void save(Post post) {
        String key = cacheKey(post.getId());
        template.opsForValue().set(key, post);
        template.expire(key, 1, TimeUnit.HOURS);
    }

    @Override
    public Optional<Post> getById(Long id) {
        return Optional.ofNullable(
                (Post) template.opsForValue().get(cacheKey(id))
        );

    }

    @Override
    public void deleteById(Long id) {

    }
}
