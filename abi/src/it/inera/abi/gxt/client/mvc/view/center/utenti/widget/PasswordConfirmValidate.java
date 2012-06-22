package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class PasswordConfirmValidate implements Validator {
	
    TextField<String> passwordCnf;
    
    public PasswordConfirmValidate(TextField<String> passwordCnf) {
        this.passwordCnf = passwordCnf;
    }
    public String validate(Field<?> field, String value) {
        String res = null;
        if(passwordCnf.getValue()!=null && field.getValue()!=null && value!=null){
        	if (!passwordCnf.getValue().equals(value)) {
        		res = "Inserire due password uguali";
        	}
        }
        return res;
    }
}