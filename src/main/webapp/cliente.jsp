<!DOCTYPE html>
<%@page import="br.com.teste.Cliente"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>tela cadastro de cliente</title>
<script>

	function confirma(pi){
		if (window.confirm("tem certeza que deseja excluir?"))
		location.href="cliente?i="+pi+"&acao=exc"
	 }


</script>

<style >

body{text-align: center;}
table {margin: auto;}

</style>
</head>


<body>

<div>
	<%
		if (request.getAttribute("reqMensagem") != null){
			out.print(request.getAttribute("reqMensagem"));
		}
	
	Cliente cli = (Cliente) request.getAttribute("cli");
	Object iCli = request.getAttribute("iCli");
	
	%>
</div>
<table>
<form method="post" action="cliente">

	<input type="hidden" name="i" value="<%=iCli%>"/>
	
	<th>Nome:
	<input type="text" value="<%=cli.getNome()%>" name="nome" /><br>
	</th>
	<th>
	E- mail:
	<input type="text" value="<%=cli.getEmail()%>" name="email" /><br>
	</th>
	<th>
	Telefone:
	<input type="text" value="<%=cli.getTelefone()%>" name="telefone" /><br>
	</th>
	
</table>
<br>
<input type="submit" value="save"/>
<br>

	
</form>
<br>
<table border="15" style="border-color: blue">
	<tr>
		<th  style="width: 250px"> Nome</th>
		<th  style="width: 250px"> E-mail</th>
		<th  style="width: 250px"> Telefone</th>
		<th style="width: 100px"> A��o</th>
		
		
	</tr>
<%
//o obg request vem como OBJ e temos que transformar-lo pra List
List<Cliente> listaJsp = (List<Cliente>)request.getAttribute("listaReq");
int i =0;
for (Cliente c: listaJsp){
// 	detalhe que ao chamar como refer�ncia uma fun��o js precisa expecificar (javascript:) pq se n vai fazer uma requisi��o ao servidor
%>  
<tr>
	<td>
		<%=c.getNome()%>  
	</td>
	<td>
		<%=c.getEmail()%>  
	</td>
	<td>
		<%=c.getTelefone()%>  
	</td>
	<td>
		<a href="javascript:confirma (<%=i%>)"> excluir</a>
		<a href="cliente?i=<%=i%>&acao=edit"> editar</a> <br>
	</td>
</tr>
<%
	i++;
}
%>
</table>

</body>
</html>