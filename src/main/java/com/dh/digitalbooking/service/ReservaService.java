package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.AuthenticationDto;
import com.dh.digitalbooking.model.Reserva;
import java.util.List;

public interface ReservaService {
    List<Reserva> allReserva();
    Reserva getByIdReseva(Long id);
    Reserva saveReserva(Reserva reserva, AuthenticationDto authenticationDto);
    void deleteReserva(Long id);
    Reserva updateReserva(Reserva reserva);
}
