package clienteweb;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.teste.Cliente;
import br.com.teste.service.ClienteService;


@WebServlet(urlPatterns = {"/cliente", "/clienteServlet", "/clienteController"})
public class ClienteServlet extends HttpServlet{
	

	ClienteService clienteService;

	
	
	@Override
	public void init() throws ServletException {
		clienteService = new ClienteService();
		super.init();
	}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	int indice =-1;
	Cliente cli = new Cliente();
	cli.setEmail("");
	String i = req.getParameter("i");
	String acao = req.getParameter("acao");
	if (i != null && i != "") {
		indice = Integer.parseInt(i);
	}
	if(i!=null && i!="" && acao != null && acao != "") {
		if (acao.equals("exc")) {
			clienteService.excluir(indice);	
		}
		
		else if (acao.equals("edit")) {
			cli = clienteService.buscarPorIndice(indice);
		}
		
	}
	
	
//	inicializando um obj do tipo dispatcher com o endereço pra onde vai mandar as requisições 
	RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp"); 
	
	req.setAttribute("cli", cli);
	req.setAttribute("iCli", indice);
	req.setAttribute("listaReq", clienteService.getTodosClientes());
	dispatcher.forward(req, resp);
}



@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		tela .jsp ta chamando esse metodo, e aqui pega o que vier do campo "email" colocamos ele no obj Cliente cli e colocamos na ListaDeClientes
		String email = req.getParameter("email");
		String i = req.getParameter("i");
		int indice =-1;
		if (i != null && i != "") {
			indice = Integer.parseInt(i);
		}
		
		Cliente cli = new Cliente();
		cli.setEmail(email);
		clienteService.salvar(indice, cli);

		cli = new Cliente();
		cli.setEmail("");
//		aqui chama a tela cliente, logo após a adição, pro, uma linha, contra, duas requisições ao servidor (Requisição extra GET)
//		resp.sendRedirect("cliente");
		
//		aqui atribui mais um atributo ao obj req, no caso a lista atualizada e encaminha pro caminho definido no obj RequestDispatcher
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp"); 
		req.setAttribute("listaReq", clienteService.getTodosClientes());
		req.setAttribute("cli", cli);
//		coloca atributo como -1 para n pegar no nullpointer exception
		req.setAttribute("iCli", -1);
//		mandar mais um atributo para 
		req.setAttribute("reqMensagem", "deu bom o cadastro");
		dispatcher.forward(req, resp);

		
	}




}
