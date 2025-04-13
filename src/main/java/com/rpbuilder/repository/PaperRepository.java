package com.rpbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpbuilder.model.Paper;

public interface PaperRepository extends JpaRepository<Paper, Long> {
}
