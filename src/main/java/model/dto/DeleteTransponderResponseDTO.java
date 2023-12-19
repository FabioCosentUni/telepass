package model.dto;

public class DeleteTransponderResponseDTO {


    private String transponderId;
    private String code;

    public String getTransponderId() {
        return transponderId;
    }

    public void setTransponderId(String transponderId) {
        this.transponderId = transponderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
