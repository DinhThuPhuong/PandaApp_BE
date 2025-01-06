package com.example.PandaCoffee.repositories;

import com.example.PandaCoffee.model.CodeConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeConfirmEmailRepository extends JpaRepository<CodeConfirmEmail,Long> {


    @Query("Select c From CodeConfirmEmail c Where c.message = :message")
    CodeConfirmEmail findACodeByToken (String message);
}