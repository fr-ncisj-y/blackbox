package com.jayplusplus.blackbox.repository;

import com.jayplusplus.blackbox.entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User info repository.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<UserInfo> findByUsername(String username);

}
