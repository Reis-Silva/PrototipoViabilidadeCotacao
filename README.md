# Prototipo Viabilidade de Cotação-Moedas

Desafio construído para cadastro de cotações de moedas estrangeiras e envio das mesmas para o team de gerentes de agências, através das combinações "Hibernate-JPA-Maven" com o banco de dados Microsoft SQLserver e, consumo da API olinda`(https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/swagger-ui3#/)` .

## Resume

Neste aplicativo é possível resgatar as cotações de moedas estrangeiras com a gravação das mesmas no banco de dados e, envio de email com os dados atualizados destas para os gerentes de agências que estão cadastrados, sendo a função efetuada durante 5 dias (Segunda-feira à sexta-feira) as 15:00:00h.  

## Features

- Suporte no JavaEE podendo ser utilizado o JavaSE-8 (`jdk 8.x e 11.x`).

- Suporte no Servidor Apache Tomcat versões `8.x e 9.x`, aconselhável a versão `9.x` - Link: https://downloads.apache.org/tomcat/tomcat-9/v9.0.39/bin/apache-tomcat-9.0.39-windows-x64.zip

- Dados recorrentes de dependencias do projeto podem ser utilizadas de acordo como está definido no Link do pom.xml:https://github.com/Reis-Silva/PrototipoViabilidadeCotacao/blob/main/PrototipoViabilidadeCotacao/pom.xml desde de que esteja dentro das versões do JavaSE-8.

- Conexão com Microsoft SQLServer Management Studio (8 - v18.6) contendo o SQL Server 15, no entanto pode ser utilizado em outras versões. Dados recorrentes de conexão com o banco de dados encontram-se no persistence.xml: https://github.com/Reis-Silva/PrototipoViabilidadeCotacao/blob/main/PrototipoViabilidadeCotacao/src/main/java/META-INF/persistence.xml

- Utilização do Project lombok para simplificação de códigos (Javadoc: https://projectlombok.org/api/ e download: https://projectlombok.org/download).

- Utilização do framework Quartz para a criação do agendador de tarefas (Javadoc: http://www.quartz-scheduler.org/documentation/ e download: http://www.quartz-scheduler.org/downloads/).

## Troubleshooting

- Nas configurações do Microsoft SQLServer Management Studio em: `SQL Server Configuration Manager/Configurações de Rede do SQL Server/Protocolos para o servidor"`. Verificar se a opção do protocolo está `Habilitada`.
 
<p align="center">
<img src="https://github.com/Reis-Silva/PrototipoViabilidadeCotacao/blob/main/PrototipoViabilidadeCotacao/src/main/java/META-INF/resources/img/ProtocoloHabilitado.png">
</p>
 
- Em caso de erro referente ao bloqueio efetuado pelo Firewall, ir para a aba Endereço IP, selecionar `IP4` e mudar o endereço IP para `194.100.1.100`, em `IPAll` verificar se a porta está como `1433` e as portas dinamicas como 0 ou 55629. Reinicie todas os processos da conexão do SQL server pela aba `serviços/abrir serviços` do Gerenciador de Tarefas. 
 
 <p align="center">
<img src="https://github.com/Reis-Silva/PrototipoViabilidadeCotacao/blob/main/PrototipoViabilidadeCotacao/src/main/java/META-INF/resources/img/IP4_IPAll.png">
</p>

## How to Use

-  1 - Clone o projeto através do link: `https://github.com/Reis-Silva/PrototipoViabilidadeCotacao/`;

-  2 - Coloque a pasta do Servidor Apache Tomcat em um local de preferência. Na IDE eclipseEE, utilize a opção File/new/new server, escolha a versão do servidor adquirida através do donwload, depois selecione onde se encontra esta pasta;

-  3 - Utilizar a opção "update Maven" para atualizar as referências de bibliotecas;

-  4 - Quando o Servidor estiver conectado, utilize a página para utilizar o aplicativo `http://localhost:8080/PrototipoViabilidadeCotacao/index.xhtml`.

## Observed:

OBS: No mecanismos de Banco de Dados do SQL Server utiliza-se da "Autenticação do SQL Server": `Logon: root e Senha 1234`; 

OBS2: Função de criação do banco de dados não está automática (foi estabelecido a criação obrigatória do "moedasbd" no servidor, pode ser mudado) apenas para a tabela;

OBS3: Email de exemplo criado para o envio automático: -  `Login:testeemaildesafio@gmail.com / Senha:testeemaildesafio@12`. Caso seja necessário para habilitar o uso em algum outro local que esteja acessando (Já está habilitado a opção de segurança mínima); 

OBS4: Utilizou-se o Eclipse EE - Version: 2020-09 (4.17.0).

########################################################################################################################################################################################
### []´s
