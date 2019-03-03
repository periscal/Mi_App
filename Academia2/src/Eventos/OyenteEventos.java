package Eventos;
import java.util.EventListener;

public interface OyenteEventos extends EventListener {
    void capturarMiEvento( MiObjectoEvento evt );
    }
