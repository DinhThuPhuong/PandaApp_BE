package com.example.PandaCoffee.repositories;
import com.example.PandaCoffee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select a From User a where a.email = :email")
    User findUserByEmail(String email);
}