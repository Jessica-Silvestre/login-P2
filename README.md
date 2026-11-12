# Sistema de Login – CRUD de Usuários (P2)

Projeto da avaliação **P2** da disciplina **Desenvolvimento para Servidores I**
– Fatec São Roque Prof. Marcos.

**Aluno(a):** Jéssica Silvestre de Paula

## Objetivo

Implementar um sistema desktop com **Java e MySQL** que faz o CRUD
(Create, Read, Update e Delete) de usuários, seguindo os passos vistos em aula:
login, cadastro, tela inicial, alteração e exclusão de dados.

## Tecnologias
- Java 17 ou superior (Swing) – Apache NetBeans, projeto *Java with Ant*
- MySQL 8 e MySQL Connector/J 8.4 (jar na pasta `lib`)
- Senha criptografada com a função `MD5` do MySQL

## Banco de dados

Banco `bd_adega`, tabela `usuario` (o script completo está em `script.sql`):

## Conceitos aplicados (do material da aula)

- Classes, atributos e métodos; encapsulamento (getters e setters)
- Herança (as telas estendem `JFrame`)
- **Sobrecarga de método** (`verificaUsuario` com 1 e com 2 parâmetros)
- Atributos **estáticos** (`nomeUsuario` e `usuarioSistema`)
- Tratamento de exceções (`try/catch`, `SQLException`, `HeadlessException`)
- Interface gráfica com eventos (`ActionListener`) e mensagens (`JOptionPane`)
- Acesso a banco com JDBC: `SELECT`, `INSERT`, `UPDATE` e `DELETE`

## Como executar

1. Execute o `script.sql` no MySQL Workbench (cria o banco, a tabela e o usuário de teste).
2. Abra a pasta do projeto no NetBeans (*File > Open Project*).
3. O driver MySQL já está na pasta `lib` e referenciado no projeto. Se aparecer aviso em **Libraries**, clique com o botão direito em *Libraries > Add JAR/Folder* e selecione `lib/mysql-connector-j-8.4.0.jar`.
4. Confira em `Conexao.java` o usuário, a senha e a porta do seu MySQL.
5. Execute `TestaTela.java` (botão direito > *Run File*).

**Usuário de teste:** `admin` / `123`

## Ajustes em relação ao material

## Ajustes em relação ao material

Para manter tudo transparente, estas foram as diferenças em relação ao código visto em aula:

- **Nome do banco:** `bd_adega` no lugar de `bd_login`.
- **Visual:** foi criada a classe `Estilo`, com cores por tela e avisos coloridos. Os avisos continuam usando `JOptionPane`.
- **Layout das telas:** `BorderLayout` e `GridLayout` no lugar de `setBounds` com `setLayout(null)`. O comportamento é o mesmo.
- **Senha:** `getPassword()` no lugar de `getText()` do `JPasswordField`.
- **Tela de alteração:** o nome informado é validado diretamente no campo da tela.
- **Teclado:** a tecla *Enter* aciona o botão Entrar.

