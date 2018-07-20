package ba.fit.java.spring.mvc.viewmodels;

import java.util.Date;

public class SesijaIndexRow
{
    public Date vrijemeLogiranja ;
    public String token ;
    public String ipAdresa;

    public SesijaIndexRow(Date vrijemeLogiranja, String token, String ipAdresa) {
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
