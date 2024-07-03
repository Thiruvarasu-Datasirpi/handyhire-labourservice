package handyhire.labourservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    public int statuscode;
    public String statusdescription;
    public Object details;



    public Response(int statuscode, String statusdescription, Object details) {
        this.statuscode = statuscode;
        this.statusdescription = statusdescription;
        this.details = details;
    }

    public Response(int statuscode, String statusdescription) {
        this.statuscode = statuscode;
        this.statusdescription = statusdescription;
    }


}


