package br.com.cotacao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.cotacao.entidade.datasource.Moedas;
import br.com.cotacao.service.WEBStatus;

public class JavaMailApp {

	@SuppressWarnings("unchecked")
	public <T> void javamail(List<T> mail) {

		DataUtils dataTeste = new DataUtils();
		CotacaoBean cotacao = new CotacaoBean();
		Moedas unidadeMoedas = new Moedas();

		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testeemaildesafio@gmail.com", "testeemaildesafio@12");
			}
		});
		
		List<Moedas> testeServidor = null;
		try {
			for (int i = 0; i < unidadeMoedas.getUnidadeMoedas().length; i++) {
				testeServidor = WEBStatus.listarCotas(unidadeMoedas.getUnidadeMoedas()[i], dataTeste.todayAsString(), dataTeste.todayAsString());
				if(testeServidor.isEmpty()) {
					System.out.print("\n" + unidadeMoedas.getUnidadeMoedas()[i] + "- Moeda Inativa\n");
				}else {
					System.out.print("\n" + unidadeMoedas.getUnidadeMoedas()[i] + "- Moeda ativa\n");
					break;
				}
			}
			
			if (testeServidor.isEmpty()) {
				System.out.print("Servidor Inativo: " + testeServidor + "\n");
				cotacao.successExport(false, "API Inativa - Nao foi possível enviar o Email...");
			} else {
				System.out.print("\nServidor Ativo\n");

				if (mail.isEmpty()) {
					System.out.print("\nLista de emails vazia\n");
					cotacao.successExport(false, "Não há gerentes cadastrados para o envio de email...");
				} else {
					session.setDebug(true);
					List<T> lista = new ArrayList<T>();
					try {

						for (int i = 0; i < unidadeMoedas.getUnidadeMoedas().length; i++) {
							System.out.println("Cotação: " + cotacao.moedaCotacaoAtualEmail(unidadeMoedas.getUnidadeMoedas()[i]));
							lista.add((T) cotacao.moedaCotacaoAtualEmail(unidadeMoedas.getUnidadeMoedas()[i]));
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress("testeemaildesafio@gmail.com"));
						// Remetente

						Address[] allEmails = new Address[mail.size()];

						System.out.print("Email da Tabela: " + mail);

						for (int i = 0; i < mail.size(); i++) {

							allEmails = InternetAddress.parse(mail.get(i).toString());
							
							message.setRecipients(Message.RecipientType.TO, allEmails);
							message.setSubject("Tabela diária - Cotação de Moedas");
							message.setContent(lista.toString(), "text/html; charset=utf-8");

							/** Método para enviar a mensagem criada */
							Transport.send(message);
							System.out.println("email enviado!");
						}
						cotacao.successExport(true, "Email Enviado");
					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}
				}
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

}
