package br.com.rodrigoale.banco;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rodrigoale.banco.domain.Cliente;
import br.com.rodrigoale.banco.domain.ContaBancaria;
import br.com.rodrigoale.banco.domain.Transacao;
import br.com.rodrigoale.banco.domain.enums.TipoCliente;
import br.com.rodrigoale.banco.domain.enums.TipoTransacao;
import br.com.rodrigoale.banco.repositories.ClienteRepository;
import br.com.rodrigoale.banco.repositories.ContaBancariaRepository;
import br.com.rodrigoale.banco.repositories.TransacaoRepository;

@SpringBootApplication
public class BancoApplication implements CommandLineRunner {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	TransacaoRepository transacaoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Cliente cli1 = new Cliente(1, "teste", TipoCliente.PESSOAFISICA);
		ContaBancaria c1 = new ContaBancaria(111, 111, 50.0, cli1);
		Transacao t1 = new Transacao(null, TipoTransacao.CREDITO, 50.0, sdf.parse("21/05/2019"),c1);
		
		cli1.getContas().addAll(Arrays.asList(c1));
		c1.getTransacoes().addAll(Arrays.asList(t1));
		
		Cliente cli2 = new Cliente(2, "teste2", TipoCliente.PESSOAJURIDICA);
		ContaBancaria c2 = new ContaBancaria(222, 111, 70.0, cli2);
		Transacao t2 = new Transacao(null, TipoTransacao.DEBITO, 70.0, sdf.parse("23/05/2019"),c2);
		
		cli2.getContas().addAll(Arrays.asList(c2));
		c2.getTransacoes().addAll(Arrays.asList(t2));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		contaBancariaRepository.saveAll(Arrays.asList(c1,c2));
		transacaoRepository.saveAll(Arrays.asList(t1,t2));
		
	}

}
