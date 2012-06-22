package it.inera.abi.gxt.client;

import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.widget.InfoConfig;

/*Classe che estende infoConfig per poter settare valori custom*/
public class InfoConfigCustom extends InfoConfig{

	public InfoConfigCustom(String title, String message) {
		super(title,message);
	}

	public InfoConfigCustom(String title, String message, Params params) {
		super(title,message,params);
	}

	public void setDysplay(int display){
		this.display=display;
	}
	public void setWidth(int width){
		this.width=width;
	}

	public void setHeigth(int height){
		this.height=height;
	}
}