package com.galexandrov.zoo.repositories;

import com.galexandrov.zoo.models.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository <Guide,Integer> {
}
