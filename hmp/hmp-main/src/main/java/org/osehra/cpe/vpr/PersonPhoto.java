package org.osehra.cpe.vpr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.osehra.cpe.vpr.pom.AbstractPOMObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

public class PersonPhoto extends AbstractPOMObject {

    public static final String DEFAULT_CONTENT_TYPE = "image/png;base64";

    private String personUid;
    private String contentType;
    private String imageData;

    public PersonPhoto(String personUid, String contentType, String base64ImageData) {
        super(null);
        String uid = personUid.replace("user", "personphoto");
        setData("uid", uid);
        setData("personUid", personUid);
        setData("contentType", contentType);
        setData("imageData", base64ImageData);
    }

    public PersonPhoto(String personUid, String contentType, byte[] binaryImageData) {
        this(personUid, contentType, Base64.encodeBase64String(binaryImageData));
    }

    public PersonPhoto(String personUid, String base64ImageData) {
        this(personUid, DEFAULT_CONTENT_TYPE, base64ImageData);
    }

    public PersonPhoto(String personUid, byte[] binaryImageData) {
        this(personUid, DEFAULT_CONTENT_TYPE, Base64.encodeBase64String(binaryImageData));
    }

    public String getPersonUid() {
        return personUid;
    }

    public String getContentType() {
        return contentType;
    }

    public String getImageData() {
        return imageData;
    }

    @JsonIgnore
    public byte[] getImageBytes() {
        return Base64.decodeBase64(imageData);
    }
}
