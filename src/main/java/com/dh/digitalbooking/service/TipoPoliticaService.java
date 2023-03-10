package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.TipoPolitica;
import java.util.List;

public interface TipoPoliticaService {
    List<TipoPolitica> allTipoPolitica();
    TipoPolitica getByIdTipoPolitica(Long id);
    TipoPolitica saveTipoPolitica(TipoPolitica tipoPolitica);
    void deleteTipoPolitica(Long id);
    TipoPolitica updateTipoPolitica(TipoPolitica tipoPolitica);
}
