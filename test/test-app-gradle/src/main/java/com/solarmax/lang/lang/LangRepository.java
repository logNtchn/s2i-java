package com.solarmax.lang.lang;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LangRepository extends PagingAndSortingRepository<Lang, UUID> {

    List<Lang> findByLang(String lang);
}
