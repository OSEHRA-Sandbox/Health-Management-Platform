package org.osehra.cpe.vpr;

import java.util.Map;

import org.osehra.cpe.vpr.pom.AbstractPatientObject;

public class Diagnosis extends AbstractPatientObject {

    private String facilityCode;
	private String facilityName;
    private String diagnosis;
    private String assignToName;
    private String assignToCode;
    private String ownerName;
    private String ownerCode;
    
    public String getFacilityCode() {
		return facilityCode;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public String getAssignToName() {
		return assignToName;
	}

	public String getAssignToCode() {
		return assignToCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

    public Diagnosis() {
        super(null);
    }

	public Diagnosis(Map<String, Object> vals) {
		super(vals);
		// TODO Auto-generated constructor stub
	}

}
