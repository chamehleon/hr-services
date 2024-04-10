package gov.iti.jets.Utils;


import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
@JsonbPropertyOrder({"data", "status", "message", "link"})
public class GeneralResponse<T> {
    private Response.Status status;
    private String message;
    private T data;
    private String link = null;

    public GeneralResponse(Response.Status status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GeneralResponse(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public GeneralResponse(Response.Status status, String message, T data, String link) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.link = link;
    }



}