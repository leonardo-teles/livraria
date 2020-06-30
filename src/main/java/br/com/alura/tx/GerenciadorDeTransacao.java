package br.com.alura.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;

	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception {

		System.out.println("Inicia a transação");
		// abre transacao
		manager.getTransaction().begin();
		
		// chama os DAO's que precisam de um TX
		Object resultado = contexto.proceed();
		
		System.out.println("Comita a transação");
		// commita a transacao
		manager.getTransaction().commit();

		return resultado;
	}
}
