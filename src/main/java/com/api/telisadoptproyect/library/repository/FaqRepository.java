package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, String> {
    List<Faq> findBySpecie_Id(String id);
    List<Faq> findBySpecie_IdOrderByCreatedOnAsc(String id);
}
