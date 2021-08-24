package domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieEvent {

    private String id;
    private String movieId;
    private Date date;
}
