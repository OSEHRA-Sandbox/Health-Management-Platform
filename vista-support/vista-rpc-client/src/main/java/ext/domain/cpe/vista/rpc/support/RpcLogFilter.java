package EXT.DOMAIN.cpe.vista.rpc.support;

import EXT.DOMAIN.cpe.vista.rpc.RpcEvent;

/**
 * A Filter can be used to provide fine grain control over which RPCs are logged.
 */
public interface RpcLogFilter {
    /**
     * Check if a given RPC event should be logged.
     *
     * @param rpcEvent the event that occurred.
     */
    boolean isLoggable(RpcEvent rpcEvent);
}