package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ReservaDto;
import com.dh.digitalbooking.dtoMapper.ReservaDtoMapper;
import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.repository.ReservaRepository;
import com.dh.digitalbooking.service.ReservaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaServiceImp implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaDtoMapper mapper;

    public ReservaServiceImp(ReservaRepository reservaRepository, ReservaDtoMapper mapper) {
        this.reservaRepository = reservaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ReservaDto> allReserva() {
        return mapper.toListReservaDto(reservaRepository.findAll());
    }

    @Override
    public ReservaDto getByIdReseva(Long id) {
        return null;
    }

    @Override
    public ReservaDto saveReserva(ReservaDto reservaDto) {
        Reserva reserva = mapper.toReserva(reservaDto);
        return mapper.toResevaDto(reservaRepository.save(reserva));
    }

    @Override
    public void deleteReserva(Long id) {

    }

    @Override
    public ReservaDto updateReserva(ReservaDto reservaDto) {
        return null;
    }

}
