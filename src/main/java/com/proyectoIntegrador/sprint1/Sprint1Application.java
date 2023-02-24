package com.proyectoIntegrador.sprint1;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.repository.CategoriaRepository;
import com.proyectoIntegrador.sprint1.repository.PaisRepository;
import com.proyectoIntegrador.sprint1.service.imp.CategoriaServiceImp;
import com.proyectoIntegrador.sprint1.service.imp.PaisServiceImp;
import com.proyectoIntegrador.sprint1.service.imp.ProductoServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(
			CategoriaRepository categoriaRepository,
			PaisRepository paisRepository
	) {
		return args -> {
			Categoria hotel = new Categoria(
					"Hotel",
					"Descripcion de la categoria Hotel",
					"http://imagen-categoria-hotel.com");
			Categoria hostel = new Categoria(
					"Hostel",
					"Descripcion de la categoria Hostel",
					"http://imagen-categoria-hostel.com");
			Categoria departamento = new Categoria(
					"Departamento",
					"Descripcion de la categoria Departamento",
					"http://imagen-categoria-departamento.com");
			Categoria bedAndBreakfast = new Categoria(
					"Bed and breakfast",
					"Descripcion de la categoria Bed and breakfast",
					"http://imagen-categoria-Bed and breakfast.com");
			categoriaRepository.saveAll(List.of(hotel, hostel, departamento, bedAndBreakfast));

			Pais argentina = new Pais("Argentina");
			Pais chile = new Pais("Chile");
			Pais uruguay = new Pais("Uruguay");
			Pais brasil = new Pais("Brasil");
			Pais colombia = new Pais("Colombia");
			paisRepository.saveAll(List.of(argentina, chile, uruguay, brasil, colombia));



		};
	}
}
