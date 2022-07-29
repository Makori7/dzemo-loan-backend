package loan.application.co.dzemoloanbackend.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UniversalResponseWrapper {

    private int responseCode;
    private String responseMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object object;


    public UniversalResponseWrapper(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }



    public UniversalResponseWrapper(int responseCode, String responseMessage, Object object) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.object = object;
    }
}
