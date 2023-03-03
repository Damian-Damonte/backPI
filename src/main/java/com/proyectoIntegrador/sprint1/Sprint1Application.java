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

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            CategoriaServiceImp categoriaServiceImp,
            PaisRepository paisRepository,
            CiudadRepository ciudadRepository,
            ProductoServiceImp productoServiceImp,
            CaracteristicaRepository caracteristicaRepository,
            TipoPoliticaRepository tipoPoliticaRepository
    ) {
        return args -> {
            Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "http://imagen-categoria-hotel.com"));
            Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "http://imagen-categoria-hostel.com"));
            Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "http://imagen-categoria-departamento.com"));
            Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "http://imagen-categoria-Bed and breakfast.com"));

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

            Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
            Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
            Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
            Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
            Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));

            TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
            TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
            TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));

            Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(wifi, cocina, televisor));
            Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(pileta, aptoMascotas, televisor));
            Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas));

            Imagen imagen1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
            Imagen imagen2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
            Imagen imagen3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
            Imagen imagen4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");

            Set<Imagen> imagenesSet = new HashSet<>(Set.of(imagen1, imagen2, imagen3, imagen4));

            Politica politica1 = new Politica("Check-out: 10:00", normasDeLaCasa);
            Politica politica2 = new Politica("No se permiten fiestas", normasDeLaCasa);
            Politica politica3 = new Politica("Detector de humo", saludYSeguridad);
            Politica politica4 = new Politica("Deposito de seguridad", saludYSeguridad);
            Politica politica5 = new Politica("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía", politicaDeCancelacion);

            Set<Politica> politicas1 = new HashSet<>(Set.of(politica1, politica2, politica3, politica4, politica5));
            Set<Politica> politicas2 = new HashSet<>(Set.of(politica3, politica4, politica5));
            Set<Politica> politicas3 = new HashSet<>(Set.of(politica1));

            Coordenadas coordenadas = new Coordenadas(
                    new BigDecimal("-34.55044"),
                    new BigDecimal("-58.47996"));

            Producto hotel1 = new Producto(
                    "Hotel 1",
                    "Titulo de la descripcion del hotel 1",
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    hotel,
                    buenosAires,
                    caracteristicas1,
                    imagenesSet,
                    politicas1,
                    coordenadas
            );

            Producto hostel1 = new Producto(
                    "Hostel 1",
                    "Titulo de la descripcion del hostel 1",
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
                    hostel,
                    rioDeJaneiro,
                    caracteristicas2,
                    imagenesSet,
                    politicas2,
                    coordenadas
            );

            Producto departamento1 = new Producto(
                    "Departamento 1",
                    "Titulo de la descripcion del departamento 1",
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc",
                    departamento,
                    cali,
                    caracteristicas3,
                    imagenesSet,
                    politicas3,
                    coordenadas
            );

            Producto bedAndBreakfast1 = new Producto(
                    "Bed and breakfast 1",
                    "Titulo de la descripcion del Bed and breakfast 1",
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc",
                    bedAndBreakfast,
                    santiago,
                    caracteristicas1,
                    imagenesSet,
                    politicas1,
                    coordenadas
            );

            productoServiceImp.saveProducto(hotel1);
            productoServiceImp.saveProducto(hostel1);
            productoServiceImp.saveProducto(departamento1);
            productoServiceImp.saveProducto(bedAndBreakfast1);
        };
    }
}
