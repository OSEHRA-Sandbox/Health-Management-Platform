package org.osehra.cpe.vpr;

import java.util.Map;

import org.osehra.cpe.vpr.pom.AbstractPOMObject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

public class PatientRace extends AbstractPOMObject{

	private Long id;
    private Patient patient;
    private String code;
    private String name;
    private String vuid;
    
    public PatientRace() {
    	super(null);
    }
    
    @JsonCreator
    public PatientRace(Map<String, Object> vals) {
    	super(vals);
    }

    public Long getId() {
		return id;
	}

    @JsonBackReference("patient-race")
	public Patient getPatient() {
		return patient;
	}

    void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getVuid() {
		return vuid;
	}
}
