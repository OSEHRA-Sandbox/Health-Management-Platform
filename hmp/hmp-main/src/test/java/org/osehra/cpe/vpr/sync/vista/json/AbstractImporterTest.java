package org.osehra.cpe.vpr.sync.vista.json;

import org.osehra.cpe.vpr.Patient;
import org.junit.Before;

public class AbstractImporterTest {
    protected static final String MOCK_PID = "34";

    protected Patient mockPatient;

    @Before
    public void setUp() throws Exception {
        mockPatient = new Patient();
        mockPatient.setData("pid", MOCK_PID);
    }
}
