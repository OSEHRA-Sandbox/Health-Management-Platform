package org.osehra.cpe.vista.rpc.broker.conn;

import org.osehra.cpe.vista.rpc.LinesFromRpcResponseExtractor;
import org.osehra.cpe.vista.rpc.RpcResponse;
import org.osehra.cpe.vista.rpc.RpcResponseExtractionException;
import org.osehra.cpe.vista.rpc.RpcResponseExtractor;
import org.springframework.dao.DataAccessException;

public class VistaSystemInfoResponseExtractor implements RpcResponseExtractor<VistaSystemInfo> {

    private LinesFromRpcResponseExtractor linesFromExtractor = new LinesFromRpcResponseExtractor();

    @Override
    public VistaSystemInfo extractData(RpcResponse response) throws RpcResponseExtractionException {
        String[] lines = linesFromExtractor.extractData(response);
        VistaSystemInfo info = new VistaSystemInfo();
        info.setServer(lines[0]);
        info.setVolume(lines[1]);
        info.setUCI(lines[2]);
        info.setDevice(lines[3]);
        if (lines.length > 7) {
            info.setDomainName(lines[6]);
            if ("1".equals(lines[7]))
                info.setProductionAccount(true);
        }
        return info;
    }
}
