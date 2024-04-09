package gov.iti.jets.Exceptions.ExceptionMessages;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
