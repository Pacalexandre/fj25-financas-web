package br.com.caelum.financas.mb;

import java.io.*;

import javax.enterprise.context.*;
import javax.inject.*;

import br.com.caelum.financas.service.*;


@Named
@RequestScoped
public class CicloDeVidaBean {
	
	@Inject
	private Agendador agendador;
	
	public void executeAgendador() {
		agendador.executa();

	
	}
}
