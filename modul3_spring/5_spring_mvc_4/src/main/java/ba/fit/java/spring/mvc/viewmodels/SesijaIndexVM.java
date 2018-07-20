package ba.fit.java.spring.mvc.viewmodels;

import java.util.List;

public class SesijaIndexVM {
    public List<SesijaIndexRow> getRows() {
        return rows;
    }

    public String getTrenutniToken() {
        return trenutniToken;
    }

    public List<SesijaIndexRow> rows ;
    public String trenutniToken ;


}
