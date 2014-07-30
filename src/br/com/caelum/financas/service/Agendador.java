package br.com.caelum.financas.service;

import java.io.*;

import javax.annotation.*;
import javax.ejb.*;

@Singleton
@Startup
public class Agendador {

	private static int totalCriado;

	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

	@PostConstruct
	void posConstrucao() {
		System.out.println("Criando Agendador");
		totalCriado++;
	}

	@PreDestroy
	void preDestruicao() {
		System.out.println("Destruindo Agendador");
	}
	
	@Schedule(hour="*", minute="*",second="*/10", dayOfWeek="Wed", persistent=false)
	public void enviaEmail(){
		System.out.println("Enviando Email por cada 30 seg");
	}

}
