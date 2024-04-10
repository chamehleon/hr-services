package gov.iti.jets.Exceptions;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
        private String errorMessage;
        private int errorCode;
        private String errorDescription;
        // constructor
        // setters & getters
}


