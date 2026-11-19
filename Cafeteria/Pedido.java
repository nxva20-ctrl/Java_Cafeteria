public class Pedido {
    private String idPedido;
    private String nombreCliente;
    private ListaEnlazada<Producto> productosPedido;
    private String estado;

    public Pedido(String idPedido, String nombreCliente) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.productosPedido = new ListaEnlazada<>();
        this.estado = "En Espera";
    }

    public void agregarProducto(Producto producto) {
        productosPedido.insertarAlFinal(producto);
    }

    public double calcularTotal() {
        double total = 0.0;
        Nodo<Producto> actual = productosPedido.getCabeza();

        while (actual != null) {
            total += actual.getDato().getPrecio();
            actual = actual.getSiguiente();
        }
        return total;
    }

    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public ListaEnlazada<Producto> getProductosPedido() { return productosPedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " | Cliente: " + nombreCliente + " | Estado: " + estado + " | Total: $" + calcularTotal();
    }
}