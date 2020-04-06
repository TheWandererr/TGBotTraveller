package bot.web.response.details;


import lombok.Data;

@Data
public class ResponseDetails {

    private String message;

    public ResponseDetails(String message) {
        this.message = message;
    }

    public static ResponseDetails success() {
        return new ResponseDetails("success");
    }
}
