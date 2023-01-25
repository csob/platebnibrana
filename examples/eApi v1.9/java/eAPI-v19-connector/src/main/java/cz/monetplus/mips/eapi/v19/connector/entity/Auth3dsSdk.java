package cz.monetplus.mips.eapi.v19.connector.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Auth3dsSdk extends ApiBase implements Signable {

    private static final long serialVersionUID = -3825192932302805075L;


    /**
     * Universally unique ID created upon all installations and updates of the
     * 3DS Requestor APp on a Customer Device. This will be newly generated and
     * stored by the 3DS SDK for each installation or update. The field is
     * limited to 36 characters and it shall have a canonical format as defined
     * in IETF RFC 4122. This may utilise any of the specified versions as long
     * as the output meets specified requirements.
     */
    private String appID;

    /**
     * JWE Object as defined Section 6.2.2.1 containing data encrypted by the
     * SDK for the DS to decrypt. This element is the only field encrypted in
     * this version of the EMV 3-D Secure specification. The field is sent from
     * the SDK and it is limited to 64.000 characters. The data will be present
     * when sending to DS, but not present from DS to ACS.
     */
    private String encData;

    /**
     * Public key component of the ephemeral key pair generated by the 3DS SDK
     * and used to establish session keys between the 3DS SDK and ACS. In AReq,
     * this data element is contained within the ACS Signed Content JWS Object.
     * The field is limited to maximum 256 characters.
     */
    private String ephemPubKey;

    /**
     * Indicates the maximum amount of time (in minutes) for all exchanges. The
     * field shall have value greater or equals than 05.
     */
    private Integer maxTimeout;

    /**
     * Identifies the vendor and version of the 3DS SDK that is integrated in a
     * 3DS Requestor App, assigned by EMVCo when the 3DS SDK is approved. The
     * field is limited to 32 characters.
     */
    private String referenceNumber;

    /**
     * Universally unique transaction identifier assigned by the 3DS SDK to
     * identify a single transaction. The field is limited to 36 characters and
     * it shall be in a canonical format as defined in IETF RFC 4122. This may
     * utilise any of the specified versions as long as the output meets
     * specific requirements.
     */
    private String transID;


    @Override
    public String toSign() {
        StringBuilder sb = new StringBuilder();
        add(sb, appID);
        add(sb, encData);
        add(sb, ephemPubKey);
        add(sb, maxTimeout);
        add(sb, referenceNumber);
        add(sb, transID);
        deleteLast(sb);
        return sb.toString();
    }
}
