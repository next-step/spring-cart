package cart.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidErrorResponse {

    private List<ErrorResponse> errorResponses;

}
