import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Estructuras de datos personalizadas del proyecto
        ListaEnlazada<Producto> catalogo = new ListaEnlazada<>();
        ColaSimple<Pedido> colaPedidos = new ColaSimple<>();
        Pila<String> historialAcciones = new Pila<>();

        // Precargar algunos productos iniciales al catálogo
        catalogo.insertarAlFinal(new Producto("P01", "Café Americano", 25.0, "Bebidas"));
        catalogo.insertarAlFinal(new Producto("P02", "Panini de Pollo", 45.0, "Alimentos"));
        catalogo.insertarAlFinal(new Producto("P03", "Jugo Natural", 30.0, "Bebidas"));

        int opcion = 0;

        do {
            try {
                System.out.println("\n========================================");
                System.out.println("   SISTEMA DE COMANDAS - CAFETERÍA UPVM");
                System.out.println("========================================");
                System.out.println("1. Ver Catálogo de Productos");
                System.out.println("2. Registrar y Encolar Nuevo Pedido");
                System.out.println("3. Despachar Siguiente Pedido (Cocina)");
                System.out.println("4. Ver Última Acción Registrada (Pila/Undo)");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- CATÁLOGO DE PRODUCTOS ---");
                        Nodo<Producto> actual = catalogo.getCabeza();
                        if (actual == null) {
                            System.out.println("El catálogo está vacío.");
                        } else {
                            while (actual != null) {
                                System.out.println(actual.getDato());
                                actual = actual.getSiguiente();
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- REGISTRAR NUEVO PEDIDO ---");
                        System.out.print("Ingrese el ID del pedido (ej. T01): ");
                        String idPed = scanner.nextLine();
                        System.out.print("Ingrese el nombre del cliente: ");
                        String cliente = scanner.nextLine();

                        Pedido nuevoPedido = new Pedido(idPed, cliente);

                        // Añadimos el primer producto del catálogo como ejemplo para el pedido
                        Nodo<Producto> primerProd = catalogo.getCabeza();
                        if (primerProd != null) {
                            nuevoPedido.agregarProducto(primerProd.getDato());
                            System.out.println("Producto añadido al pedido: " + primerProd.getDato().getNombre());
                        }

                        // Se encola bajo el principio FIFO exigido
                        colaPedidos.encolar(nuevoPedido);
                        historialAcciones.apilar("Pedido registrado para: " + cliente);
                        System.out.println("¡Pedido encolado exitosamente en la posición de espera!");
                        break;

                    case 3:
                        System.out.println("\n--- COCINA: DESPACHAR PEDIDO ---");
                        Pedido proximo = colaPedidos.desencolar();
                        if (proximo == null) {
                            System.out.println("No hay pedidos pendientes en la cola.");
                        } else {
                            proximo.setEstado("Entregado");
                            System.out.println("Despachando -> " + proximo);
                            historialAcciones.apilar("Pedido despachado: " + proximo.getIdPedido());
                        }
                        break;

                    case 4:
                        System.out.println("\n--- HISTORIAL DE ACCIONES (PILA) ---");
                        if (historialAcciones.estaVacia()) {
                            System.out.println("No hay acciones en el historial.");
                        } else {
                            System.out.println("Última acción en la cima: " + historialAcciones.verCima());
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema de cafetería...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Debe ingresar un número entero válido.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}