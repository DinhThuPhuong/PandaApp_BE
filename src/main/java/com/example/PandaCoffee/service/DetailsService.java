package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.Details;
import com.example.PandaCoffee.repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    public List<Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    public Details createDetail(Details detail) {
        return detailsRepository.save(detail);
    }
}