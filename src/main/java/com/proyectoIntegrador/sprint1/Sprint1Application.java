package com.proyectoIntegrador.sprint1;

import com.proyectoIntegrador.sprint1.model.*;
import com.proyectoIntegrador.sprint1.repository.*;
import com.proyectoIntegrador.sprint1.service.imp.CategoriaServiceImp;
import com.proyectoIntegrador.sprint1.service.imp.PaisServiceImp;
import com.proyectoIntegrador.sprint1.service.imp.ProductoServiceImp;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Digital Booking API",
				version = "1.0.0",
				description = "API RESTful desarrollada por el grupo 1 de la camada 7 de Certified Tech Developer. Este proyecto forma parte del proyecto integrador del primer track."
		)
)
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(
			CategoriaRepository categoriaRepository,
			PaisRepository paisRepository,
			CiudadRepository ciudadRepository,
			ProductoRepository productoRepository,
			CaracteristicaRepository caracteristicaRepository,
			TipoPoliticaRepository tipoPoliticaRepository
	) {
		return args -> {
			Categoria hotel = categoriaRepository.save(new Categoria("Hotel", "Descripcion de la categoria Hotel", "http://imagen-categoria-hotel.com"));
			Categoria hostel = categoriaRepository.save(new Categoria("Hostel", "Descripcion de la categoria Hostel", "http://imagen-categoria-hostel.com"));
			Categoria departamento = categoriaRepository.save(new Categoria("Departamento", "Descripcion de la categoria Departamento", "http://imagen-categoria-departamento.com"));
			Categoria bedAndBreakfast = categoriaRepository.save(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "http://imagen-categoria-Bed and breakfast.com"));

			Pais argentina = paisRepository.save(new Pais("Argentina"));
			Pais chile = paisRepository.save(new Pais("Chile"));
			Pais uruguay = paisRepository.save(new Pais("Uruguay"));
			Pais brasil = paisRepository.save(new Pais("Brasil"));
			Pais colombia = paisRepository.save(new Pais("Colombia"));

			Ciudad buenosAires = ciudadRepository.save(new Ciudad("Buenos Aires", argentina));
			Ciudad santiago = ciudadRepository.save(new Ciudad("Santiago", chile));
			Ciudad monteVideo = ciudadRepository.save(new Ciudad("Montevide", uruguay));
			Ciudad rioDeJaneiro = ciudadRepository.save(new Ciudad("Rio de Janeiro", brasil));
			Ciudad cali = ciudadRepository.save(new Ciudad("Cali", colombia));

			Producto hotel1 = new Producto("Hotel 1", "Titulo de la descripcion del hotel 1" , "Descripcion del hotel 1",
					hotel, buenosAires);
			Producto hostel1 = new Producto("Hostel 1", "Titulo de la descripcion del hostel 1" , "Descripcion del hostel 1",
					hostel, rioDeJaneiro);
			Producto departamento1 = new Producto("Deparatamento 1", "Titulo de la descripcion del departamento 1" , "Descripcion del departamento 1",
					departamento, cali);
			Producto bedAndBreakfast1 = new Producto("BedAndBreakfast1 1", "Titulo de la descripcion del bedAndBreakfast1 1" , "Descripcion del bedAndBreakfast1 1",
					bedAndBreakfast, santiago);

			productoRepository.saveAll(List.of(hotel1, hostel1, departamento1, bedAndBreakfast1));

			Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
			Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
			Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
			Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
			Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));

			TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
			TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));
			TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));



		};
	}
}
