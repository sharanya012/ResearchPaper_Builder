package com.rpbuilder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpbuilder.model.Paper;
import com.rpbuilder.repository.PaperRepository;

@Service
public class PaperService {

    @Autowired
    private PaperRepository repository;

    // Save paper to the database
    public void savePaper(Paper paper) {
        repository.save(paper);
    }

    // Get paper by id
    public Paper getPaperById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
