package ba.fit.java.spring.mvc.viewmodels;

import java.util.Date;
import java.util.List;

public class SesijaIndexVM {
    public List<Row> getRows() {
        return rows;
    }

    public String getTrenutniToken() {
        return trenutniToken;
    }

    public List<Row> rows ;
    public String trenutniToken ;


}
