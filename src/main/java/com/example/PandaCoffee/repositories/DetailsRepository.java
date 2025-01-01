package com.example.PandaCoffee.repositories;
import com.example.PandaCoffee.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DetailsRepository extends JpaRepository<Details, Integer> {
}