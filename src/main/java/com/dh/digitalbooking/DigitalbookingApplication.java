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


            Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
            Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80"));
            Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
            Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));

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
                    "Check-out: 10:00\n"+
                            "No se permiten fiestas\n" +
                            "No fumar\n" +
                            "Check-in: 10:00\n" +
                            "No se permiten fiestas\n",
                    normasDeLaCasa
            );

            Politica politica3 = new Politica(
                    "Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadía\n" +
                            "reintegro del 50%\n"+
                            "politica de cancelación 1\n" +
                            "politica de cancelación 2\n",
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

            Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(cocina, televisor, parrilla, lavadora, noFumar));
            Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(noFumar, parrilla, lavadora, pileta, aptoMascotas, televisor, wifi));
            Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas, banioPrivado, vistaCiudad, parrilla, lavadora));

            //zero
            Imagen zero1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/zero/317878776.jpg");
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
            Imagen antico2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/antico/44251868.jpg");
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
            Imagen leBatiment2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222877504.jpg");
            Imagen leBatiment3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222879984.jpg");
            Imagen leBatiment4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222890172.jpg");
            Imagen leBatiment5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/222890182.jpg");
            Imagen leBatiment6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/22384908.jpg");
            Imagen leBatiment7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/LeBatiment/54304600.jpg");

            //hiltongarden
            Imagen hiltongarden1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249632667.jpg");
            Imagen hiltongarden2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249633069.jpg");
            Imagen hiltongarden3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249634973.jpg");
            Imagen hiltongarden4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/249871836.jpg");
            Imagen hiltongarden5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Hilton/313379046.jpg");

            //laBrisa
            Imagen laBrisa1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/LaBrisa/151251211.jpg");
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
            Imagen nuovoMiami6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024608.jpg");
            Imagen nuovoMiami7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024704.jpg");
            Imagen nuovoMiami8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/NuovoMiami/404024805.jpg");

            //villadEste
            Imagen villadEste1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367990746.jpg");
            Imagen villadEste2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367990812.jpg");
            Imagen villadEste3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367991438.jpg");
            Imagen villadEste4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/villadEste/367991543.jpg");
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
            Imagen urbanyHostel8 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381536.jpg");
            Imagen urbanyHostel9 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381552.jpg");
            Imagen urbanyHostel10 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/urbany/382381640.jpg");

            //laSoberanaBed&Breakfast
            Imagen laSoberanaHosteria1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128347938.jpg");
            Imagen laSoberanaHosteria2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128348420.jpg");
            Imagen laSoberanaHosteria3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/128352007.jpg");
            Imagen laSoberanaHosteria4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/156003329.jpg");
            Imagen laSoberanaHosteria5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/216458663.jpg");
            Imagen laSoberanaHosteria6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/LaSoberana/216458911.jpg");

            //utaka
            Imagen utaka1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/221795404.jpg");
            Imagen utaka2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227765817.jpg");
            Imagen utaka3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227765829.jpg");
            Imagen utaka4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227766702.jpg");
            Imagen utaka5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/227766715.jpg");
            Imagen utaka6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/248803298.jpg");
            Imagen utaka7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/utaka/336734755.jpg");

            //orillaMansa
            Imagen orillaMansa1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916227.jpg");
            Imagen orillaMansa2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916745.jpg");
            Imagen orillaMansa3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/291916952.jpg");
            Imagen orillaMansa4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726088.jpg");
            Imagen orillaMansa5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726101.jpg");
            Imagen orillaMansa6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/orillaMansa/440726961.jpg");
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
            Imagen laReservaVirginLodge9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/37579995.jpg");
            Imagen laReservaVirginLodge10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/apartments/laReservaLodge/38312132.jpg");

            //bonFim
            Imagen bonFim1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/10484160.jpg");
            Imagen bonFim2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/16785629.jpg");
            Imagen bonFim3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/228280.webp");
            Imagen bonFim4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/63895518.jpg");
            Imagen bonFim5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/bedAndBreakfast/posadabom/93812496.jpg");

            //nacionalDeCuba
            Imagen nacionaldeCuba1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/182553824.jpg");
            Imagen nacionaldeCuba2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/182574494.jpg");
            Imagen nacionaldeCuba3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400798721.jpg");
            Imagen nacionaldeCuba4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400802292.jpg");
            Imagen nacionaldeCuba5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/Nacional/400802332.jpg");

            //interContinental
            Imagen interContinental1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/260331427.jpg");
            Imagen interContinental2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341425558.jpg");
            Imagen interContinental3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341436162.jpg");
            Imagen interContinental4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341439879.jpg");
            Imagen interContinental5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341466879.jpg");
            Imagen interContinental6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/341466875.jpg");
            Imagen interContinental7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/359664047.jpg");
            Imagen interContinental8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hoteles/intercontinental/419472171.jpg");

            //inineHours
            Imagen nineHours1 = new Imagen("Imagen 1", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/247091310.jpg");
            Imagen nineHours2 = new Imagen("Imagen 2", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664182.jpg");
            Imagen nineHours3 = new Imagen("Imagen 3", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664193.jpg");
            Imagen nineHours4 = new Imagen("Imagen 4", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664204.jpg");
            Imagen nineHours5 = new Imagen("Imagen 5", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664209.jpg");
            Imagen nineHours6 = new Imagen("Imagen 6", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664226.jpg");
            Imagen nineHours7 = new Imagen("Imagen 7", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/249664229.jpg");
            Imagen nineHours8 = new Imagen("Imagen 8", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/338517809.jpg");
            Imagen nineHours9 = new Imagen("Imagen 9", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/338517816.jpg");
            Imagen nineHours10 = new Imagen("Imagen 10", "https://grupo1-digitalbooking-s3-images.s3.amazonaws.com/hostels/ninehours/365167162.jpg");


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
                    new BigDecimal("82.3889"));

            Coordenadas interContinentalc = new Coordenadas(
                    new BigDecimal("40.7553"),
                    new BigDecimal("73.9861"));

            Coordenadas shangriLac = new Coordenadas(
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

            Coordenadas santaCaterinac = new Coordenadas(
                    new BigDecimal("40.6349"),
                    new BigDecimal("14.6110"));

            Coordenadas lanticac = new Coordenadas(
                    new BigDecimal("41.90205777396797"),
                    new BigDecimal("12.472714768447537"));

            Coordenadas leBatimentc = new Coordenadas(
                    new BigDecimal("-34.59636804201318"),
                    new BigDecimal("58.40801951557742"));

            Coordenadas anticoc = new Coordenadas(
                    new BigDecimal("43.768165137628806"),
                    new BigDecimal("11.254516863816157"));

            Coordenadas zeroc = new Coordenadas(
                    new BigDecimal("41.400409870994125"),
                    new BigDecimal("2.205703437638464"));

            Producto zero = new Producto();
            zero.setTitulo("L'antica Locanda Dell'Orso");
            zero.setTituloDescripcion("Alojate en el corazón de Florencia");
            zero.setDescripcion("El Antico Centro Suite ofrece alojamientos modernos con conexión wifi gratis en el centro de Florencia, junto a la Piazza della Repubblica y a 2 minutos a pie de la catedral de Santa María del Fiore.\n" +
                    "\n" +
                    "Las habitaciones están insonorizadas y cuentan con suelo de parquet, aire acondicionado, TV, zona de estar y minibar. El baño privado incluye secador de pelo y artículos de aseo gratuitos.\n" +
                    "\n" +
                    "El Antico Centro Suite se encuentra a 350 metros del Ponete Vecchio, a 4 minutos a pie de la Galería Uffizi y a 900 metros de la estación de tren de Florencia Santa Maria Novella.\n" +
                    "\n" +
                    "Nuestros clientes dicen que esta parte de Florencia es su favorita, según los comentarios independientes. ");
            zero.setDireccion("Via De' Brunelleschi, 5, Uffizi, 50122 Florencia, Italia");
            zero.setPrecioPorNoche(new BigDecimal("39200.00"));
            zero.setCategoria(hostel);
            zero.setCiudad(barcelona);
            zero.setCaracteristicas(caracteristicas3);
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
            antico.setPrecioPorNoche(new BigDecimal("46400.00"));
            antico.setCategoria(bedAndBreakfast);
            antico.setCiudad(florencia);
            antico.setCaracteristicas(caracteristicas3);
            antico.setImagenes(iantico);
            antico.setPoliticas(pantico);
            antico.setCoordenadas(anticoc);
            productoServiceImp.saveProducto(antico);

            Producto lantica = new Producto();
            lantica.setTitulo("L'antica Locanda Dell'Orso");
            lantica.setTituloDescripcion("Alojate en el corazón de Roma");
            lantica.setDescripcion(
                    "L'antica Locanda Dell'Orso ocupa un edificio del siglo XIII del centro de Roma, a 350 metros de la plaza Piazza Navona. Hay wifi gratis en todo el recinto.\n" +
                            "\n" +
                            "Todas las habitaciones están insonorizadas y disponen de techos con vigas a la vista, aire acondicionado, TV de pantalla plana y minibar. El baño privado incluye ducha, secador de pelo y artículos de aseo gratuitos.\n" +
                            "\n" +
                            "La Fontana di Trevi está a 15 minutos a pie de L'antica Locanda Dell'Orso y el Coliseo, a 2 km.\n" +
                            "\n" +
                            "Nuestros clientes dicen que esta parte de Roma es su favorita, según los comentarios independientes.");
            lantica.setDireccion("Paraguay 2862, Recoleta, 1425 Buenos Aires, Argentina");
            lantica.setPrecioPorNoche(new BigDecimal("46400.00"));
            lantica.setCategoria(bedAndBreakfast);
            lantica.setCiudad(roma);
            lantica.setCaracteristicas(caracteristicas3);
            lantica.setImagenes(ilantica);
            lantica.setPoliticas(plantica);
            lantica.setCoordenadas(lanticac);
            productoServiceImp.saveProducto(lantica);

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
            leBatiment.setCaracteristicas(caracteristicas3);
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
            hiltongarden.setPrecioPorNoche(new BigDecimal("520000.00"));
            hiltongarden.setCategoria(hotel);
            hiltongarden.setCiudad(neuquen);
            hiltongarden.setCaracteristicas(caracteristicas3);
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
            laBrisa.setCaracteristicas(caracteristicas2);
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
            nuovoMiami.setPrecioPorNoche(new BigDecimal("360000.00"));
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
            urbanyHostel.setPrecioPorNoche(new BigDecimal("36000.00"));
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
            villadEste.setPrecioPorNoche(new BigDecimal("380500.00"));
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
            laSoberanaHosteria.setPrecioPorNoche(new BigDecimal("380500.00"));
            laSoberanaHosteria.setCategoria(bedAndBreakfast);
            laSoberanaHosteria.setCiudad(elCalafate);
            laSoberanaHosteria.setCaracteristicas(caracteristicas1);
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
            utaka.setCaracteristicas(caracteristicas3);
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
            orillaMansa.setCaracteristicas(caracteristicas2);
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
            pousadaBonfim.setPrecioPorNoche(new BigDecimal("133450.00"));
            pousadaBonfim.setCategoria(bedAndBreakfast);
            pousadaBonfim.setCiudad(buzios);
            pousadaBonfim.setCaracteristicas(caracteristicas3);
            pousadaBonfim.setImagenes(ipousadaBonfim);
            pousadaBonfim.setPoliticas(ppousadaBonfim);
            pousadaBonfim.setCoordenadas(pousadaBonfimc);
            productoServiceImp.saveProducto(pousadaBonfim);

            Producto nacionaldeCuba  = new Producto();
            nacionaldeCuba.setTitulo("Nacional de Cuba");
            nacionaldeCuba.setTituloDescripcion("Nostalgia isleña con arquitectura colonial");
            nacionaldeCuba.setDescripcion("La arquitectura colonial española en el centro de la Habana Vieja del siglo XVI incluye el Castillo de la Fuerza Real, un fuerte y un museo marítimo. El edificio del Capitolio Nacional es un monumento icónico de la década de 1920. En la Habana Vieja también se encuentra la catedral barroca de San Cristóbal y la Plaza Vieja, cuyos edificios reflejan la dinámica mezcla arquitectónica de la ciudad.");
            nacionaldeCuba.setDireccion("Calle O Esq. 21, Vedado. La Habana, Cuba");
            nacionaldeCuba.setPrecioPorNoche(new BigDecimal("360000.00"));
            nacionaldeCuba.setCategoria(hotel);
            nacionaldeCuba.setCiudad(laHabana);
            nacionaldeCuba.setCaracteristicas(caracteristicas2);
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
            interContinental.setCaracteristicas(caracteristicas1);
            interContinental.setImagenes(iinterContinental);
            interContinental.setPoliticas(pnacionaldeCuba);
            interContinental.setCoordenadas(interContinentalc);
            productoServiceImp.saveProducto(interContinental);

            Producto nineHours  = new Producto();
            nineHours .setTitulo("Nine Hours Shinjuku-North");
            nineHours .setTituloDescripcion("Alojate en el corazón de Tokio");
            nineHours .setDescripcion("Nine Hours Shinjuku-North, El nine hours Suidobashi goza de una buena ubicación en el distrito Chiyoda de Tokio, a 500 metros del santuario Misaki Inari, a menos de 1 km de la iglesia de San Francisco Javier y a 11 minutos a pie del santuario Tsukudo. Este hotel cápsula de 2 estrellas ofrece habitaciones con aire acondicionado, baño compartido y WiFi gratuita. Cuenta con recepción 24 horas y consigna de equipaje.\n" +
                    "\n" +
                    "Cerca del hotel cápsula hay varios lugares de interés, como el Museo de Arte del Centro de la Amistad de Japón-China, el Museo y Salón de la Fama del Béisbol y el centro comercial Ramla. El nine hours Suidobashi se halla a 16 km del aeropuerto internacional de Tokio-Haneda, el más cercano. ");
            nineHours .setDireccion("100-8283 Tokio, Chiyoda-ku, Marunouchi Trust Tower Main, 1-8-3 Marunouchi,Japón");
            nineHours .setPrecioPorNoche(new BigDecimal("323124.00"));
            nineHours .setCategoria(hotel);
            nineHours .setCiudad(tokio);
            nineHours .setCaracteristicas(caracteristicas2);
            nineHours .setImagenes(ininehours);
            nineHours .setPoliticas(pnineHours);
            nineHours .setCoordenadas(shangriLac);
            productoServiceImp.saveProducto(nineHours);


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

            Puntuacion puntuacionLaSoberanaHosteria = new Puntuacion();
            puntuacionLaSoberanaHosteria.setValor(2);
            puntuacionLaSoberanaHosteria.setProducto(laSoberanaHosteria);
            puntuacionLaSoberanaHosteria.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLaSoberanaHosteria, userDetailsDtoAdmin);

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
            puntuacionNineHours.setProducto(laBrisa);
            puntuacionNineHours.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionNineHours, userDetailsDtoAdmin);

//			putuaciones departamentos
            Puntuacion puntuacionUtaka = new Puntuacion();
            puntuacionUtaka.setValor(2);
            puntuacionUtaka.setProducto(utaka);
            puntuacionUtaka.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionUtaka, userDetailsDtoAdmin);

            Puntuacion puntuacionLeBatiment = new Puntuacion();
            puntuacionLeBatiment.setValor(5);
            puntuacionLeBatiment.setProducto(pousadaBonfim);
            puntuacionLeBatiment.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLeBatiment, userDetailsDtoAdmin);

//			puntuaciones bed and breakfast
            Puntuacion puntuacionOrillaMansa = new Puntuacion();
            puntuacionOrillaMansa.setValor(4);
            puntuacionOrillaMansa.setProducto(orillaMansa);
            puntuacionOrillaMansa.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionOrillaMansa, userDetailsDtoAdmin);

            Puntuacion puntuacionPousadaBonfim = new Puntuacion();
            puntuacionPousadaBonfim.setValor(5);
            puntuacionPousadaBonfim.setProducto(pousadaBonfim);
            puntuacionPousadaBonfim.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionPousadaBonfim, userDetailsDtoAdmin);

            Puntuacion puntuacionLantica = new Puntuacion();
            puntuacionLantica.setValor(3);
            puntuacionLantica.setProducto(pousadaBonfim);
            puntuacionLantica.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionLantica, userDetailsDtoAdmin);

            Puntuacion puntuacionAntico = new Puntuacion();
            puntuacionAntico.setValor(5);
            puntuacionAntico.setProducto(pousadaBonfim);
            puntuacionAntico.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionAntico, userDetailsDtoAdmin);

        };
    }
}
