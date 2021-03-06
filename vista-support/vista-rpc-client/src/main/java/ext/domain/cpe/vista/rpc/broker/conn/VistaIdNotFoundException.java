package org.osehra.cpe.vista.rpc.broker.conn;

import org.osehra.cpe.vista.rpc.RpcException;

public class VistaIdNotFoundException extends RpcException {
    private String vistaId;

    public VistaIdNotFoundException(String vistaId) {
        super("Unknown vistaId " + vistaId);
        this.vistaId = vistaId;
    }

    public VistaIdNotFoundException(String vistaId, Throwable cause) {
        super("Unknown vistaId " + vistaId, cause);
        this.vistaId = vistaId;
    }

    public String getVistaId() {
        return vistaId;
    }
}
