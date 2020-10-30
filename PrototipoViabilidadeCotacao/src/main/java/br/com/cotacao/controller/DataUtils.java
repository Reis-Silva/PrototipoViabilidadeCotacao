package br.com.cotacao.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils extends Thread {

	boolean laco = true;
	boolean envioTempoEmail = true;
	
	public String todayAsString() {
		Date today = new Date();

		return dateAsString(today);
	}

	public String dateAsString(Date data) {
		String formato = "MM-dd-yyyy";
		DateFormat df = new SimpleDateFormat(formato);
		String dataAsString = df.format(data);

		return dataAsString;
	}

	public void calendarioEnvioEmail() {
		new Thread() {

			@Override
			public void run() {
				while (laco = true) {
					try {

						Calendar daytime = Calendar.getInstance();
						Calendar hourtime = Calendar.getInstance();
						
						int segunda = daytime.get(Calendar.DAY_OF_MONTH) - daytime.get(Calendar.DAY_OF_WEEK) + 2;
						int sexta = daytime.get(Calendar.DAY_OF_MONTH) + daytime.get(Calendar.DAY_OF_WEEK) - 2;
						int today = hourtime.get(Calendar.DAY_OF_MONTH);
						int hora = hourtime.get(Calendar.HOUR_OF_DAY);
						int minuto = hourtime.get(Calendar.MINUTE);
						int segundo = hourtime.get(Calendar.SECOND);

						int[] listaTempo = { segunda, sexta, today, hora, minuto, segundo };
						
						contrucaoTempoEmail(listaTempo);
						Thread.sleep(10000); 
						

					} catch (InterruptedException e) {

					}
				}

			}
		}.start();
	}

	public void contrucaoTempoEmail(int[] listaTempo) {

		if (listaTempo[2] >= listaTempo[0] && listaTempo[2] <= listaTempo[1]) {

			String h = String.valueOf(listaTempo[3]);
			String m = String.valueOf(listaTempo[4]);
			String s = String.valueOf(listaTempo[5]);

			String conc = h + m + s;
			int conversaohora = Integer.parseInt(conc);
			
			System.out.print("\nHora Atual:" + conversaohora+"\n\nVerificando a hora de envio...\n");
			
			if ((conversaohora + 101) >= 150000) {
				envioTempoEmail = true;
			}

			if ((conversaohora + 100) >= 150000 && (conversaohora - 100) <= 150000 && envioTempoEmail == true) {
				
				try {
					System.out.print("\nefetuado\n\n");
					CotacaoBean envioEmails = new CotacaoBean();
					envioEmails.buscarEmailGerente();
					envioTempoEmail = false;
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		}

	}

}
