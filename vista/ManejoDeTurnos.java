package vista;

import controlador.*;
import modelo.*;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManejoDeTurnos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        // **********************************************************************************
        // Menu principal:
        // **********************************************************************************
        // En este menu:
        //  la opcion 1 va a simular la llegada del mensaje de un jugador solicitando su alta o baja en 
        // alguno de los turnos activos.
        // Las demas opciones permitiran trabajar con los distintos objetos que forman parte de la aplicacion,
        // pudiendo agregar, modificar, eliminar ya sea un turno, un grupo, una cancha o un jugador de la base de datos.
    
         while (!salir) {
            try {
                System.out.println("Menu Turnos:");
                System.out.println("1. Recibe mensaje");
                System.out.println("2. Agregar...(Turno, Grupo, Cancha, Jugador)");
                System.out.println("3. Modificar...(Turno, Grupo, Cancha, Jugador)");
                System.out.println("4. Eliminar...(Turno, Grupo, Cancha, Jugador)");
                System.out.println("5. Mostrar...(Turno, Grupo, Cancha, Jugador)");
                System.out.println("6. Salir");

                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        try (Connection connection = ConexionBD.getConnection()) {
                        recibirMensaje(connection); 
                        } catch (SQLException e) { 
                        e.printStackTrace();
                        }   
                        break;
                    case 2:
                        mostrarSubmenu("Agregar");
                        break;
                    case 3:
                        mostrarSubmenu("Modificar");
                        break;
                    case 4:
                        mostrarSubmenu("Eliminar");
                        break;
                    case 5:
                        mostrarSubmenuMostrar();
                        break;
                    case 6:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número.");
                scanner.next(); 
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // **********************************************************************************
    // Menu secundario:
    // **********************************************************************************
    public static void mostrarSubmenu(String accion) throws OpcionInvalidaException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(accion + "...");
        System.out.println("1. Jugador");
        System.out.println("2. Turno");
        System.out.println("3. Grupo");
        System.out.println("4. Cancha");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                if (accion.equals("Agregar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        agregarJugador(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Modificar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        modificarJugador(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Eliminar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        eliminarJugador(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if (accion.equals("Agregar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        agregarTurno(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Modificar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        modificarTurno(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Eliminar")) {
                   try (Connection connection = ConexionBD.getConnection()) {
                        eliminarTurno(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                 if (accion.equals("Agregar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        agregarGrupo(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Modificar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        modificarGrupo(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Eliminar")) {
                   try (Connection connection = ConexionBD.getConnection()) {
                        eliminarGrupo(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                }
                break;
            case 4:
                 if (accion.equals("Agregar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        agregarCancha(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Modificar")) {
                    try (Connection connection = ConexionBD.getConnection()) {
                        modificarCancha(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                } else if (accion.equals("Eliminar")) {
                   try (Connection connection = ConexionBD.getConnection()) {
                        eliminarCancha(connection); 
                    } catch (SQLException e) { 
                        e.printStackTrace();
                    }
                }
                break;
            default:
                throw new OpcionInvalidaException("Opción no válida en el submenú.");
        }
    
    }    
        
    // **********************************************************************************
    // Menu para mostrar contenido de cada base de datos:
    // **********************************************************************************
    public static void mostrarSubmenuMostrar() throws OpcionInvalidaException { 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mostrar...");
        System.out.println("1. Jugadores");
        System.out.println("2. Turnos");
        System.out.println("3. Grupos");
        System.out.println("4. Canchas");
        System.out.println("5. Inscriptos");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        
        switch (opcion) {
            case 1:
                System.out.println("Lista de Jugadores:");
                try (Connection connection = ConexionBD.getConnection()) {
                    JugadorCont jugadorCont = new JugadorCont(connection);
                    List<Jugador> jugadores = jugadorCont.getAllJugadores();
                    for (Jugador jugador : jugadores) {
                        System.out.println("ID de Jugador: " + jugador.getJugadorId() + ", Nombre: " + jugador.getNombre() + ", Edad: " + jugador.getEdad() + ", Contacto: " + jugador.getContacto() + ", Posicion: " + jugador.getPosicion() + ", Grupo: " + jugador.getGrupoId());
                        System.out.println("-------------------------------------------------------------------");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Lista de Turnos:");
                try (Connection connection = ConexionBD.getConnection()) {
                    TurnoCont turnoCont = new TurnoCont(connection);
                    List<Turno> turnos = turnoCont.getAllTurnos();
                    for (Turno turno : turnos) {
                        System.out.println("ID de Turno: " + turno.getTurnoId() + ", Hora: " + turno.getHora() + ", Duracion: " + turno.getDuracion() + ", Grupo: " + turno.getGrupoId() + ", Campo: " + turno.getCampoId());
                        System.out.println("-------------------------------------------------------------------");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Lista de Grupos:");
                
                try (Connection connection = ConexionBD.getConnection()) {
                    GrupoCont grupoCont = new GrupoCont(connection);
                    List<Grupo> grupos = grupoCont.getAllGrupos();
                    for (Grupo grupo : grupos) {
                        System.out.println("ID de Grupo: " + grupo.getGrupoId() + ", Nombre: " + grupo.getNombre() + ", Edad Máxima: " + grupo.getEdadMax() + ", Edad Mínima: " + grupo.getEdadMin());
                        System.out.println("-------------------------------------------------------------------");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                System.out.println("Lista de Canchas:");
                try (Connection connection = ConexionBD.getConnection()) {
                    CampoCont campoCont = new CampoCont(connection);
                    List<Campo> campos = campoCont.getAllCampos();
                    for (Campo campo : campos) {
                        System.out.println("ID de Cancha: " + campo.getCampoId() + ", Nombre: " + campo.getNombre() + ", Precio: " + campo.getPrecio() + ", Cantidad Jugadores: " + campo.getCantidadJugadores() + ", Tipo de Suelo: " + campo.getPiso());
                        System.out.println("-------------------------------------------------------------------");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println("Lista de inscriptos:");
                try (Connection connection = ConexionBD.getConnection()) {
                    JugadorTurnoCont jugadorTurnoCont = new JugadorTurnoCont(connection);
                    List<JugadorTurno> jugadoresT = jugadorTurnoCont.getAllJugadorTurnos();
                    for (JugadorTurno jugadorTurno : jugadoresT) {
                        System.out.println("ID inscripcion: " + jugadorTurno.getJtId() + ", ID de turno: " + jugadorTurno.getTurnoId() + ", ID de jugador: " + jugadorTurno.getJugadorId());
                        System.out.println("-------------------------------------------------------------------");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new OpcionInvalidaException("Opción no válida en el submenú.");
        }
    
    }
    
    // Los siguientes metodos permiten que, pidiendo mediante un ID nos devuelva el objeto que tiene ese ID,
    // ya sea un turno, jugador, cancha o grupo
    
    // Devuelve el turno que coincide con el ID en la base de datos de turnos, caso contrario devuelve null
    public static Turno turnoConId(int id) {
        try (Connection connection = ConexionBD.getConnection()) {
            TurnoCont turnoCont = new TurnoCont(connection);
            List<Turno> turnos = turnoCont.getAllTurnos();
            for (Turno turno : turnos) {
                if (turno.getTurnoId() == id) {
                    return turno;
                }
            }
        } catch (SQLException e) {
                    e.printStackTrace();
        }
        return null;
    }
    // Devuelve el jugador que coincide con el ID de la base de datos de jugadores, caso contrario devuelve null
    public static Jugador jugadorConId(int id) {
        try (Connection connection = ConexionBD.getConnection()) {
            JugadorCont jugadorCont = new JugadorCont(connection);
            List<Jugador> jugadores = jugadorCont.getAllJugadores();
            for (Jugador jugador : jugadores) {
                if (jugador.getJugadorId() == id) {
                return jugador;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        // Devuelve la cancha que coincide con el ID de la base de datos de canchas, caso contrario devuelve null
    public static Campo campoConId(int id) {
        try (Connection connection = ConexionBD.getConnection()) {
            CampoCont campoCont = new CampoCont(connection);
            List<Campo> campos = campoCont.getAllCampos();
            for (Campo campo : campos) {
                if (campo.getCampoId() == id) {
                return campo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
         // Devuelve el grupo que coincide con el ID de la base de datos de grupós, caso contrario devuelve null
    public static Grupo grupoConId(int id) {
        try (Connection connection = ConexionBD.getConnection()) {
            GrupoCont grupoCont = new GrupoCont(connection);
            List<Grupo> grupos = grupoCont.getAllGrupos();
            for (Grupo grupo : grupos) {
                if (grupo.getGrupoId() == id) {
                return grupo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    // **********************************************************************************
    // Menu que permite ingresar los datos que llegarian en el mensaje del jugador:
    // **********************************************************************************
    public static void recibirMensaje(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Primero debera ingresar la combinacion de ID del turno y el ID del Jugador: ");
        System.out.print("Ingrese el ID del turno: ");
        int idTurno = scanner.nextInt();
        Turno turnoSeleccionado = turnoConId(idTurno);
        if (turnoSeleccionado == null) {
            System.out.println("Turno no encontrado.");
            return;
        }
        
        System.out.print("Ingrese el ID del jugador: ");
        int idJugador = scanner.nextInt();
        Jugador jugadorSeleccionado = jugadorConId(idJugador);
        if (jugadorSeleccionado == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }
        
        System.out.println("Seleccione una opción:");
        System.out.println("1. Agregar jugador a turno");
        System.out.println("2. Eliminar jugador de turno");
        int opcion = scanner.nextInt();
        int jtId = 0;        
        switch (opcion) {
            case 1:
                try {
                    JugadorTurnoDatos jugadorTurnoDatos = new JugadorTurnoDatos(connection);
                    JugadorTurno nuevoJugadorTurno = new JugadorTurno();
                    nuevoJugadorTurno.setTurnoId(idTurno);
                    nuevoJugadorTurno.setJugadorId(idJugador);
                    
                    jugadorTurnoDatos.agregarJugadorTurno(nuevoJugadorTurno);
                    System.out.println("Jugador agregado exitosamente."); 
                    
                } catch (SQLException e) { 
                    e.printStackTrace(); 
                }
                break;
            case 2:
                try {
                    //try (Connection connection = ConexionBD.getConnection()) {
                    JugadorTurnoCont jugadorTurnoCont = new JugadorTurnoCont(connection);
                    List<JugadorTurno> turnos = jugadorTurnoCont.getAllJugadorTurnos();
                    for (JugadorTurno jugadorTurno : turnos) {
                        if ((jugadorTurno.getJugadorId() == idJugador) && (jugadorTurno.getTurnoId() == idTurno)) {
                            jtId = jugadorTurno.getJtId();
                        }
                    }
                    
                    if (jtId != 0) {
                        JugadorTurnoDatos jugadorTurnoDatos = new JugadorTurnoDatos(connection);
                        jugadorTurnoDatos.eliminarJugadorTurno(jtId);
                        
                    }
                } catch (SQLException e) { 
                    e.printStackTrace();
                    System.out.println("El jugador no se encuentra en el turno.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    
    // **********************************************************************************
    // Manejo de los jugadoress:
    // **********************************************************************************
    
    //Permite agregar un nuevo jugador a la base de datos de jugadores
    public static void agregarJugador(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        // System.out.print("Ingrese el ID del jugador: ");
        // int id = scanner.nextInt();
        // scanner.nextLine();  
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono del jugador: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese la edad del jugador: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la posicion del jugador: ");
        String posicion = scanner.nextLine();
        System.out.print("Ingrese la valoracion del jugador: ");
        int valoracion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese ID del grupo del jugador: ");
        int grupoId = scanner.nextInt();
        scanner.nextLine();
        
        try { 
            // Crear instancia de JugadorDatos y JugadorTurnoDatos
            JugadorDatos jugadorDatos = new JugadorDatos(connection); 
            // Crear nuevo jugador 
            Jugador nuevoJugador = new Jugador(); 
            nuevoJugador.setNombre(nombre); 
            nuevoJugador.setEdad(edad); 
            nuevoJugador.setContacto(telefono); 
            nuevoJugador.setPosicion(posicion); 
            nuevoJugador.setValoracion(valoracion); 
            nuevoJugador.setGrupoId(grupoId);
            
            // Agrega jugador a la base de datos 
            jugadorDatos.agregarJugador(nuevoJugador);
            
            System.out.println("Jugador agregado exitosamente."); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al agregar jugador."); 
        }
        
    }
    
    //Permite modificar un jugador de la lista de jugadores.
    public static void modificarJugador(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del jugador a modificar: ");
        int idJugador = scanner.nextInt();
        scanner.nextLine(); 
        JugadorDatos jugadorDatos = new JugadorDatos(connection); 
        Jugador jugadorSeleccionado = jugadorConId(idJugador);
        
        if (jugadorSeleccionado == null) {
            System.out.println("El jugador no existe.");
            return;
        }

        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Contacto");
        System.out.println("3. Edad");
        System.out.println("4. Posicion");
        System.out.println("5. Valoracion");
        System.out.println("6. Id del grupo");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre del jugador: ");
                String nuevoNombre = scanner.nextLine();
                jugadorSeleccionado.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese el nuevo contacto del jugador: ");
                String nuevoContacto = scanner.nextLine();
                jugadorSeleccionado.setContacto(nuevoContacto);
                break;
            case 3:
                System.out.print("Ingrese la nueva edad del jugador: ");
                int nuevaEdad = scanner.nextInt();
                scanner.nextLine();
                jugadorSeleccionado.setEdad(nuevaEdad);
                break;
            case 4:
                System.out.print("Ingrese la nueva posicion del jugador: ");
                String nuevaPosicion = scanner.nextLine();
                jugadorSeleccionado.setPosicion(nuevaPosicion);
                break;
            case 5:
                System.out.print("Ingrese la nueva valoracion del jugador: ");
                int nuevaValoracion = scanner.nextInt();
                scanner.nextLine();
                jugadorSeleccionado.setValoracion(nuevaValoracion);
                break;
            case 6:
                System.out.print("Ingrese la nueva ID Grupo del jugador: ");
                int nuevaIdGrupo = scanner.nextInt();
                scanner.nextLine();
                jugadorSeleccionado.setGrupoId(nuevaIdGrupo);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        
        jugadorDatos.modificarJugador(jugadorSeleccionado);
        
        System.out.println("Jugador actualizado.");
    }
    
    //Permite eliminar un jugador de la lista de jugadores.
    public static void eliminarJugador(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del jugador: ");
        int id = scanner.nextInt();
        
        JugadorDatos jugadorDatos = new JugadorDatos(connection);
        Jugador jugadorSeleccionado = jugadorConId(id);
        
        if (jugadorSeleccionado != null) {
            jugadorDatos.eliminarJugador(id);
            
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    // **********************************************************************************
    // Manejo de los turnos:
    // **********************************************************************************
    // Permite agregar un nuevo turno a la base de datos de turnos
    public static void agregarTurno(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha del turno: aaaa-mm-dd hh:mm:ss");
        String fecha = scanner.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime hora = LocalDateTime.parse("1900-01-01 00:00:00",formato);
        try { 
            hora = LocalDateTime.parse(fecha,formato);
        } catch (Exception e) {
            System.out.println("El formato de fecha ingresado no es el correcto.");
        }
        System.out.print("Ingrese la duracion del turno: ");
        int duracion = scanner.nextInt();
        System.out.print("Ingrese el ID del Grupo al que pertenece el turno: ");
        int idGrupo = scanner.nextInt();
        System.out.print("Ingrese ID del lugar del turno: ");
        int idLugar = scanner.nextInt();
        
        
        try {
            TurnoDatos turnoDatos = new TurnoDatos(connection);
            
            Turno nuevoTurno = new Turno();
            nuevoTurno.setHora(hora);
            nuevoTurno.setDuracion(duracion);
            nuevoTurno.setGrupoId(idGrupo);
            nuevoTurno.setCampoId(idLugar);
            
            turnoDatos.agregarTurno(nuevoTurno);
            
            System.out.println("Turno agregado exitosamente."); 
        } catch (SQLException e) { 
            //e.printStackTrace(); 
            System.out.println("Error al agregar turno."); 
        }
    }
    
    // Permite modificar un turno de la base de datos de turnos
    public static void modificarTurno(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del turno a modificar: ");
        int idTurno = scanner.nextInt();
        scanner.nextLine(); 
        
        TurnoDatos turnoDatos = new TurnoDatos(connection);
        Turno turnoSeleccionado = turnoConId(idTurno);
        
        if (turnoSeleccionado == null) {
            System.out.println("El jugador no existe.");
            return;
        }
        
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Fecha");
        System.out.println("2. Duracion");
        System.out.println("3. Id del Grupo");
        System.out.println("4. Id del Lugar");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
                
        switch (opcion) {
            case 1:
                System.out.println("Ingrese la nueva fecha del turno: aaaa-mm-dd hh:mm:ss");
                String fecha = scanner.nextLine();
                
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime hora = LocalDateTime.parse("1900-01-01 00:00:00",formato);
                try { 
                    hora = LocalDateTime.parse(fecha,formato);
                } catch (Exception e) {
                    System.out.println("El formato de fecha ingresado no es el correcto.");
                }
                
                
                //LocalDateTime hora = LocalDateTime.parse(fecha);
                turnoSeleccionado.setHora(hora);
                break;
            case 2:
                System.out.print("Ingrese el nueva duracion del turno: ");
                int nuevaDuracion = scanner.nextInt();
                scanner.nextLine();
                turnoSeleccionado.setDuracion(nuevaDuracion);
                break;
            case 3:
                System.out.print("Ingrese la nueva id del grupo del turno: ");
                int nuevaIdGrupo = scanner.nextInt();
                scanner.nextLine();
                turnoSeleccionado.setGrupoId(nuevaIdGrupo);
                break;
            case 4:
                System.out.print("Ingrese el nuevo id del nuevo lugar del turno: ");
                int nuevoIdLugar = scanner.nextInt();
                scanner.nextLine();
                turnoSeleccionado.setCampoId(nuevoIdLugar);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        
        turnoDatos.modificarTurno(turnoSeleccionado);
        
    }

    // Permite eliminar un turno de la base de datos de turnos
    public static void eliminarTurno(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del turno: ");
        int id = scanner.nextInt();
        
        TurnoDatos turnoDatos = new TurnoDatos(connection);
        Turno turnoSeleccionado = turnoConId(id);
        
        
        if (turnoSeleccionado != null) {
            turnoDatos.eliminarTurno(id);
        } else {
            System.out.println("Turno no encontrado.");
        }
    }

    // **********************************************************************************
    // Manejo de los grupos:
    // **********************************************************************************
    
    // Permite agregar un nuevo grupo a la base de datos de grupos
    public static void agregarGrupo(Connection connection) {
        Scanner scanner = new Scanner(System.in);
               
        System.out.print("Ingrese el nombre del grupo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el Edad maxima del grupo: ");
        int eMax = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el Edad maxima del grupo: ");
        int eMin = scanner.nextInt();
        scanner.nextLine();
        
        try {
            GrupoDatos grupoDatos = new GrupoDatos(connection);
            
            Grupo nuevoGrupo = new Grupo();
            nuevoGrupo.setNombre(nombre);
            nuevoGrupo.setEdadMax(eMax);
            nuevoGrupo.setEdadMin(eMin);
                       
            grupoDatos.agregarGrupo(nuevoGrupo);
            
            System.out.println("Grupo agregado exitosamente."); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al agregar grupo."); 
        }
            
    }

    // Permite modificar un grupo de la base de datos de grupos
    public static void modificarGrupo(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del grupo a modificar: ");
        int idGrupo = scanner.nextInt();
        scanner.nextLine(); 
        
        GrupoDatos grupoDatos = new GrupoDatos(connection);
        Grupo grupoSeleccionado = grupoConId(idGrupo);
        
        if ( grupoSeleccionado == null ) {
            System.out.println("El grupo no existe.");
            return;
        }
        
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Edad minima");
        System.out.println("3. Edad maxima");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
                
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre del grupo: ");
                String nuevoNombre = scanner.nextLine();
                grupoSeleccionado.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese edad minima para el grupo: ");
                int eMin = scanner.nextInt();
                scanner.nextLine();
                grupoSeleccionado.setEdadMin(eMin);
                break;
            case 3:
                System.out.print("Ingrese edad maxima para el grupo: ");
                int eMax = scanner.nextInt();
                scanner.nextLine();
                grupoSeleccionado.setEdadMax(eMax);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        
        grupoDatos.modificarGrupo(grupoSeleccionado);
        System.out.println("Grupo modificado.");
    }

    //Permite eliminar un grupo de la base de datos de grupos
    public static void eliminarGrupo(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del grupo: ");
        int id = scanner.nextInt();
        
        GrupoDatos grupoDatos = new GrupoDatos(connection);
        Grupo grupoSeleccionado = grupoConId(id);
        
        if (grupoSeleccionado != null) {
            grupoDatos.eliminarGrupo(id);
            
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    // **********************************************************************************
    // Manejo de los canchas:
    // **********************************************************************************
    // Permite agregar una nueva cancha a la base de datos de canchas
    public static void agregarCancha(Connection connection) {
        Scanner scanner = new Scanner(System.in);
                       
        System.out.print("Ingrese el nombre de la cancha: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio de la cancha: ");
        int precio = scanner.nextInt();
        scanner.nextLine();
        BigDecimal pr = BigDecimal.valueOf(precio);
        System.out.print("Ingrese el capacidad de la cancha: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de superficie de la cancha: ");
        String piso = scanner.nextLine();
        
        try {
            CampoDatos campoDatos = new CampoDatos(connection);
            
            Campo nuevoCampo = new Campo();
            nuevoCampo.setNombre(nombre);
            nuevoCampo.setPrecio(pr);
            nuevoCampo.setCantidadJugadores(capacidad);
            nuevoCampo.setPiso(piso);
            
            campoDatos.agregarCampo(nuevoCampo);
            
            System.out.println("Cancha agregada exitosamente."); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al agregar cancha."); 
        }
        
    }
    // Permite modificar una cancha existente
        public static void modificarCancha(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cancha a modificar: ");
        int idCancha = scanner.nextInt();
        scanner.nextLine(); 
        
        CampoDatos campoDatos = new CampoDatos(connection);
        Campo campoSeleccionado = campoConId(idCancha);
        
        
        if (campoSeleccionado == null) {
            System.out.println("La cancha no existe.");
            return;
        }

        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Precio");
        System.out.println("3. Cantidad de jugadores");
        System.out.println("4. Tipo de superfice");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre de la cancha: ");
                String nuevoNombre = scanner.nextLine();
                campoSeleccionado.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese el precio de la cancha: ");
                int precio = scanner.nextInt();
                BigDecimal pr = BigDecimal.valueOf(precio);
                campoSeleccionado.setPrecio(pr);
                break;
            case 3:
                System.out.print("Ingrese la cantidad de jugadores de la cancha: ");
                int cantidad = scanner.nextInt();
                campoSeleccionado.setCantidadJugadores(cantidad);
                break;
            case 4:
                System.out.print("Ingrese el tipo de superficie de la cancha: ");
                String nuevoPiso = scanner.nextLine();
                campoSeleccionado.setPiso(nuevoPiso);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        
        campoDatos.modificarCampo(campoSeleccionado);
        System.out.println("Cancha modificada.");
    }
    // Permite eliminar una cancha existente
    public static void eliminarCancha(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la cancha: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        CampoDatos campoDatos = new CampoDatos(connection);
        Campo campoSeleccionado = campoConId(id);
        
        if (campoSeleccionado != null) {
            campoDatos.eliminarCampo(id);
            
        } else {
            System.out.println("Cancha no encontrada.");
        }
    }
}    


