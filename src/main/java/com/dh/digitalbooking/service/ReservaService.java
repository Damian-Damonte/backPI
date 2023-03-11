package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.ReservaDto;
import java.util.List;

public interface ReservaService {
    List<ReservaDto> allReserva();
    ReservaDto getByIdReseva(Long id);
    ReservaDto saveReserva(ReservaDto reservaDto);
    void deleteReserva(Long id);
    ReservaDto updateReserva(ReservaDto reservaDto);
}
