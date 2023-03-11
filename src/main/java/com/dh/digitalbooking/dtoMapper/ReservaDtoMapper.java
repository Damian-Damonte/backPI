package com.dh.digitalbooking.dtoMapper;

import com.dh.digitalbooking.dto.ReservaDto;
import com.dh.digitalbooking.model.Reserva;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaDtoMapper {
    public Reserva toReserva(ReservaDto dto) {
        return new Reserva(
                dto.getId(),
                dto.getCheckIn(),
                dto.getCheckOut(),
                dto.getHoraLlegada(),
                dto.getDatosExtra(),
                dto.isVacunaCovid()
        );
    }

    public ReservaDto toResevaDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getId(),
                reserva.getCheckIn(),
                reserva.getCheckOut(),
                reserva.getHoraLlegada(),
                reserva.getDatosExtra(),
                reserva.isVacunaCovid()
        );
    }

    public List<ReservaDto> toListReservaDto(List<Reserva> reservas) {
        return reservas.stream().map(this::toResevaDto).collect(Collectors.toList());
    }
}
