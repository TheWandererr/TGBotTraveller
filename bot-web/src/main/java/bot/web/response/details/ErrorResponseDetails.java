package bot.web.response.details;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResponseDetails extends ResponseDetails {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
    private String details;

    public ErrorResponseDetails(Date timestamp, String message, String details) {
        super(message);
        this.timestamp = timestamp;
        this.details = details;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
