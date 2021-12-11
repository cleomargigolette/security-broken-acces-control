package com.cesararaujostudio.api.repository;

import com.cesararaujostudio.api.bases.BaseRepository;
import com.cesararaujostudio.api.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsername(String username);
}