package org.osehra.cpe.vista.rpc.broker.protocol;

public interface TransportMetrics {
    /**
     * Returns the number of bytes transferred.
     */
    long getBytesTransferred();

    /**
     * Resets the counts
     */
    void reset();
}
