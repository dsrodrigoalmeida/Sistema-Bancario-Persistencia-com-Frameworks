package br.com.rodrigoale.banco.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigoale.banco.domain.Cliente;
import br.com.rodrigoale.banco.domain.ContaBancaria;
import br.com.rodrigoale.banco.domain.Transacao;
import br.com.rodrigoale.banco.domain.enums.TipoCliente;
import br.com.rodrigoale.banco.domain.enums.TipoTransacao;
import br.com.rodrigoale.banco.services.ClienteService;
import br.com.rodrigoale.banco.services.ContaBancariaService;
import br.com.rodrigoale.banco.services.TransacaoService;

@RestController
@RequestMapping(value = "/contas")
public class ContaBancariaResource {

	@Autowired
	private ContaBancariaService contaBancariaService;

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private ClienteService clienteService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView find(@PathVariable Integer id) {
		ContaBancaria obj = contaBancariaService.find(id);
		ModelAndView modelAndView = new ModelAndView("resultado");

		modelAndView.addObject("conta", obj);
		return modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;
	}

	@RequestMapping(value = "/paginadeposito", method = RequestMethod.GET)
	public ModelAndView pagina_deposito() {

		ModelAndView modelAndView = new ModelAndView("deposito");

		return modelAndView;
	}

	@RequestMapping(value = "/deposito", method = RequestMethod.POST)
	public ModelAndView deposito(@RequestParam("conta") int conta, @RequestParam("agencia") int agencia,
			@RequestParam("valor") double valor) throws ParseException {
		ContaBancaria obj = contaBancariaService.find(conta);

		if (obj != null && obj.getAgencia().equals(agencia)) {

			Transacao transacao = new Transacao(null, TipoTransacao.CREDITO, valor,sdf.parse("29/05/2019"), obj);
			transacaoService.insert(transacao);
			Double novoSaldo = obj.getSaldo() + valor;
			obj.setSaldo(novoSaldo);
			obj.getTransacoes().addAll(Arrays.asList(transacao));
			contaBancariaService.update(obj);
		} else {
			System.out.println("Dados invalido. Tente novamente");
		}

		ModelAndView modelAndView = new ModelAndView("resultado");

		modelAndView.addObject("conta", obj);

		return modelAndView;
	}

	@RequestMapping(value = "/paginasaque", method = RequestMethod.GET)
	public ModelAndView pagina_saque() {

		ModelAndView modelAndView = new ModelAndView("saque");

		return modelAndView;
	}

	@RequestMapping(value = "/saque", method = RequestMethod.POST)
	public ModelAndView saque(@RequestParam("conta") int conta, @RequestParam("agencia") int agencia,
			@RequestParam("valor") double valor) throws ParseException {
		ContaBancaria obj = contaBancariaService.find(conta);

		if (obj != null && obj.getAgencia().equals(agencia)) {
			Double novoSaldo = obj.getSaldo() - valor;
			if (novoSaldo >= 0) {
				Transacao transacao = new Transacao(null, TipoTransacao.DEBITO, valor,sdf.parse("29/05/2019"), obj);
				transacaoService.insert(transacao);
				obj.setSaldo(novoSaldo);
				obj.getTransacoes().addAll(Arrays.asList(transacao));
				contaBancariaService.update(obj);
			} else {
				System.out.println("O valor digitado ultrapassa o seu saldo");
			}
		} else {
			System.out.println("Dados invalido. Tente novamente");
		}

		ModelAndView modelAndView = new ModelAndView("resultado");

		modelAndView.addObject("conta", obj);

		return modelAndView;
	}

	@RequestMapping(value = "/paginatransferencia", method = RequestMethod.GET)
	public ModelAndView pagina_transferencia() {

		ModelAndView modelAndView = new ModelAndView("transferencia");

		return modelAndView;
	}

	@RequestMapping(value = "/transferencia", method = RequestMethod.POST)
	public ModelAndView transferencia(@RequestParam("contaOrigem") int contaOrigem,
			@RequestParam("agenciaOrigem") int agenciaOrigem, @RequestParam("contaDestino") int contaDestino,
			@RequestParam("agenciaDestino") int agenciaDestino, @RequestParam("valor") double valor) throws ParseException {
		ContaBancaria objOrigem = contaBancariaService.find(contaOrigem);
		ContaBancaria objDestino = contaBancariaService.find(contaDestino);
		if (objOrigem != null && objOrigem.getAgencia().equals(agenciaOrigem)
				&& (objDestino != null && objDestino.getAgencia().equals(agenciaDestino))
				&& !objOrigem.getConta().equals(objDestino.getConta())) {
			Double novoSaldoOrigem = objOrigem.getSaldo() - valor;
			if (novoSaldoOrigem >= 0) {
				Transacao transacaoOrigem = new Transacao(null, TipoTransacao.DEBITO, valor,sdf.parse("29/05/2019"), objOrigem);
				transacaoService.insert(transacaoOrigem);
				objOrigem.setSaldo(novoSaldoOrigem);
				objOrigem.getTransacoes().addAll(Arrays.asList(transacaoOrigem));

				Double novoSaldoDestino = objDestino.getSaldo() + valor;
				Transacao transacaoDestino = new Transacao(null, TipoTransacao.CREDITO, valor,sdf.parse("29/05/2019"), objDestino);
				transacaoService.insert(transacaoDestino);
				objDestino.setSaldo(novoSaldoDestino);
				objDestino.getTransacoes().addAll(Arrays.asList(transacaoDestino));
				contaBancariaService.update(objDestino);
			} else {
				System.out.println("O valor digitado ultrapassa o seu saldo");
			}
		} else {
			System.out.println("Dados invalido. Tente novamente");
		}

		ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;
	}

	@RequestMapping(value = "/paginabuscaagencia", method = RequestMethod.GET)
	public ModelAndView pagina_busca_agencia() {

		ModelAndView modelAndView = new ModelAndView("buscaagencia");

		return modelAndView;
	}

	@RequestMapping(value = "/buscaagencia", method = RequestMethod.GET)
	public ModelAndView findByAgencia(@RequestParam("agencia") int agencia) {
		List<ContaBancaria> obj = contaBancariaService.findByAgencia(agencia);

		if (obj.size() > 0) {
			ModelAndView modelAndView = new ModelAndView("resultados");

			modelAndView.addObject("contas", obj);

			return modelAndView;
		} else {
			System.out.println("Agencia não encontrada. Tente novamente");
		}
		ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;

	}

	@RequestMapping(value = "/paginabuscacliente", method = RequestMethod.GET)
	public ModelAndView pagina_buscacliente() {

		ModelAndView modelAndView = new ModelAndView("buscacliente");

		return modelAndView;
	}

	@RequestMapping(value = "/buscacliente", method = RequestMethod.GET)
	public ModelAndView findByCpfOuCnpj(@RequestParam("cpfOuCnpj") Integer cpfOuCnpj) {
		List<ContaBancaria> obj = contaBancariaService.findByCpfOuCnpj(cpfOuCnpj);

		if (obj.size() > 0) {
			ModelAndView modelAndView = new ModelAndView("resultados");

			modelAndView.addObject("contas", obj);

			return modelAndView;
		} else {
			System.out.println("Cliente não encontrado. Tente novamente");
		}
		ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;
	}

	@RequestMapping(value = "/paginacadastro", method = RequestMethod.GET)
	public ModelAndView pagina_cadastro() {

		ModelAndView modelAndView = new ModelAndView("cadastro");

		return modelAndView;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastro(@RequestParam("cpfOuCpnj") Integer cpfOuCpnj, @RequestParam("nome") String nome,
			@RequestParam("radioName") String radio, @RequestParam("conta") Integer conta,
			@RequestParam("agencia") Integer agencia) {

		Cliente cliente = this.cadastraCliente(cpfOuCpnj, nome, radio);
		ContaBancaria obj = this.cadastrarConta(conta, agencia, cliente);

		ModelAndView modelAndView = new ModelAndView("resultado");
		modelAndView.addObject("conta", obj);
		return modelAndView;
	}

	private ContaBancaria cadastrarConta(Integer conta, Integer agencia, Cliente cliente) {
		ContaBancaria contaBancaria = contaBancariaService.find(conta);

		if (contaBancaria != null) {
			System.out.println("Essa Conta Ja Existe. Tente Cadastrar outra!");

		} else {

			ContaBancaria novaContaBancaria = new ContaBancaria(conta, agencia, 0.0, cliente);
			clienteService.insert(cliente);
			contaBancariaService.insert(novaContaBancaria);
			return novaContaBancaria;
		}
		return contaBancaria;
	}

	private Cliente cadastraCliente(Integer cpfOuCpnj, String nome, String radio) {

		Cliente cliente = clienteService.find(cpfOuCpnj);

		if (cliente != null) {
			System.out.println("Esse cliente ja possui cadastro no sistema. "
					+ "Sera utilizado o cadastro que está armazenado no sistema!");
			return cliente;

		} else {
			cliente = new Cliente();
			if (radio.equals("pessoa_fisica")) {
				cliente.setTipo(TipoCliente.PESSOAFISICA);
			} else {
				cliente.setTipo(TipoCliente.PESSOAJURIDICA);
			}
			cliente.setNome(nome);
			cliente.setId(cpfOuCpnj);
		}
		return cliente;
	}

}
