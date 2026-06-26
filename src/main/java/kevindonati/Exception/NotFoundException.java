package kevindonati.Exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Elemento non trovato");
    }

    public NotFoundException(String id) {
        super("Elemento con id: " + id + " non trovato");
    }
}
