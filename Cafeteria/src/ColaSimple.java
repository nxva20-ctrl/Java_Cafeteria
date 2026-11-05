public class ColaSimple<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public ColaSimple() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) return null;
        return frente.getDato();
    }

    public int getTamanio() {
        return tamanio;
    }
}