# sefaz

##Config

Ferramentas usadas:
Eclipse IDE for Enterprise Java Developers, Version: 2020-12 (4.18.0), com o pluging Tools 4.17.0.Final.
XAMPP com MySql 8.0.0 e phpMyAdmin 5.0.4.
WildFly 21.0.2.Final (Já existe a 22.0.0, mas o pluging JBoss Tools 4.17.0.Final ainda não a suporta).

É necessário criar um banco de dados e um usuário com senha que tenha acesso a esse banco. (banco: sefaz, user: adriano, password: 1234 - GRANT ALL PRIVILEGES ON `sefaz`.* TO 'adriano'@'localhost' WITH GRANT OPTION;)

É necessário baixar o driver JDBC para conexão com o banco de dados (Connector/J 8.0.21) e colocá-lo dentro da pasta modules\system\layers\base\com\mysql\main (Criar se não existir) junto com um arquivo module.xml de configuração.

<?xml version="1.0" encoding="UTF-8"?>
<module name="com.mysql" xmlns="urn:jboss:module:1.5">

    <resources>
        <resource-root path="mysql-connector-java-8.0.22.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>

É necessário criar uma conta de admin e cadastrar uma senha (Sugestão de admin admin) no WildFly usando o add-user.bat dentro da pasta bin.
Além de pelo o Eclipse, é possível iniciar o servidor executando o arquivo standalone.bat dentro da pasta bin.
Para configurar a conexão com o banco de dados e o WildFly, é preciso acessar as configurações administrativas do servidor (Onde irá pedir o usuario e senha cadastrada no add-user.bat). Após levantá-lo, abrir localhost:9990, colocar usuario e senha e acessar a aba Configuration.

Na aba de Configuration, ir em Subsystems/Datasources & Drivers/JDBC Drivers e clicar no botão de + para adicionar o driver do MySql.
Driver Name: {nome que irá aparecer, sugestão: mysql}
Driver Module Name: com.mysql
Driver Class Name: com.mysql.cj.jdbc.Driver
Driver XA Datasource Class Name: com.mysql.cj.jdbc.MysqlXADataSource

Ainda na aba Configuration, ir em Subsystems/Datasources & Drivers/Datasources, clicar no botão de + e selecionar Add XA Datasource.
Escolher: MySql
Name: {nome que irá aparecer, sugestão: SefazDS}
JNDI Name: java:/sefaz-ds (Recupera as informações do datasource de dentro do persistence.xml )
Informações do driver: (Já vem preenchidas)
Connection URL: Informar ao final o nome do banco de dados
User Name: Usuário do banco (adriano)
Password: Senha desse usuário (1234)
Realizar o teste de conexão e concluir.









