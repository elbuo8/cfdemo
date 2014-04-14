package controllers;

import play.*;
import play.mvc.*;
import com.sendgrid.*;

import views.html.*;

public class Application extends Controller {

    private static SendGrid sg = new SendGrid(System.getenv("SG_USER"), System.getenv("SG_PWD"));

    public static Result register() {
        Mail m = new Mail();
        m.addTo(request().getQueryString("email"));
        m.setFrom("yamil@sendgrid.com");
        m.setSubject("Welcome to the Cloud Foundry + SendGrid Demo");
        m.setText("It has been awesome showing you this fake signup form!");
        try {
            SendGridResponse resp = sg.send(m);
            if (resp.getStatus()) {
                return redirect("/success");
            } else {
                return ok("Email fail");
            }
        } catch(SendGridException e) {
            e.printStackTrace();
            return ok("Fail");
        }
    }

}
