package loan.application.co.dzemoloanbackend.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchWrapper {

    private LocalDate from;
    private LocalDate to;
    private String accountNumber;
    private int page;
    private int size;
}
