package ba.fit.java.spring.mvc.viewmodels;

import java.util.Date;

public class Row
{
    public Date vrijemeLogiranja ;
    public String token ;
    public String ipAdresa;

    public Row(Date vrijemeLogiranja, String token, String ipAdresa) {
        this.vrijemeLogiranja = vrijemeLogiranja;
        this.token = token;
        this.ipAdresa = ipAdresa;
    }

    public Date getVrijemeLogiranja() {
        return vrijemeLogiranja;
    }

    public String getToken() {
        return token;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }
}
