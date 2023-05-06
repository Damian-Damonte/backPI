package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Politica;

public interface PoliticaService {
    Politica getByIdPolitica(Long id);
    boolean existsByPolicyType_id(Long id);
}
