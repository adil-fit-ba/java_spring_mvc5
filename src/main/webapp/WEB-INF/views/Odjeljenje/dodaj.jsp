<%@ page import="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM" %>
<%@ page import="javassist.compiler.ast.Pair" %>
<%@ page import="ba.fit.java.spring.mvc.helper.SelectListItem" %>
<%
    OdjeljenjeDodajVM model = (OdjeljenjeDodajVM) request.getAttribute("model");
%>

<h2>Dodaj</h2>

<<orm action="/Odjeljenje/Snimi" >

<div class="form-group">
    <label>Školska godina: </label>
    <div>

        <input class="form-control" type="text" data-val="true" data-val-length="The field skolaGodina must be a string with a maximum length of 7." data-val-length-max="7" data-val-regex="The field skolaGodina must match the regular expression &#x27;[0-9]{4}[\/-][0-9]{2}&#x27;." data-val-regex-pattern="[0-9]{4}[\/-][0-9]{2}" data-val-remote="&#x27;skolaGodina&#x27; is invalid." data-val-remote-additionalfields="*.skolaGodina,*.oznaka" data-val-remote-url="/NastavnikModul/Odjeljenje/ProvjeriOznaku" data-val-required="The skolaGodina field is required." id="skolaGodina" name="skolaGodina" value="" />
        <span class="text-danger field-validation-valid" data-valmsg-for="skolaGodina" data-valmsg-replace="true"></span>
    </div>
</div>

<div class="form-group">
    <label>Razred: </label>
    <div>
        <input class="form-control" type="number" data-val="true" data-val-range="The field razred must be between 1 and 9." data-val-range-max="9" data-val-range-min="1" data-val-required="The razred field is required." id="razred" name="razred" value="0" />
        <span class="text-danger field-validation-valid" data-valmsg-for="razred" data-valmsg-replace="true"></span>
    </div>
</div>

<div class="form-group">
    <label>Oznaka: </label>
    <div>
        <input class="form-control" type="text" data-val="true" data-val-length="The field oznaka must be a string with a maximum length of 3." data-val-length-max="3" data-val-regex="The field oznaka must match the regular expression &#x27;[1-9][-][a-z]&#x27;." data-val-regex-pattern="[1-9][-][a-z]" data-val-remote="&#x27;oznaka&#x27; is invalid." data-val-remote-additionalfields="*.oznaka,*.skolaGodina" data-val-remote-url="/NastavnikModul/Odjeljenje/ProvjeriOznaku" data-val-required="The oznaka field is required." id="oznaka" name="oznaka" value="" />
        <span class="text-danger field-validation-valid" data-valmsg-for="oznaka" data-valmsg-replace="true"></span>
    </div>
</div>


<div class="form-group">
    <label>Razrednik: </label>
    <div>

        <input class="form-control" readonly type="text" id="nastavnik" name="nastavnik" value="nastavnik1" />
    </div>
</div>

<div class="form-group">
    <label>Niže odjeljenje: </label>
    <div>
        <select class="form-control" data-val="true" data-val-required="The odjeljenjeID field is required." id="odjeljenjeID" name="odjeljenjeID">
            <option>(odaberite odjeljenje)</option>
            <% for (SelectListItem p : model.odjeljenja) {%>
                 <option value="<%= p.value%>"> <%= p.text %></option>
            <% } %>
        </select>
        <span class="text-danger field-validation-valid" data-valmsg-for="odjeljenjeID" data-valmsg-replace="true"></span>
    </div>
</div>

<input type="submit" value="Snimi" />
</orm>


       


