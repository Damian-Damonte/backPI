package com.dh.digitalbooking;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.model.*;
import com.dh.digitalbooking.repository.*;
import com.dh.digitalbooking.service.imp.CategoriaServiceImp;
import com.dh.digitalbooking.service.imp.ProductoServiceImp;
import com.dh.digitalbooking.service.imp.PuntuacionServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DigitalbookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalbookingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CategoriaServiceImp categoriaServiceImp,
            PaisRepository paisRepository,
            CiudadRepository ciudadRepository,
            ProductoServiceImp productoServiceImp,
            CaracteristicaRepository caracteristicaRepository,
            TipoPoliticaRepository tipoPoliticaRepository,
            RoleRepository roleRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            PuntuacionServiceImp puntuacionServiceImp
    ) {
        return args -> {
            Rol rolUser = roleRepository.save(new Rol("ROLE_USER"));
            Rol rolAdmin = roleRepository.save(new Rol("ROLE_ADMIN"));

            Usuario usuarioAdmin = usuarioRepository.save(new Usuario(
                    "admin",
                    "admin",
                    "admin@gmail.com",
                    passwordEncoder.encode("admin"),
                    rolAdmin
            ));

            Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/categorias/catHotel.jpg"));
            Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/categorias/catHostel.jpg"));
            Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/categorias/catDepartamento.jpg"));
            Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/categorias/CatBedBreakfast.jpg"));

            Pais argentina = paisRepository.save(new Pais("Argentina"));
            Pais italia = paisRepository.save(new Pais("Italia"));
            Pais brasil = paisRepository.save(new Pais("Brasil"));
            Pais colombia = paisRepository.save(new Pais("Colombia"));
            Pais estadosUnidos = paisRepository.save(new Pais("Estados Unidos"));
            Pais espania = paisRepository.save(new Pais("España"));
            Pais cuba = paisRepository.save(new Pais("Cuba"));
            Pais japon = paisRepository.save(new Pais("Japón"));

            Ciudad puertoIguazu = ciudadRepository.save(new Ciudad("Puerto Iguazú", argentina));
            Ciudad sanMartindelosAndes = ciudadRepository.save(new Ciudad("San Martín de los Andes", argentina));
            Ciudad elCalafate = ciudadRepository.save(new Ciudad("El Calafate", argentina));
            Ciudad neuquen = ciudadRepository.save(new Ciudad("Neuquen", argentina));
            Ciudad barcelona = ciudadRepository.save(new Ciudad("Barcelona", espania));
            Ciudad grado = ciudadRepository.save(new Ciudad("Grado", italia));
            Ciudad ushuaia = ciudadRepository.save(new Ciudad("Ushuaia", argentina));
            Ciudad buzios = ciudadRepository.save(new Ciudad("Buzios", brasil));
            Ciudad miami = ciudadRepository.save(new Ciudad("Miami", estadosUnidos));
            Ciudad santaMa = ciudadRepository.save(new Ciudad("Santa María", colombia));
            Ciudad laHabana = ciudadRepository.save(new Ciudad("La Habana", cuba));
            Ciudad newyork = ciudadRepository.save(new Ciudad("New York", estadosUnidos));
            Ciudad tokio = ciudadRepository.save(new Ciudad("Tokio", japon));
            Ciudad buenosaires = ciudadRepository.save(new Ciudad("Buenos Aires", argentina));
            Ciudad roma = ciudadRepository.save(new Ciudad("Roma", italia));
            Ciudad florencia = ciudadRepository.save(new Ciudad("Florencia", italia));

            Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
            Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
            Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
            Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
            Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));
            Caracteristica parrilla = caracteristicaRepository.save(new Caracteristica("Parrilla"));
            Caracteristica lavadora = caracteristicaRepository.save(new Caracteristica("Lavadora"));
            Caracteristica banioPrivado = caracteristicaRepository.save(new Caracteristica("Baño privado"));
            Caracteristica noFumar = caracteristicaRepository.save(new Caracteristica("No fumar"));
            Caracteristica vistaCiudad = caracteristicaRepository.save(new Caracteristica("Vistas a la ciudad"));

            Caracteristica desayuno = caracteristicaRepository.save(new Caracteristica("Desayuno incluido"));
            Caracteristica calefaccion = caracteristicaRepository.save(new Caracteristica("Calefaccion"));
            Caracteristica gimnasio = caracteristicaRepository.save(new Caracteristica("Gimnasio"));
            Caracteristica recepcion24 = caracteristicaRepository.save(new Caracteristica("Recepcion 24hs"));
            Caracteristica spa = caracteristicaRepository.save(new Caracteristica("Spa"));
            Caracteristica seguridad24 = caracteristicaRepository.save(new Caracteristica("Seguridad las 24hs"));
            Caracteristica bicicletas = caracteristicaRepository.save(new Caracteristica("Alquiler de bicicletas"));
            Caracteristica conferencias = caracteristicaRepository.save(new Caracteristica("Sala de conferencias"));
            Caracteristica restaurant = caracteristicaRepository.save(new Caracteristica("Restaurant"));
            Caracteristica playa = caracteristicaRepository.save(new Caracteristica("Acceso a la playa"));
            Caracteristica infantil = caracteristicaRepository.save(new Caracteristica("Zona infantil"));
            Caracteristica bar = caracteristicaRepository.save(new Caracteristica("Bar"));
            Caracteristica cajaFuerte = caracteristicaRepository.save(new Caracteristica("Caja fuerte"));
            Caracteristica minibar = caracteristicaRepository.save(new Caracteristica("Minibar"));
            Caracteristica chimenea = caracteristicaRepository.save(new Caracteristica("Chimenea"));

            TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
            TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
            TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));

            Politica politica1 = new Politica(
                    "Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus\n" +
                            "Detector de humo\n" +
                            "Depósito de seguridad\n" +
                            "Camaras de seguridad\n" +
                            "Matafuegos\n"
                    , saludYSeguridad);

            Politica politica2 = new Politica(
                    "Check-out: 22:00\n"+
                            "No se permiten fiestas\n" +
                            "No fumar\n" +
                            "Check-in: 10:00",
                    normasDeLaCasa
            );

            Politica politica3 = new Politica(
                    "Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadía",
                    politicaDeCancelacion
            );

            Set<Politica> pleBatiment = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> philtongarden = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> plaBrisa = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pnuovoMiami = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> purbanyHostel = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pvilladEste = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> plaSoberanaHosteria = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> putaka = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> porillaMansa = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> plaReservaVirginLodge = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> ppousadaBonfim = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pnacionaldeCuba = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pnineHours= new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> plantica = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pantico = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pzero = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> papaTokio = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pakihabara = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pcerulean = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> pmandarin = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> priari = new HashSet<>(Set.of(politica1, politica2, politica3));
            Set<Politica> phigashiGinza = new HashSet<>(Set.of(politica1, politica2, politica3));


            Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(cocina, televisor, desayuno, bar, noFumar));
            Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(cajaFuerte, parrilla, lavadora, pileta, aptoMascotas, seguridad24, wifi));
            Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas, banioPrivado, vistaCiudad, parrilla, lavadora));
            Set<Caracteristica> caracteristicas4 = new HashSet<>(Set.of(desayuno, calefaccion, gimnasio, recepcion24, spa, noFumar, bicicletas, conferencias, restaurant));
            Set<Caracteristica> caracteristicas5 = new HashSet<>(Set.of(playa, infantil, bar, cajaFuerte, seguridad24, pileta, wifi, aptoMascotas, televisor));
            Set<Caracteristica> caracteristicas6 = new HashSet<>(Set.of( desayuno, bicicletas, televisor, playa, noFumar, aptoMascotas, restaurant));


            //iakihabara
            Imagen akihabara1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/102923328.jpg");
            Imagen akihabara2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/102927809.jpg");
            Imagen akihabara3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/102929333.jpg");
            Imagen akihabara4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/102933385.jpg");
            Imagen akihabara5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/148149172.jpg");
            Imagen akihabara6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/akihabara/91528717.jpg", 1);


            //iapaTokio
            Imagen apaTokio1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/267561410.jpg");
            Imagen apaTokio2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/267562611.jpg");
            Imagen apaTokio3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/267562762.jpg");
            Imagen apaTokio4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/267562958.jpg");
            Imagen apaTokio5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/268256913.jpg");
            Imagen apaTokio6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/351263389.jpg");
            Imagen apaTokio7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/423018626.jpg");
            Imagen apaTokio8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/423018785.jpg");
            Imagen apaTokio9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/423019058.jpg", 1);
            Imagen apaTokio10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/ApaTokio/425037658.jpg");


            //zero
            Imagen zero1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/317878776.jpg", 1);
            Imagen zero2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/335358534.jpg");
            Imagen zero3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/335358537.jpg");
            Imagen zero4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/379975372.jpg");
            Imagen zero5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/379975401.jpg");
            Imagen zero6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/379975564.jpg");
            Imagen zero7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/379975789.jpg");
            Imagen zero8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/379978624.jpg");
            Imagen zero9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/380007838.jpg");
            Imagen zero10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/380017718.jpg");


            //antico
            Imagen antico1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/158951841.jpg");
            Imagen antico2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/44251868.jpg", 1);
            Imagen antico3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/44252068.jpg");
            Imagen antico4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/59144935.jpg");
            Imagen antico5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/59144968.jpg");
            Imagen antico6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/59203163.jpg");

            //L'antica
            Imagen lantica1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/278184800.jpg");
            Imagen lantica2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/259294104.jpg");
            Imagen lantica3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/256045.webp");
            Imagen lantica4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/255284.webp");
            Imagen lantica5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/243303.webp");
            Imagen lantica6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/lantica/173662.webp");

            //LeBatiment
            Imagen leBatiment1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222877138.jpg");
            Imagen leBatiment2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222877504.jpg", 1);
            Imagen leBatiment3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222879984.jpg");
            Imagen leBatiment4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222890172.jpg");
            Imagen leBatiment5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222890182.jpg");
            Imagen leBatiment6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/22384908.jpg");
            Imagen leBatiment7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/54304600.jpg");

            //hiltongarden
            Imagen hiltongarden1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249632667.jpg", 1);
            Imagen hiltongarden2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249633069.jpg");
            Imagen hiltongarden3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249634973.jpg");
            Imagen hiltongarden4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249871836.jpg");
            Imagen hiltongarden5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/313379046.jpg");

            //laBrisa
            Imagen laBrisa1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/151251211.jpg", 1);
            Imagen laBrisa2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/151251594.jpg");
            Imagen laBrisa3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/207644787.jpg");
            Imagen laBrisa4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/207876546.jpg");
            Imagen laBrisa5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/208219220.jpg");
            Imagen laBrisa6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/215387289.jpg");
            Imagen laBrisa7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/215387290.jpg");
            Imagen laBrisa8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/243354046.jpg");

            //nuovoMiami
            Imagen nuovoMiami1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024485.jpg");
            Imagen nuovoMiami2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024528.jpg");
            Imagen nuovoMiami3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024541.jpg");
            Imagen nuovoMiami4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024544.jpg");
            Imagen nuovoMiami5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024601.jpg");
            Imagen nuovoMiami6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024608.jpg", 1);
            Imagen nuovoMiami7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024704.jpg");
            Imagen nuovoMiami8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024805.jpg");

            //villadEste
            Imagen villadEste1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367990746.jpg");
            Imagen villadEste2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367990812.jpg");
            Imagen villadEste3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367991438.jpg");
            Imagen villadEste4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367991543.jpg", 1);
            Imagen villadEste5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367992879.jpg");
            Imagen villadEste6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/92126852.jpg");
            Imagen villadEste7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/92133640.jpg");

            //urbanyHostel
            Imagen urbanyHostel1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/153802975.jpg");
            Imagen urbanyHostel2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/153802993.jpg");
            Imagen urbanyHostel3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382378214.jpg");
            Imagen urbanyHostel4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382378367.jpg");
            Imagen urbanyHostel5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382378386.jpg");
            Imagen urbanyHostel6 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382380792.jpg");
            Imagen urbanyHostel7 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382380918.jpg");
            Imagen urbanyHostel8 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381536.jpg", 1);
            Imagen urbanyHostel9 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381552.jpg");
            Imagen urbanyHostel10 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381640.jpg");

            //laSoberanaBed&Breakfast
            Imagen laSoberanaHosteria1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128347938.jpg");
            Imagen laSoberanaHosteria2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128348420.jpg");
            Imagen laSoberanaHosteria3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128352007.jpg");
            Imagen laSoberanaHosteria4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/156003329.jpg");
            Imagen laSoberanaHosteria5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/216458663.jpg", 1);
            Imagen laSoberanaHosteria6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/216458911.jpg");

            //utaka
            Imagen utaka1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/221795404.jpg");
            Imagen utaka2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227765817.jpg");
            Imagen utaka3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227765829.jpg");
            Imagen utaka4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227766702.jpg");
            Imagen utaka5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227766715.jpg");
            Imagen utaka6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/248803298.jpg");
            Imagen utaka7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/336734755.jpg", 1);

            //orillaMansa
            Imagen orillaMansa1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916227.jpg");
            Imagen orillaMansa2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916745.jpg");
            Imagen orillaMansa3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916952.jpg");
            Imagen orillaMansa4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726088.jpg");
            Imagen orillaMansa5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726101.jpg");
            Imagen orillaMansa6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726961.jpg", 1);
            Imagen orillaMansa7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440727282.jpg");
            Imagen orillaMansa8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440727370.jpg");

            //laReservaVirginLodge
            Imagen laReservaVirginLodge1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/190987055.jpg");
            Imagen laReservaVirginLodge2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/190987075.jpg");
            Imagen laReservaVirginLodge3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/190987145.jpg");
            Imagen laReservaVirginLodge4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/295815330.jpg");
            Imagen laReservaVirginLodge5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/295821941.jpg");
            Imagen laReservaVirginLodge6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/36817791.jpg");
            Imagen laReservaVirginLodge7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/37579647.jpg");
            Imagen laReservaVirginLodge8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/37579676.jpg");
            Imagen laReservaVirginLodge9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/37579995.jpg", 1);
            Imagen laReservaVirginLodge10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/38312132.jpg");

            //bonFim
            Imagen bonFim1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/10484160.jpg");
            Imagen bonFim2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/16785629.jpg");
            Imagen bonFim3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/228280.webp");
            Imagen bonFim4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/63895518.jpg");
            Imagen bonFim5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/93812496.jpg", 1);

            //nacionalDeCuba
            Imagen nacionaldeCuba1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/182553824.jpg");
            Imagen nacionaldeCuba2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/182574494.jpg");
            Imagen nacionaldeCuba3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400798721.jpg", 1);
            Imagen nacionaldeCuba4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400802292.jpg");
            Imagen nacionaldeCuba5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400802332.jpg");

            //interContinental
            Imagen interContinental1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/260331427.jpg");
            Imagen interContinental2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341425558.jpg");
            Imagen interContinental3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341436162.jpg");
            Imagen interContinental4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341439879.jpg");
            Imagen interContinental5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341466879.jpg", 1);
            Imagen interContinental6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341466875.jpg");
            Imagen interContinental7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/359664047.jpg");
            Imagen interContinental8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/419472171.jpg");

            //inineHours
            Imagen nineHours1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/247091310.jpg", 1);
            Imagen nineHours2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664182.jpg");
            Imagen nineHours3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664193.jpg");
            Imagen nineHours4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664204.jpg");
            Imagen nineHours5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664209.jpg");
            Imagen nineHours6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664226.jpg");
            Imagen nineHours7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664229.jpg");
            Imagen nineHours8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/338517809.jpg");
            Imagen nineHours9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/338517816.jpg");
            Imagen nineHours10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/365167162.jpg");

            //cerulean
            Imagen cerulean1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/136498098.jpg");
            Imagen cerulean2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/158462606.jpg");
            Imagen cerulean3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/163449048.jpg");
            Imagen cerulean4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/163449119.jpg");
            Imagen cerulean5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/188219952.jpg");
            Imagen cerulean6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/197566623.jpg");
            Imagen cerulean7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/238504753.jpg");
            Imagen cerulean8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/238505124.jpg");
            Imagen cerulean9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/287460269.jpg");
            Imagen cerulean10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/cerulean/287460533.jpg", 1);


            //mandarin
            Imagen mandarin1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/177035322.jpg");
            Imagen mandarin2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/233111733.jpg");
            Imagen mandarin3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/233112283.jpg");
            Imagen mandarin4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/233112480.jpg");
            Imagen mandarin5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/239889460.jpg");
            Imagen mandarin6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/239899002.jpg", 1);
            Imagen mandarin7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/241804125.jpg");
            Imagen mandarin8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/250322928.jpg");
            Imagen mandarin9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/mandarin/250323305.jpg");


            //iriari
            Imagen riari1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/12923368.jpg", 1);
            Imagen riari2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/32212853.jpg");
            Imagen riari3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/32220044.jpg");
            Imagen riari4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/32220119.jpg");
            Imagen riari5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/446123361.jpg");
            Imagen riari6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/Riari/446123363.jpg");


            //higashiGinza
            Imagen higashiGinza1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/340823300.jpg");
            Imagen higashiGinza2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/340823829.jpg");
            Imagen higashiGinza3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/340823911.jpg");
            Imagen higashiGinza4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/342286608.jpg");
            Imagen higashiGinza5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/342349570.jpg");
            Imagen higashiGinza6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/347373486.jpg");
            Imagen higashiGinza7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/347374544.jpg");
            Imagen higashiGinza8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/446039267.jpg");
            Imagen higashiGinza9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/446039323.jpg");
            Imagen higashiGinza10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/446702783.jpg", 1);
            Imagen higashiGinza11 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/higashi/446919588.jpg");


            //Set de imagenes por producto nombrarlos i+el nombre del producto
            Set<Imagen> iantico= new HashSet<>(Set.of(antico1, antico2, antico3, antico4, antico5, antico6));
            Set<Imagen> ilantica= new HashSet<>(Set.of(lantica1, lantica2, lantica3, lantica4, lantica5, lantica6));
            Set<Imagen> iLeBatiment= new HashSet<>(Set.of(leBatiment1, leBatiment2, leBatiment3, leBatiment4, leBatiment5, leBatiment6, leBatiment7));
            Set<Imagen> ihiltongarden = new HashSet<>(Set.of(hiltongarden1, hiltongarden2, hiltongarden3, hiltongarden4, hiltongarden5));
            Set<Imagen> ilaBrisa = new HashSet<>(Set.of(laBrisa1, laBrisa2, laBrisa3, laBrisa4, laBrisa5, laBrisa6, laBrisa7, laBrisa8));
            Set<Imagen> inuovoMiami = new HashSet<>(Set.of(nuovoMiami1, nuovoMiami2, nuovoMiami3, nuovoMiami4, nuovoMiami5, nuovoMiami6, nuovoMiami7, nuovoMiami8));
            Set<Imagen> ivilladEste = new HashSet<>(Set.of(villadEste1, villadEste2, villadEste3, villadEste4, villadEste5, villadEste6, villadEste7));
            Set<Imagen> iurbanyHostel = new HashSet<>(Set.of(urbanyHostel1, urbanyHostel2, urbanyHostel3, urbanyHostel4, urbanyHostel5, urbanyHostel6, urbanyHostel7, urbanyHostel8, urbanyHostel10));
            Set<Imagen> ilaSoberanaHosteria = new HashSet<>(Set.of(laSoberanaHosteria1, laSoberanaHosteria2, laSoberanaHosteria3, laSoberanaHosteria4, laSoberanaHosteria5, laSoberanaHosteria6));
            Set<Imagen> iutaka = new HashSet<>(Set.of(utaka1, utaka2, utaka3, utaka4, utaka5, utaka6, utaka7));
            Set<Imagen> iorillaMansa= new HashSet<>(Set.of(orillaMansa1, orillaMansa2, orillaMansa3, orillaMansa4, orillaMansa5, orillaMansa6, orillaMansa7, orillaMansa8));
            Set<Imagen> ilaReservaVirginLodge= new HashSet<>(Set.of(laReservaVirginLodge1, laReservaVirginLodge2, laReservaVirginLodge3, laReservaVirginLodge4, laReservaVirginLodge5, laReservaVirginLodge6, laReservaVirginLodge7, laReservaVirginLodge8, laReservaVirginLodge9, laReservaVirginLodge10));
            Set<Imagen> ipousadaBonfim= new HashSet<>(Set.of(bonFim1, bonFim2, bonFim3, bonFim4, bonFim5));
            Set<Imagen> inacionaldeCuba= new HashSet<>(Set.of(nacionaldeCuba1, nacionaldeCuba2, nacionaldeCuba3, nacionaldeCuba4, nacionaldeCuba5));
            Set<Imagen> iinterContinental= new HashSet<>(Set.of(interContinental1, interContinental2, interContinental3, interContinental4, interContinental5, interContinental6, interContinental7, interContinental8));
            Set<Imagen> ininehours= new HashSet<>(Set.of(nineHours1, nineHours2, nineHours3, nineHours4, nineHours5, nineHours6, nineHours7, nineHours8, nineHours9, nineHours10));
            Set<Imagen> izero= new HashSet<>(Set.of(zero1, zero2, zero3, zero4, zero5, zero6, zero7, zero8, zero9, zero10));
            Set<Imagen> iapaTokio= new HashSet<>(Set.of(apaTokio1, apaTokio2, apaTokio3, apaTokio4, apaTokio5, apaTokio6, apaTokio7, apaTokio8, apaTokio9, apaTokio10));
            Set<Imagen> iakihabara= new HashSet<>(Set.of(akihabara1, akihabara2, akihabara3, akihabara4, akihabara5, akihabara6));
            Set<Imagen> icerulean= new HashSet<>(Set.of(cerulean1, cerulean2, cerulean3, cerulean4, cerulean5, cerulean6, cerulean7, cerulean8, cerulean9, cerulean10));
            Set<Imagen> imandarin= new HashSet<>(Set.of(mandarin1, mandarin2, mandarin3, mandarin4, mandarin5, mandarin6, mandarin7, mandarin8, mandarin9));
            Set<Imagen> iriari= new HashSet<>(Set.of(riari1, riari2, riari3, riari4, riari5, riari6 ));
            Set<Imagen> ihigashiGinza= new HashSet<>(Set.of(higashiGinza1, higashiGinza2, higashiGinza3, higashiGinza4, higashiGinza5, higashiGinza6, higashiGinza7, higashiGinza8, higashiGinza9, higashiGinza10, higashiGinza11));



            //coordenadas NombreHotel+c
            Coordenadas hiltongardenc = new Coordenadas(
                    new BigDecimal("-38.941697761424834"),
                    new BigDecimal("-68.05962964717754"));

            Coordenadas laBrisaLocaHostel = new Coordenadas(
                    new BigDecimal("11.24500468403564"),
                    new BigDecimal("-74.21162384247455"));

            Coordenadas nuoMiamiApartaments = new Coordenadas(
                    new BigDecimal("25.734365891053194"),
                    new BigDecimal("-80.23622998084356"));

            Coordenadas urbanyHostelc = new Coordenadas(
                    new BigDecimal("41.407725926268164"),
                    new BigDecimal("2.1863069709469762"));

            Coordenadas villadEstec = new Coordenadas(
                    new BigDecimal("45.84856625461699"),
                    new BigDecimal("9.085044041108125"));

            Coordenadas laSoberanaHosteriac = new Coordenadas(
                    new BigDecimal("-50.31369487360898"),
                    new BigDecimal("-72.31902873315147"));

            Coordenadas utakac = new Coordenadas(
                    new BigDecimal("-54.809057600421816"),
                    new BigDecimal("-68.32123219840852"));

            Coordenadas orillaMansac = new Coordenadas(
                    new BigDecimal("-40.15945137990247"),
                    new BigDecimal("-71.35977835657081"));

            Coordenadas laReservaVirginLodgec = new Coordenadas(
                    new BigDecimal("-25.60489625556165"),
                    new BigDecimal("-54.55083371465647"));

            Coordenadas pousadaBonfimc = new Coordenadas(
                    new BigDecimal("-22.74533965556891"),
                    new BigDecimal("-41.881199656985"));

            Coordenadas nacionaldeCubac = new Coordenadas(
                    new BigDecimal("23.1408"),
                    new BigDecimal("-82.3889"));

            Coordenadas interContinentalc = new Coordenadas(
                    new BigDecimal("40.7553"),
                    new BigDecimal("-73.9861"));

            Coordenadas nineHoursc = new Coordenadas(
                    new BigDecimal("35.6664"),
                    new BigDecimal("139.7504"));

            Coordenadas rafflesc = new Coordenadas(
                    new BigDecimal("35.6664"),
                    new BigDecimal("139.7504"));

            Coordenadas leBristolc = new Coordenadas(
                    new BigDecimal("25.2048"),
                    new BigDecimal("139.7504"));

            Coordenadas ritzCarltonc = new Coordenadas(
                    new BigDecimal("35.6664"),
                    new BigDecimal("55.2708"));

            Coordenadas burjAlArabc = new Coordenadas(
                    new BigDecimal("25.1412"),
                    new BigDecimal("55.1852"));

            Coordenadas uniquec = new Coordenadas(
                    new BigDecimal("-23.5814"),
                    new BigDecimal("46.6774"));

            Coordenadas riaric = new Coordenadas(
                    new BigDecimal("41.89318764064088"),
                    new BigDecimal("12.464547415839215"));

            Coordenadas lanticac = new Coordenadas(
                    new BigDecimal("41.90205777396797"),
                    new BigDecimal("12.472714768447537"));

            Coordenadas leBatimentc = new Coordenadas(
                    new BigDecimal("-34.59636804201318"),
                    new BigDecimal("-58.40801951557742"));

            Coordenadas anticoc = new Coordenadas(
                    new BigDecimal("43.768165137628806"),
                    new BigDecimal("11.254516863816157"));

            Coordenadas zeroc = new Coordenadas(
                    new BigDecimal("41.400409870994125"),
                    new BigDecimal("2.205703437638464"));

            Coordenadas apaTokioc = new Coordenadas(
                    new BigDecimal("35.696109036046906"),
                    new BigDecimal("139.70259149813182"));

            Coordenadas akihabarac = new Coordenadas(
                    new BigDecimal("35.6973835392973"),
                    new BigDecimal("139.77405384055245"));

            Coordenadas ceruleanc = new Coordenadas(
                    new BigDecimal("35.65656227250088"),
                    new BigDecimal("139.69938911841297"));

            Coordenadas mandarinc = new Coordenadas(
                    new BigDecimal("35.68714139008886"),
                    new BigDecimal("139.7730927195066"));

            Coordenadas higashiGinzac = new Coordenadas(
                    new BigDecimal("35.66431125601999"),
                    new BigDecimal("139.77144674967388"));








            Producto zero = new Producto();
            zero.setTitulo("Hotel SB Diagonal Zero Barcelona");
            zero.setTituloDescripcion("Alojate en el corazón de Florencia");
            zero.setDescripcion("El Hotel SB Diagonal Zero Barcelona se encuentra en Barcelona, frente al centro internacional de convenciones y el Auditori Fòrum. Cuenta con spa, centro de fitness, piscina panorámica de temporada y solárium en la azotea.\n" +
                    "\n" +
                    "El centro de fitness es de acceso gratuito. El acceso al spa es solo para mayores de 14 años e incluye baño turco y sauna finlandesa. Se ofrecen tratamientos de belleza y masajes por un suplemento.\n" +
                    "\n" +
                    "Las habitaciones del Hotel SB Diagonal Zero Barcelona están insonorizadas y disponen de aire acondicionado, TV vía satélite de pantalla plana, conexión WiFi gratuita, caja fuerte para ordenador portátil y soporte para iPod.\n" +
                    "\n" +
                    "Los huéspedes disponen de utensilios de planchado, set de té y café, hervidor de agua, sistema de pantalla táctil para regular la luz y la temperatura, y baño privado con secador de pelo. Se facilitan albornoces y zapatillas bajo petición.");
            zero.setDireccion("Via De' Brunelleschi, 5, Uffizi, 50122 Florencia, Italia");
            zero.setPrecioPorNoche(new BigDecimal("39200.00"));
            zero.setCategoria(hostel);
            zero.setCiudad(barcelona);
            zero.setCaracteristicas(caracteristicas1);
            zero.setImagenes(izero);
            zero.setPoliticas(pzero);
            zero.setCoordenadas(zeroc);
            productoServiceImp.saveProducto(zero);

            Producto antico = new Producto();
            antico.setTitulo("L'antica Locanda Dell'Orso");
            antico.setTituloDescripcion("Alojate en el corazón de Florencia");
            antico.setDescripcion("El Antico Centro Suite ofrece alojamientos modernos con conexión wifi gratis en el centro de Florencia, junto a la Piazza della Repubblica y a 2 minutos a pie de la catedral de Santa María del Fiore.\n" +
                    "\n" +
                    "Las habitaciones están insonorizadas y cuentan con suelo de parquet, aire acondicionado, TV, zona de estar y minibar. El baño privado incluye secador de pelo y artículos de aseo gratuitos.\n" +
                    "\n" +
                    "El Antico Centro Suite se encuentra a 350 metros del Ponete Vecchio, a 4 minutos a pie de la Galería Uffizi y a 900 metros de la estación de tren de Florencia Santa Maria Novella.\n" +
                    "\n" +
                    "Nuestros clientes dicen que esta parte de Florencia es su favorita, según los comentarios independientes. ");
            antico.setDireccion("Via De' Brunelleschi, 5, Uffizi, 50122 Florencia, Italia");
            antico.setPrecioPorNoche(new BigDecimal("52000.00"));
            antico.setCategoria(bedAndBreakfast);
            antico.setCiudad(florencia);
            antico.setCaracteristicas(caracteristicas2);
            antico.setImagenes(iantico);
            antico.setPoliticas(pantico);
            antico.setCoordenadas(anticoc);
            productoServiceImp.saveProducto(antico);

            Producto riari = new Producto();
            riari.setTitulo("Villa Riari Garden");
            riari.setTituloDescripcion("Alojate en el corazón de Roma");
            riari.setDescripcion("La Villa Riari Garden, situada en el singular barrio romano de Trastevere, cuenta con un jardín privado con mesas y sillas.\n" +
                    "\n" +
                    "Las habitaciones están amuebladas con sencillez, son funcionales y disponen de suelo de baldosa, escritorio y lavamanos. Algunas tienen baño compartido. Los huéspedes pueden relajarse en un salón con conexión a internet gratuita y TV LCD.\n" +
                    "\n" +
                    "El personal de la Riari Villa está disponible las 24 horas y proporciona servicio de información turística. La conexión Wi-Fi es gratuita.\n" +
                    "\n" +
                    "El establecimiento se encuentra a 10 minutos a pie de la animada plaza Trilussa, que está rodeada de enotecas, cafeterías y restaurantes. La basílica de San Pedro y la plaza Navona están a 1,5 km.\n");
            riari.setDireccion("Via Dei Riari 44, Trastevere, 00165 Roma, Italia");
            riari.setPrecioPorNoche(new BigDecimal("46400.00"));
            riari.setCategoria(bedAndBreakfast);
            riari.setCiudad(roma);
            riari.setCaracteristicas(caracteristicas3);
            riari.setImagenes(iriari);
            riari.setPoliticas(priari);
            riari.setCoordenadas(riaric);
            productoServiceImp.saveProducto(riari);

            Producto leBatiment = new Producto();
            leBatiment.setTitulo("Le Batiment");
            leBatiment.setTituloDescripcion("Departamentos confortables en el corazón de la ciudad");
            leBatiment.setDescripcion("El alojamiento Le Batiment ofrece departamentos confortables con wifi gratis en el exclusivo barrio de Recoleta, en el centro de Buenos Aires. Se halla a solo 1 calle de la avenida Córdoba.\n" +
                    "\n" +
                    "Los departamentos del Batiment tienen cocina totalmente equipada con microondas y heladera. Además, disponen de zona de comedor y zona de estar con TV por cable. El servicio de limpieza no está incluido, pero puede solicitarse por un adicional.\n" +
                    "\n" +
                    "El alojamiento Le Batiment está cerca de varios restaurantes, bares y teatros. Hay una cafetería a 120 metros donde se puede desayunar.\n" +
                    "\n" +
                    "Los departamentos están situados a 5 calles de la avenida comercial Santa Fe y a poca distancia a pie de la estación de subte más cercana, que ofrece enlaces con los principales lugares de interés de la ciudad.\n");
            leBatiment.setDireccion("Paraguay 2862, Recoleta, 1425 Buenos Aires, Argentina");
            leBatiment.setPrecioPorNoche(new BigDecimal("55675.00"));
            leBatiment.setCategoria(departamento);
            leBatiment.setCiudad(buenosaires);
            leBatiment.setCaracteristicas(caracteristicas4);
            leBatiment.setImagenes(iLeBatiment);
            leBatiment.setPoliticas(pleBatiment);
            leBatiment.setCoordenadas(leBatimentc);
            productoServiceImp.saveProducto(leBatiment);

            Producto hiltongarden = new Producto();
            hiltongarden.setTitulo("Hilton Garden Hotel");
            hiltongarden.setTituloDescripcion("Un espacio ideal para eventos empresariales");
            hiltongarden.setDescripcion("El Hilton Garden Neuquen está muy bien situado en el distrito de Mongkok, a solo 7 minutos a pie de la estación MTR de Mongkok, rodeado de populares zonas comerciales y de compras y de lugares de interés.\n" +
                    "\n" +
                    "El Hilton Garden Inn Hong Kong Mongkok está a 1 km del mercado nocturno de Temple Street, a 700 metros del Ladies Market y a 24 km del aeropuerto internacional de Hong Kong.\n" +
                    "\n" +
                    "Hay una recepción 24 horas, instalaciones para reuniones y salones de banquetes sin pilares, un espacio ideal para bodas, reuniones sociales y conferencias.\n" +
                    "\n" +
                    "El restaurante del establecimiento sirve una excelente selección de platos de cocina occidental y china.\n");
            hiltongarden.setDireccion("Sales Torres 98-1, Q8300ASV Neuquén");
            hiltongarden.setPrecioPorNoche(new BigDecimal("72500.00"));
            hiltongarden.setCategoria(hotel);
            hiltongarden.setCiudad(neuquen);
            hiltongarden.setCaracteristicas(caracteristicas5);
            hiltongarden.setImagenes(ihiltongarden);
            hiltongarden.setPoliticas(philtongarden);
            hiltongarden.setCoordenadas(hiltongardenc);
            productoServiceImp.saveProducto(hiltongarden);

            Producto laBrisa = new Producto();
            laBrisa.setTitulo("La Brisa Loca");
            laBrisa.setTituloDescripcion("Cervezas, sol y amigos");
            laBrisa.setDescripcion("La Brisa Loca está situada en una mansión republicana de mas de 100 años que ha sido restaurada para resaltar su clásico encanto colonial caribeño. La gran mansión de techos altos, con sus dieciocho dormitorios y suites, tiene capacidad para 90 personas. Con el estilo tradicional y esquemas de color de la auténtica arquitectura del Caribe colombiano, disfrutarás y apreciarás los aspectos más destacados de la región, no solo en la ciudad colonial más antigua de América, sino también aca en tu hospedaje. En La Brisa Loca, puedes explorar y descubrir Colombia con comodidad y estilo ... pero luego debes enfrentar la tarea más grande de todas ... ¡dejar el hostal!");
            laBrisa.setDireccion("Sales Torres 98-1, Q8300ASV Neuquén");
            laBrisa.setPrecioPorNoche(new BigDecimal("36000.00"));
            laBrisa.setCategoria(hostel);
            laBrisa.setCiudad(santaMa);
            laBrisa.setCaracteristicas(caracteristicas6);
            laBrisa.setImagenes(ilaBrisa);
            laBrisa.setPoliticas(plaBrisa);
            laBrisa.setCoordenadas(laBrisaLocaHostel);
            productoServiceImp.saveProducto(laBrisa);

            Producto nuovoMiami = new Producto();
            nuovoMiami.setTitulo("Nuovo Miami Apartaments at Coconut Grove");
            nuovoMiami.setTituloDescripcion("Alquiler de lujo en pleno Coconut Grove");
            nuovoMiami.setDescripcion("El CoconutGrove - Luxurious Vacation Rentals in Coconut Grove ofrece sala de fitness, sauna y alojamiento con aire acondicionado y WiFi gratuita en Miami, a 600 metros del centro comercial Cocowalk. Hay aparcamiento privado.\n" +
                    "\n" +
                    "El apartamento dispone de TV de pantalla plana y baño privado con artículos de aseo gratuitos, secador de pelo y bañera. Hay nevera, microondas y cafetera.\n" +
                    "\n" +
                    "El iCoconutGrove - Luxurious Vacation Rentals in Coconut Grove alberga un restaurante que sirve cocina americana, peruana e internacional. También se pueden solicitar opciones vegetarianas.\n" +
                    "\n" +
                    "También hay bañera de hidromasaje.\n" +
                    "\n" +
                    "El ICoconutGrove - Luxurious Vacation Rentals in Coconut Grove alberga un solárium.\n" +
                    "\n" +
                    "El apartamento está a 4,4 km del Museo Vizcaya y a 5,7 km de la Universidad de Miami. El aeropuerto más cercano es el aeropuerto internacional de Miami, ubicado a 10 km del iCoconutGrove - Luxurious Vacation Rentals in Coconut Grove. ");
            nuovoMiami.setDireccion("2889 McFarlane Rd, Coconut Grove, Miami, FL");
            nuovoMiami.setPrecioPorNoche(new BigDecimal("58200.00"));
            nuovoMiami.setCategoria(hotel);
            nuovoMiami.setCiudad(miami);
            nuovoMiami.setCaracteristicas(caracteristicas1);
            nuovoMiami.setImagenes(inuovoMiami);
            nuovoMiami.setPoliticas(pnuovoMiami);
            nuovoMiami.setCoordenadas(nuoMiamiApartaments);
            productoServiceImp.saveProducto(nuovoMiami);

            Producto urbanyHostel  = new Producto();
            urbanyHostel.setTitulo("Urbany Hostel");
            urbanyHostel.setTituloDescripcion("La comodidad que estas buscando");
            urbanyHostel.setDescripcion("El Urbany Hostel Barcelona se encuentra en la avenida Meridiana, en Barcelona, a 300 metros de la estación de metro Clot. Este albergue grande y moderno tiene amplias áreas comunes donde puedes relajarte y conocer gente de otros países. Hay WiFi gratuita en todo el hotel y una terraza en la azotea con vistas a la ciudad y servicio de bar.\n" +
                    "\n" +
                    "Tanto las habitaciones privadas como las habitaciones compartidas están decoradas en estilo moderno y constan de taquillas, baño y aire acondicionado.\n" +
                    "\n" +
                    "El Urbany Barcelona sirve un desayuno buffet continental a diario. Además, dispone de cocina equipada con fogones, neveras y microondas. También alberga un bar cafetería y máquinas expendedoras. Además, se preparan almuerzos para llevar bajo petición.\n" +
                    "\n" +
                    "La recepción está abierta las 24 horas y dispone de mostrador de información turística. Se proporcionan mapas gratuitos de la ciudad.\n" +
                    "\n" +
                    "El Urbany Hostel está situado a 5 minutos a pie del centro comercial Glòries y a 15 minutos a pie de la Sagrada Familia de Gaudí. Asimismo, el establecimiento se halla a 15 minutos en metro o en autobús del centro de la ciudad y a 2 km de la playa.\n");
            urbanyHostel.setDireccion("Avenida Meridiana, 97, Sant Martí, 08026 Barcelona, España");
            urbanyHostel.setPrecioPorNoche(new BigDecimal("23700.00"));
            urbanyHostel.setCategoria(hostel);
            urbanyHostel.setCiudad(barcelona);
            urbanyHostel.setCaracteristicas(caracteristicas2);
            urbanyHostel.setImagenes(iurbanyHostel);
            urbanyHostel.setPoliticas(purbanyHostel);
            urbanyHostel.setCoordenadas(urbanyHostelc);
            productoServiceImp.saveProducto(urbanyHostel);

            Producto villadEste  = new Producto();
            villadEste.setTitulo("Hotel Villa d'Este");
            villadEste.setTituloDescripcion("Cerca de la playa");
            villadEste.setDescripcion("El Hotel Villa D'Este se encuentra en Grado, a solo 400 metros del mar, y ofrece piscina al aire libre. Ofrece habitaciones con aire acondicionado, balcón y TV vía satélite.\n" +
                    "\n" +
                    "El hotel dispone de terraza con tumbonas. También hay un terminal con conexión a internet y WiFi gratuita en las zonas comunes.\n" +
                    "\n" +
                    "El almuerzo y la cena se pueden disfrutar en un hotel asociado, situado a 60 metros del Villa D'Este Hotel.");
            villadEste.setDireccion("Via Giuseppe Parini 9, 34073 Grado, Italia");
            villadEste.setPrecioPorNoche(new BigDecimal("38050.00"));
            villadEste.setCategoria(hotel);
            villadEste.setCiudad(grado);
            villadEste.setCaracteristicas(caracteristicas3);
            villadEste.setImagenes(ivilladEste);
            villadEste.setPoliticas(pvilladEste);
            villadEste.setCoordenadas(villadEstec);
            productoServiceImp.saveProducto(villadEste);

            Producto laSoberanaHosteria  = new Producto();
            laSoberanaHosteria.setTitulo("La Soberana Hostería");
            laSoberanaHosteria.setTituloDescripcion("Cercanía del lago Argentino");
            laSoberanaHosteria.setDescripcion("La Hostería La Soberana se encuentra en El Calafate, a 11 km del lago Argentino, y ofrece jardín y vistas a la montaña. El establecimiento se encuentra a 2,8 km de la Isla Solitaria, a 7,2 km del Museo Regional y a 7,6 km de la laguna de Nimez. Ofrece recepción 24 horas, servicio de enlace con el aeropuerto, servicio de habitaciones y WiFi gratuita.\n" +
                    "\n" +
                    "Las habitaciones de la posada tienen vistas a la ciudad y baño privado con bidet y artículos de aseo gratuitos. Las habitaciones de la Hostería La Soberana están equipadas con ropa de cama y toallas.\n" +
                    "\n" +
                    "El establecimiento sirve un desayuno buffet o continental. La Hostería La Soberana alberga un restaurante de cocina argentina. También se puede solicitar una opción sin gluten.\n" +
                    "\n" +
                    "La estación de autobuses de El Calafate se encuentra a 8,1 km de la posada, mientras que las ruinas de Puerto Irma están a 20 km. El aeropuerto más cercano es el aeropuerto internacional Comandante Armando Tola, ubicado a 27 km de la Hostería La Soberana.\n");
            laSoberanaHosteria.setDireccion("Calle 218 N° 347, 9405 El Calafate, Argentina");
            laSoberanaHosteria.setPrecioPorNoche(new BigDecimal("38100.00"));
            laSoberanaHosteria.setCategoria(bedAndBreakfast);
            laSoberanaHosteria.setCiudad(elCalafate);
            laSoberanaHosteria.setCaracteristicas(caracteristicas4);
            laSoberanaHosteria.setImagenes(ilaSoberanaHosteria);
            laSoberanaHosteria.setPoliticas(plaSoberanaHosteria);
            laSoberanaHosteria.setCoordenadas(laSoberanaHosteriac);
            productoServiceImp.saveProducto(laSoberanaHosteria);

            Producto utaka  = new Producto();
            utaka.setTitulo("Utaka Cabañas y Apartamentos");
            utaka.setTituloDescripcion("Estudio con vistas a la montaña");
            utaka.setDescripcion("Los departamentos de La Soberana se encuentra en El Calafate, a 11 km del lago Argentino, y ofrece jardín y vistas a la montaña. El establecimiento se encuentra a 2,8 km de la Isla Solitaria, a 7,2 km del Museo Regional y a 7,6 km de la laguna de Nimez. Ofrece recepción 24 horas, servicio de enlace con el aeropuerto, servicio de habitaciones y WiFi gratuita.\n" +
                    "\n" +
                    "Las habitaciones de la posada tienen vistas a la ciudad y baño privado con bidet y artículos de aseo gratuitos. Las habitaciones de la Hostería La Soberana están equipadas con ropa de cama y toallas.\n" +
                    "\n" +
                    "El establecimiento sirve un desayuno buffet o continental. La Hostería La Soberana alberga un restaurante de cocina argentina. También se puede solicitar una opción sin gluten.\n" +
                    "\n" +
                    "La estación de autobuses de El Calafate se encuentra a 8,1 km de la posada, mientras que las ruinas de Puerto Irma están a 20 km. El aeropuerto más cercano es el aeropuerto internacional Comandante Armando Tola, ubicado a 27 km de la Hostería La Soberana.\n");
            utaka.setDireccion("Goleta Florencia 1686, 9410 Ushuaia, Argentina");
            utaka.setPrecioPorNoche(new BigDecimal("19515.00"));
            utaka.setCategoria(departamento);
            utaka.setCiudad(ushuaia);
            utaka.setCaracteristicas(caracteristicas5);
            utaka.setImagenes(iutaka);
            utaka.setPoliticas(putaka);
            utaka.setCoordenadas(utakac);
            productoServiceImp.saveProducto(utaka);

            Producto orillaMansa  = new Producto();
            orillaMansa.setTitulo("Apart Hotel Orilla Mansa");
            orillaMansa.setTituloDescripcion("Estudio con vistas a la montaña");
            orillaMansa.setDescripcion("Está situado en la avenida principal, a solo 20 metros del lago Lacar.El Apart Hotel Orilla Mansa ofrece apartamentos independientes con conexión Wi-Fi en San Martín de los Andes. El establecimiento se encuentra a 200 metros de la terminal de autobuses y a 400 metros del centro de la ciudad.\n" +
                    "\n" +
                    "Los apartamentos de Orilla Mansa presentan un estilo rústico, paredes con paneles de madera y una decoración detallada. Disponen de TV LED vía satélite y cocina bien equipada con horno y microondas.\n" +
                    "\n" +
                    "Todos los apartamentos disponen de calefacción central y una acogedora estufa de leña o salamandra en la sala de estar. El desayuno diario incluye té, café, leche, chocolate caliente, mermelada, 2 cruasanes y 3 tostadas de pan por persona.\n" +
                    "\n" +
                    "El Apart Hotel Orilla Mansa dispone de zona de barbacoa. El establecimiento cuenta con mostrador de información turística y guardaesquíes. En los alrededores se pueden practicar actividades como esquí, senderismo o equitación.");
            orillaMansa.setDireccion("Avenida San Martin 135, 8370 San Martín de los Andes, Argentina");
            orillaMansa.setPrecioPorNoche(new BigDecimal("24286.00"));
            orillaMansa.setCategoria(departamento);
            orillaMansa.setCiudad(sanMartindelosAndes);
            orillaMansa.setCaracteristicas(caracteristicas6);
            orillaMansa.setImagenes(iorillaMansa);
            orillaMansa.setPoliticas(porillaMansa);
            orillaMansa.setCoordenadas(orillaMansac);
            productoServiceImp.saveProducto(orillaMansa);

            Producto laReservaVirginLodge  = new Producto();
            laReservaVirginLodge.setTitulo("La Reserva Virgin Lodge");
            laReservaVirginLodge.setTituloDescripcion("Con espíritu de aventura y alma gourmet");
            laReservaVirginLodge.setDescripcion("La Reserva Virgin Lodge ofrece confort de 4 estrellas y aventuras en la jungla en el corazón de la selva Iryapú, a 10 minutos en coche de las cataratas panorámicas del Iguazú. Hay conexión WiFi gratuita en todo el establecimiento.\n" +
                    "\n" +
                    "La piscina al aire libre está rodeada de elegantes sillones de mimbre. El establecimiento organiza paseos por la selva todos los días.\n" +
                    "\n" +
                    "Las habitaciones de La Reserva Virgin Lodge presentan una decoración inspirada en la selva con ventanas amplias y una elegante arquitectura en madera. Todas disponen de aire acondicionado, TV LCD y balcones privados con vistas a la selva.\n" +
                    "\n" +
                    "Se sirve un desayuno gourmet con jamones finos, pan de leche recién horneado y zumos naturales. El restaurante prepara platos gourmet y la enoteca tiene una extensa carta de vinos argentinos. Por un suplemento, se organizan excursiones guiadas en bicicleta y servicios de alquiler de coches.");
            laReservaVirginLodge.setDireccion("Selva Iriapú, 3370 Puerto Iguazú, Argentina");
            laReservaVirginLodge.setPrecioPorNoche(new BigDecimal("35712.00"));
            laReservaVirginLodge.setCategoria(departamento);
            laReservaVirginLodge.setCiudad(puertoIguazu);
            laReservaVirginLodge.setCaracteristicas(caracteristicas1);
            laReservaVirginLodge.setImagenes(ilaReservaVirginLodge);
            laReservaVirginLodge.setPoliticas(plaReservaVirginLodge);
            laReservaVirginLodge.setCoordenadas(laReservaVirginLodgec);
            productoServiceImp.saveProducto(laReservaVirginLodge);

            Producto pousadaBonfim  = new Producto();
            pousadaBonfim.setTitulo("Pousada Bonfim");
            pousadaBonfim.setTituloDescripcion("Perfume marítimo y vista a Praia dos Ossos");
            pousadaBonfim.setDescripcion(
                    "La Bonfim Pousada está enfrente de la Praia dos Ossos de Búzios, a 1 km de la calle Rua das Pedras. Ofrece alojamientos de ambiente acogedor y vistas panorámicas al mar desde el solárium.\n" +
                            "\n" +
                            "Las habitaciones son luminosas y presentan una decoración sencilla. Disponen de aire acondicionado, TV, minibar y baño privado con ducha de agua caliente.\n" +
                            "\n" +
                            "Todos los días se sirve un desayuno bufé en la terraza que incluye fruta fresca, pan, embutidos y una selección de bebidas frías y calientes.\n" +
                            "\n" +
                            "A 1 km del establecimiento hay bares, tiendas y restaurantes. La terminal de autobuses de Búzios está a 1 km de la Bonfim Pousada y el aeropuerto de Cabo Frio, a 34 km.\n");
            pousadaBonfim.setDireccion("Rua Agripino de Souza, 166, Ossos, Búzios, CEP 28950-000, Brasil");
            pousadaBonfim.setPrecioPorNoche(new BigDecimal("39500.00"));
            pousadaBonfim.setCategoria(bedAndBreakfast);
            pousadaBonfim.setCiudad(buzios);
            pousadaBonfim.setCaracteristicas(caracteristicas2);
            pousadaBonfim.setImagenes(ipousadaBonfim);
            pousadaBonfim.setPoliticas(ppousadaBonfim);
            pousadaBonfim.setCoordenadas(pousadaBonfimc);
            productoServiceImp.saveProducto(pousadaBonfim);

            Producto nacionaldeCuba  = new Producto();
            nacionaldeCuba.setTitulo("Nacional de Cuba");
            nacionaldeCuba.setTituloDescripcion("Nostalgia isleña con arquitectura colonial");
            nacionaldeCuba.setDescripcion("La arquitectura colonial española en el centro de la Habana Vieja del siglo XVI incluye el Castillo de la Fuerza Real, un fuerte y un museo marítimo. El edificio del Capitolio Nacional es un monumento icónico de la década de 1920. En la Habana Vieja también se encuentra la catedral barroca de San Cristóbal y la Plaza Vieja, cuyos edificios reflejan la dinámica mezcla arquitectónica de la ciudad.");
            nacionaldeCuba.setDireccion("Calle O Esq. 21, Vedado. La Habana, Cuba");
            nacionaldeCuba.setPrecioPorNoche(new BigDecimal("36000.00"));
            nacionaldeCuba.setCategoria(hotel);
            nacionaldeCuba.setCiudad(laHabana);
            nacionaldeCuba.setCaracteristicas(caracteristicas3);
            nacionaldeCuba.setImagenes(inacionaldeCuba);
            nacionaldeCuba.setPoliticas(pnacionaldeCuba);
            nacionaldeCuba.setCoordenadas(nacionaldeCubac);
            productoServiceImp.saveProducto(nacionaldeCuba);

            Producto interContinental  = new Producto();
            interContinental.setTitulo("InterContinental New York Times Square");
            interContinental.setTituloDescripcion("Ambiente acogedor con vistas a la ciudad");
            interContinental.setDescripcion("\n" +
                    "\n" +
                    "El InterContinental New York Times Square ocupa 36 plantas y ofrece alojamiento con ventanales y vistas al perfil urbano de Manhattan, al río Hudson y al distrito de los teatros de Broadway.\n" +
                    "\n" +
                    "Todas las habitaciones tienen TV de pantalla plana, amplia zona de trabajo, baño inspirado en un spa con ducha de efecto lluvia a ras de suelo, cafetera, minibar y secador de pelo.\n" +
                    "\n" +
                    "En la 3ª planta del Intercontinental se encuentra un gimnasio abierto las 24 horas, que ofrece vistas a 44th Street y cuenta con cintas de correr y pesas libres.\n" +
                    "\n" +
                    "El InterContinental está a 37 metros de una parada de subte y a 322 metros de Times Square. En los alrededores hay teatros, comercios y restaurantes.\n" +
                    "\n" +
                    "El Stinger Cocktail Bar & Kitchen ofrece un ambiente animado, cócteles artesanales y un menú a la carta. El ambiente acogedor y las deliciosas bebidas son el lugar perfecto para disfrutar del final de la noche.\n");
            interContinental.setDireccion("300 West 44th Street, Midtown West, Nueva York, NY");
            interContinental.setPrecioPorNoche(new BigDecimal("88113.00"));
            interContinental.setCategoria(hotel);
            interContinental.setCiudad(newyork);
            interContinental.setCaracteristicas(caracteristicas4);
            interContinental.setImagenes(iinterContinental);
            interContinental.setPoliticas(pnacionaldeCuba);
            interContinental.setCoordenadas(interContinentalc);
            productoServiceImp.saveProducto(interContinental);

            Producto nineHours  = new Producto();
            nineHours.setTitulo("Nine Hours Shinjuku-North");
            nineHours.setTituloDescripcion("Alojate en el corazón de Tokio");
            nineHours.setDescripcion("Nine Hours Shinjuku-North, El nine hours Suidobashi goza de una buena ubicación en el distrito Chiyoda de Tokio, a 500 metros del santuario Misaki Inari, a menos de 1 km de la iglesia de San Francisco Javier y a 11 minutos a pie del santuario Tsukudo. Este hotel cápsula de 2 estrellas ofrece habitaciones con aire acondicionado, baño compartido y WiFi gratuita. Cuenta con recepción 24 horas y consigna de equipaje.\n" +
                    "\n" +
                    "Cerca del hotel cápsula hay varios lugares de interés, como el Museo de Arte del Centro de la Amistad de Japón-China, el Museo y Salón de la Fama del Béisbol y el centro comercial Ramla. El nine hours Suidobashi se halla a 16 km del aeropuerto internacional de Tokio-Haneda, el más cercano. ");
            nineHours.setDireccion("100-8283 Tokio, Chiyoda-ku, Marunouchi Trust Tower Main, 1-8-3 Marunouchi,Japón");
            nineHours.setPrecioPorNoche(new BigDecimal("11200.00"));
            nineHours.setCategoria(hostel);
            nineHours.setCiudad(tokio);
            nineHours.setCaracteristicas(caracteristicas5);
            nineHours.setImagenes(ininehours);
            nineHours.setPoliticas(pnineHours);
            nineHours.setCoordenadas(nineHoursc);
            productoServiceImp.saveProducto(nineHours);

            Producto apaTokio  = new Producto();
            apaTokio.setTitulo("APA Hotel Higashi Shinjuku Kabukicho Tower");
            apaTokio.setTituloDescripcion("Alojate en el corazón de Tokio");
            apaTokio.setDescripcion("\n" +
                    "\n" +
                    "El APA Hotel Higashi Shinjuku Kabukicho Tower alberga un centro de spa y bienestar y se encuentra en Tokio, en la región de Tokio, a 300 metros del parque conmemorativo Koizumi Yakumo y del Museo de Corea. Este hotel de 3 estrellas alberga un restaurante y ofrece habitaciones con aire acondicionado y baño privado. Se ofrece aparcamiento privado por un suplemento.\n" +
                    "\n" +
                    "Las habitaciones están equipadas con escritorio, TV de pantalla plana y baño compartido. Todas las habitaciones incluyen nevera.\n" +
                    "\n" +
                    "El APA Higashishinjuku Kabukicho Tower sirve un desayuno buffet todas las mañanas.\n" +
                    "\n" +
                    "El personal de la recepción habla japonés e inglés y puede facilitar información sobre la zona.\n" +
                    "\n" +
                    "El establecimiento está cerca de varios lugares de interés, como el Museo Samurai, el parque Okubo y el santuario de Inari. El aeropuerto internacional de Tokio Haneda es el más cercano y queda a 17 km del APA Hotel Higashi Shinjuku Kabukicho Tower.\n");
            apaTokio.setDireccion("160-0021 Prefectura de Tokio, Shinjuku-ku Kabukicho 2-31-12, Japón ");
            apaTokio.setPrecioPorNoche(new BigDecimal("71700.00"));
            apaTokio.setCategoria(hotel);
            apaTokio.setCiudad(tokio);
            apaTokio.setCaracteristicas(caracteristicas6);
            apaTokio.setImagenes(iapaTokio);
            apaTokio.setPoliticas(papaTokio);
            apaTokio.setCoordenadas(apaTokioc);
            productoServiceImp.saveProducto(apaTokio);


            //Akihabara Washington Hotel
            Producto akihabara  = new Producto();
            akihabara.setTitulo("Akihabara Washington Hotel");
            akihabara.setTituloDescripcion("Alojate en el corazón de Tokio");
            akihabara.setDescripcion("El Akihabara Washington Hotel se encuentra a 1 minuto a pie de la estación JR Akihabara, a 9 minutos en coche de la estación de Tokio, y ofrece alojamientos modernos, restaurante y WiFi gratuita en todas sus instalaciones.\n" +
                    "\n" +
                    "Las habitaciones disponen de aire acondicionado, nevera, escritorio y TV de pantalla plana. También incluyen baño con bañera y artículos de aseo.\n" +
                    "\n" +
                    "El establecimiento cuenta con recepción abierta las 24 horas, consigna de equipaje, servicio de cambio de divisa y caja fuerte. También se ofrece servicio de lavandería.\n" +
                    "\n" +
                    "El Hotel Washington Akihabara está a 25 minutos en coche del parque temático Tokyo Disneyland. Queda a 19 km del aeropuerto de Haneda.\n");
            akihabara.setDireccion("160-0021 Prefectura de Tokio, Shinjuku-ku Kabukicho 2-31-12, Japón ");
            akihabara.setPrecioPorNoche(new BigDecimal("62600.00"));
            akihabara.setCategoria(hotel);
            akihabara.setCiudad(tokio);
            akihabara.setCaracteristicas(caracteristicas1);
            akihabara.setImagenes(iakihabara);
            akihabara.setPoliticas(pakihabara);
            akihabara.setCoordenadas(akihabarac);
            productoServiceImp.saveProducto(akihabara);


            //Cerulean Tower Tokyu Hotel
            Producto cerulean  = new Producto();
            cerulean.setTitulo("Cerulean Tower Tokyu Hotel");
            cerulean.setTituloDescripcion("Alojate en el corazón de Tokio");
            cerulean.setDescripcion("El Cerulean Tower Tokyu Hotel está en el centro de Shibuya y ofrece habitaciones amplias con vistas panorámicas a la ciudad. El hotel cuenta con un centro de fitness y varios sitios para comer.\n" +
                    "\n" +
                    "El Cerulean Tower se encuentra a solo 5 minutos a pie de la estación de Shibuya, que enlaza con numerosas líneas de tren y metro. Desde la estación se puede llegar en tren a Harajuku y al Santuario Meiji Jingu en solo 2 minutos.\n" +
                    "\n" +
                    "Las habitaciones están en la planta 19 y superiores. Todas tienen vistas a la zona metropolitana más importante, aire acondicionado, escritorio, TV de pantalla plana, minibar y baño privado con bañera, ducha y artículos de aseo gratuitos, como cepillo de dientes, champú, acondicionador y gel de ducha. Hay servicio de habitaciones las 24 horas.\n" +
                    "\n" +
                    "El Tokyu Hotel Cerulean Tower alberga una sauna, una bañera de hidromasaje y un salón de belleza donde se realizan tratamientos. También consta de pastelería y servicio de guardería.\n" +
                    "\n" +
                    "El Cerulean Tower Tokyu Hotel cuenta con 8 restaurantes que sirven cocina japonesa, china y francesa. El bar de la última planta, el Garden Lounge y el Jazz Club sirven bebidas.\n");
            cerulean.setDireccion("150-8512 Prefectura de Tokio, Shibuya-ku, Sakuragaokacho 26-1, Japón ");
            cerulean.setPrecioPorNoche(new BigDecimal("90200.00"));
            cerulean.setCategoria(hotel);
            cerulean.setCiudad(tokio);
            cerulean.setCaracteristicas(caracteristicas2);
            cerulean.setImagenes(icerulean);
            cerulean.setPoliticas(pcerulean);
            cerulean.setCoordenadas(ceruleanc);
            productoServiceImp.saveProducto(cerulean);

            //Mandarin Oriental
            Producto mandarin  = new Producto();
            mandarin.setTitulo("Mandarin Oriental");
            mandarin.setTituloDescripcion("Alojate en el corazón de Tokio");
            mandarin.setDescripcion("El Mandarin Oriental, ubicado en la zona histórica de Nihonbashi, en el centro de Tokio, alberga excelentes restaurantes y un spa galardonado (situado en la planta 37) y ofrece todos los lujos de un hotel de 5 estrellas. Las habitaciones son de las más amplias de Tokio y tienen vistas impresionantes a la ciudad. El hotel está comunicado directamente con la estación de metro Mitsukoshimae y con la estación de tren JR Shin-Nihonbashi.\n" +
                    "\n" +
                    "Este hotel ha sido galardonado con el premio Travellers' Choice y con el Certificado de Excelencia de TripAdvisor en el año 2016.\n" +
                    "\n" +
                    "Las habitaciones del Mandarin Oriental, Tokyo disponen de ventanales con un diseño elegante inspirado en la estética japonesa. Además, incluyen una amplia variedad de comodidades modernas como TV de pantalla plana con canales vía satélite. Todas las habitaciones tienen sofá, zona de estar con escritorio y baño con bañera grande y ducha independiente."
            );
            mandarin.setDireccion("103-8328 Prefectura de Tokio, Chuo-ku Nihonbashi Muromachi 2-1-1, Japón");
            mandarin.setPrecioPorNoche(new BigDecimal("83950.00"));
            mandarin.setCategoria(hotel);
            mandarin.setCiudad(tokio);
            mandarin.setCaracteristicas(caracteristicas3);
            mandarin.setImagenes(imandarin);
            mandarin.setPoliticas(pmandarin);
            mandarin.setCoordenadas(mandarinc);
            productoServiceImp.saveProducto(mandarin);


            //higashiGinza
            Producto higashiGinza = new Producto();
            higashiGinza.setTitulo("Higashi Ginza");
            higashiGinza.setTituloDescripcion("Alojate en el corazón de Tokio");
            higashiGinza.setDescripcion("Higashi-Ginza ofrece habitaciones con aire acondicionado y TV de pantalla plana en el distrito Chuo de Tokio. Este hotel de 3 estrellas cuenta con recepción 24 horas. El establecimiento se encuentra a 500 metros del centro de la ciudad y a 200 metros del templo Hojuji.\n" +
                    "\n" +
                    "Todos los alojamientos incluyen hervidor de agua. Las habitaciones del KEIKYU EX INN Higashi-Ginza disponen de WiFi gratuita y baño privado con bidet y secador de pelo. Algunas tienen vistas a la ciudad. Hay nevera.\n" +
                    "\n" +
                    "Todas las mañanas se sirve un desayuno continental y asiático.\n" +
                    "\n" +
                    "Cerca del establecimiento hay varios lugares de interés. El KEIKYU EX INN Higashi-Ginza incluye Nihon Tenji Seitei no Chi, el templo Ensho-ji y el santuario Hoju Inari. El aeropuerto más cercano es el de Tokio Haneda, ubicado a 15 km del hotel."
            );
            higashiGinza.setDireccion("104-0045 Tokyo-to, 2-15-15 Tsukiji, Chuo-ku, Japan ");
            higashiGinza.setPrecioPorNoche(new BigDecimal("95800.00"));
            higashiGinza.setCategoria(hotel);
            higashiGinza.setCiudad(tokio);
            higashiGinza.setCaracteristicas(caracteristicas4);
            higashiGinza.setImagenes(ihigashiGinza);
            higashiGinza.setPoliticas(phigashiGinza);
            higashiGinza.setCoordenadas(higashiGinzac);
            productoServiceImp.saveProducto(higashiGinza);



            UserDetailsDto userDetailsDtoAdmin = new UserDetailsDto();
            userDetailsDtoAdmin.setUserId(1L);
            userDetailsDtoAdmin.setUserRol("ADMIN");

//			puntuaciones hoteles
            Puntuacion puntuacionHiltongarden = new Puntuacion();
            puntuacionHiltongarden.setValor(4);
            puntuacionHiltongarden.setProducto(hiltongarden);
            puntuacionHiltongarden.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionHiltongarden, userDetailsDtoAdmin);

            Puntuacion puntuacionNuovoMiami = new Puntuacion();
            puntuacionNuovoMiami.setValor(5);
            puntuacionNuovoMiami.setProducto(nuovoMiami);
            puntuacionNuovoMiami.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionNuovoMiami, userDetailsDtoAdmin);

            Puntuacion puntuacionVilladEste = new Puntuacion();
            puntuacionVilladEste.setValor(1);
            puntuacionVilladEste.setProducto(villadEste);
            puntuacionVilladEste.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionVilladEste, userDetailsDtoAdmin);

            Puntuacion puntuacionLaReservaVirginLodge = new Puntuacion();
            puntuacionLaReservaVirginLodge.setValor(3);
            puntuacionLaReservaVirginLodge.setProducto(laReservaVirginLodge);
            puntuacionLaReservaVirginLodge.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLaReservaVirginLodge, userDetailsDtoAdmin);

            Puntuacion puntuacionNacionaldeCuba = new Puntuacion();
            puntuacionNacionaldeCuba.setValor(3);
            puntuacionNacionaldeCuba.setProducto(nacionaldeCuba);
            puntuacionNacionaldeCuba.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionNacionaldeCuba, userDetailsDtoAdmin);

            Puntuacion puntuacionInterContinental = new Puntuacion();
            puntuacionInterContinental.setValor(5);
            puntuacionInterContinental.setProducto(interContinental);
            puntuacionInterContinental.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionInterContinental, userDetailsDtoAdmin);

            Puntuacion puntuacionapaTokio = new Puntuacion();
            puntuacionapaTokio.setValor(4);
            puntuacionapaTokio.setProducto(apaTokio);
            puntuacionapaTokio.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionapaTokio, userDetailsDtoAdmin);

            Puntuacion puntuacionakihabara = new Puntuacion();
            puntuacionakihabara.setValor(5);
            puntuacionakihabara.setProducto(akihabara);
            puntuacionakihabara.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionakihabara , userDetailsDtoAdmin);

            Puntuacion puntuacioncerulean = new Puntuacion();
            puntuacioncerulean.setValor(5);
            puntuacioncerulean.setProducto(cerulean);
            puntuacioncerulean.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacioncerulean, userDetailsDtoAdmin);

            Puntuacion puntuacionmandarin = new Puntuacion();
            puntuacionmandarin.setValor(5);
            puntuacionmandarin.setProducto(mandarin);
            puntuacionmandarin.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionmandarin, userDetailsDtoAdmin);

            Puntuacion puntuacionHigashiGinza = new Puntuacion();
            puntuacionHigashiGinza.setValor(4);
            puntuacionHigashiGinza.setProducto(higashiGinza);
            puntuacionHigashiGinza.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionHigashiGinza, userDetailsDtoAdmin);



//			puntuaciones hostel
            Puntuacion puntuacionUrbanyHostel = new Puntuacion();
            puntuacionUrbanyHostel.setValor(3);
            puntuacionUrbanyHostel.setProducto(urbanyHostel);
            puntuacionUrbanyHostel.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionUrbanyHostel, userDetailsDtoAdmin);

            Puntuacion puntuacionLaBrisa = new Puntuacion();
            puntuacionLaBrisa.setValor(4);
            puntuacionLaBrisa.setProducto(laBrisa);
            puntuacionLaBrisa.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLaBrisa, userDetailsDtoAdmin);

            Puntuacion puntuacionNineHours = new Puntuacion();
            puntuacionNineHours.setValor(5);
            puntuacionNineHours.setProducto(nineHours);
            puntuacionNineHours.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionNineHours, userDetailsDtoAdmin);

            Puntuacion puntuacionZero = new Puntuacion();
            puntuacionZero.setValor(3);
            puntuacionZero.setProducto(zero);
            puntuacionZero.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionZero, userDetailsDtoAdmin);

//			putuaciones departamentos
            Puntuacion puntuacionUtaka = new Puntuacion();
            puntuacionUtaka.setValor(2);
            puntuacionUtaka.setProducto(utaka);
            puntuacionUtaka.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionUtaka, userDetailsDtoAdmin);

            Puntuacion puntuacionLeBatiment = new Puntuacion();
            puntuacionLeBatiment.setValor(5);
            puntuacionLeBatiment.setProducto(leBatiment);
            puntuacionLeBatiment.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLeBatiment, userDetailsDtoAdmin);

            Puntuacion puntuacionOrillaMansa = new Puntuacion();
            puntuacionOrillaMansa.setValor(4);
            puntuacionOrillaMansa.setProducto(orillaMansa);
            puntuacionOrillaMansa.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionOrillaMansa, userDetailsDtoAdmin);

//			puntuaciones bed and breakfast

            Puntuacion puntuacionPousadaBonfim = new Puntuacion();
            puntuacionPousadaBonfim.setValor(5);
            puntuacionPousadaBonfim.setProducto(pousadaBonfim);
            puntuacionPousadaBonfim.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionPousadaBonfim, userDetailsDtoAdmin);

            Puntuacion puntuacionLaSoberanaHosteria = new Puntuacion();
            puntuacionLaSoberanaHosteria.setValor(2);
            puntuacionLaSoberanaHosteria.setProducto(laSoberanaHosteria);
            puntuacionLaSoberanaHosteria.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLaSoberanaHosteria, userDetailsDtoAdmin);

            Puntuacion puntuacionLantica = new Puntuacion();
            puntuacionLantica.setValor(3);
            puntuacionLantica.setProducto(antico);
            puntuacionLantica.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLantica, userDetailsDtoAdmin);

            Puntuacion puntuacionRiari = new Puntuacion();
            puntuacionRiari.setValor(4);
            puntuacionRiari.setProducto(riari);
            puntuacionRiari.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionRiari, userDetailsDtoAdmin);





        };
    }
}
