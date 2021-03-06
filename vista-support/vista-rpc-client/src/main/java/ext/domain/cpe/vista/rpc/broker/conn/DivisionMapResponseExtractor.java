package org.osehra.cpe.vista.rpc.broker.conn;

import org.osehra.cpe.vista.rpc.LinesFromRpcResponseExtractor;
import org.osehra.cpe.vista.rpc.RpcResponse;
import org.osehra.cpe.vista.rpc.RpcResponseExtractionException;
import org.osehra.cpe.vista.rpc.RpcResponseExtractor;
import org.osehra.cpe.vista.util.VistaStringUtils;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.Map;

public class DivisionMapResponseExtractor implements RpcResponseExtractor<Map<String, String>> {

    private LinesFromRpcResponseExtractor linesFromRpcResponseExtractor = new LinesFromRpcResponseExtractor();

    @Override
    public Map<String, String> extractData(RpcResponse response) throws RpcResponseExtractionException {
        Map<String, String> divisions = new HashMap<String, String>();

        String[] lines = response.toLines();

        int divisionCount = Integer.parseInt(lines[0]);
        if (divisionCount <= 0) return divisions;

        for (int i = 1; i < lines.length; i++) {
            String division = VistaStringUtils.piece(lines[i], VistaStringUtils.U, 3);
            String divisionName = VistaStringUtils.piece(lines[i], VistaStringUtils.U, 2);
            divisions.put(division, divisionName);
        }

        return divisions;
    }
}
