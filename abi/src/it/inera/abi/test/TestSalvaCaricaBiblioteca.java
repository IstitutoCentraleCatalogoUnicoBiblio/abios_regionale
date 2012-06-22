package it.inera.abi.test;

import java.io.File;

import it.inera.abi.logic.TrasferimentoBiblioteca;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSalvaCaricaBiblioteca {
	
	public static void main(String[] args)  throws Exception  {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrasferimentoBiblioteca trasferimentoBiblioteca = appCtxt.getBean(TrasferimentoBiblioteca.class);
//		int attuale = 0;
		try {
//			for (int i = 2178; i < 3000; i++) {
//				attuale = i;
//				System.out.println(i);
				trasferimentoBiblioteca.salva(896);	
//				File toRemove = new File("/home/reschini/Project_Inera/workspace_abi/abiapplication/saved/" + i + ".xml");
				//toRemove.delete();
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
//		trasferimentoBiblioteca.ripristina(10);
//		System.out.println(attuale);
	}
	
}
