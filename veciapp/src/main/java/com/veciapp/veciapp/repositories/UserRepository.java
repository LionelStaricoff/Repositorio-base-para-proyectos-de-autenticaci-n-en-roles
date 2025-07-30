package com.veciapp.veciapp.repositories;

import com.veciapp.veciapp.entities.UserEntity;
import com.veciapp.veciapp.security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT NEW com.veciapp.veciapp.security.UserDetailsImpl(u) FROM UserEntity u WHERE u.email = :email")
    UserDetailsImpl findByEmail(@Param("email") String email);

}
