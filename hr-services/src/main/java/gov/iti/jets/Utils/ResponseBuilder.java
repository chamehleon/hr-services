package gov.iti.jets.Utils;

import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;

public class ResponseBuilder {

    public static <T> Response SUCCESS (T data) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.OK, "Request Success", data);
        return Response.ok(generalResponse).build();
    }

    public static <T> Response CREATED(T data, Link link) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.CREATED, "Entity created", data, link.getUri().toString());
        return Response.status(Response.Status.CREATED).entity(generalResponse).build();
    }

    public static <T> Response NOT_FOUND () {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.NOT_FOUND, "Entity Not Found");
        return Response.status(Response.Status.NOT_FOUND).entity(generalResponse).build();
    }

    public static <T> Response BAD_REQUEST (T data) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.BAD_REQUEST, "Bad Request", data);
        return Response.status(Response.Status.BAD_REQUEST).entity(generalResponse).build();
    }

    public static <T> Response OK () {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.OK, "Request Success but, Empty Response");
        return Response.ok(generalResponse).build();
    }

    public static <T> Response DELETED () {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.OK, "Entity Deleted Successfully");
        return Response.ok(generalResponse).build();
    }

    public static <T> Response ERROR (T data) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>(Response.Status.INTERNAL_SERVER_ERROR, "Fatal Error", data);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(generalResponse).build();
    }

    // Add more methods for other response statuses as needed...
}