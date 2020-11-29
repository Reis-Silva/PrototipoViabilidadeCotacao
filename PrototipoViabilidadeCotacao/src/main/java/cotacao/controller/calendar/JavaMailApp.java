package cotacao.controller.calendar;

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

import cotacao.dao.moeda.MoedaJPADAO;
import cotacao.entity.moeda.Moeda;
import cotacao.service.WEBStatus;
import lombok.Data;

@Data
public class JavaMailApp {

	@SuppressWarnings("unchecked")
	public <T> void javamail(List<T> mail) {

		DataUtils dataTeste = new DataUtils();
		MoedaJPADAO moeda = new MoedaJPADAO();
		moeda.setMoeda(new Moeda());
		
		Properties props = new Properties();
		
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
				
		List<Moeda> testeServidor = null;
		try {
			for (int i = 0; i < moeda.getMoeda().getUnidadeMoedas().length; i++) {
				testeServidor = WEBStatus.listarCotas(moeda.getMoeda().getUnidadeMoedas()[i], dataTeste.todayAsString(), dataTeste.todayAsString());
				if(testeServidor.isEmpty()) {
					System.out.print("\n" + moeda.getMoeda().getUnidadeMoedas()[i] + "- Moeda Inativa\n");
				}else {
					System.out.print("\n" + moeda.getMoeda().getUnidadeMoedas()[i] + "- Moeda ativa\n");
					break;
				}
			}
			
			if (testeServidor.isEmpty()) {
				System.out.print("Servidor Inativo: " + testeServidor + "\n");
				moeda.messageView(false, "API Inativa - Nao foi possível enviar o Email...");
			} else {
				System.out.print("\nServidor Ativo\n");

				if (mail.isEmpty()) {
					System.out.print("\nLista de emails vazia\n");
					moeda.messageView(false, "Não há gerentes cadastrados para o envio de email...");
				} else {
					session.setDebug(true);
					List<T> lista = new ArrayList<T>();
					try {

						for (int i = 0; i < moeda.getMoeda().getUnidadeMoedas().length; i++) {
							System.out.println("Cotação: " + moeda.cotacaoMoedasSaveEmail(moeda.getMoeda().getUnidadeMoedas()[i]));
							lista.add((T) moeda.cotacaoMoedasSaveEmail(moeda.getMoeda().getUnidadeMoedas()[i]));
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress("testeemaildesafio@gmail.com"));
				
						Address[] allEmails = new Address[mail.size()];

						System.out.print("Email da Tabela: " + mail);

						for (int i = 0; i < mail.size(); i++) {

							allEmails = InternetAddress.parse(mail.get(i).toString());
							
							message.setRecipients(Message.RecipientType.TO, allEmails);
							message.setSubject("Tabela diária - Cotação de Moedas");
							message.setContent(lista.toString(), "text/html; charset=utf-8");

							Transport.send(message);
							System.out.println("email enviado!");
						}
						moeda.messageView(true, "Email Enviado");
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
