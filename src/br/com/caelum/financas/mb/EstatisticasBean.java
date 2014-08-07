package br.com.caelum.financas.mb;



import javax.enterprise.context.*;
import javax.inject.*;
import javax.persistence.*;

import org.hibernate.*;
import org.hibernate.stat.*;

@Named
@ApplicationScoped
public class EstatisticasBean {
	
	@Inject
	private EntityManager manager;
	
	private Statistics estatisticas;
	
		public Statistics getEstatisticas() {
			return estatisticas;
		}
		
		public void gera() {
			Session session = this.manager.unwrap(Session.class);
			this.estatisticas = session.getSessionFactory().getStatistics();
			System.out.println("Gerando est�tisitcas");

		}
}
