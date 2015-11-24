package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControleItem;
import controller.ControllerDepartamento;
import controller.ControllerEntrada;
import controller.ControllerFornecedor;
import controller.ControllerProduto;
import controller.ControllerResponsavel;
import controller.ControllerSaida;
import controller.ControllerSolicitacaoDepartamento;
import controller.ControllerSolicitacaoFornecedor;
import controller.ControllerTipo;
import model.Departamento;
import model.Entrada;
import model.Fornecedor;
import model.Item;
import model.Produto;
import model.Responsavel;
import model.Saida;
import model.SolicitacaoDepartamento;
import model.SolicitacaoFornecedor;
import model.Tipo;
import utilities.EstoqueException;

public class JanelaPrincipal implements ActionListener{
	private JFrame frame = new JFrame("Tela Principal");

	SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
	private JPanel principalTipo = new JPanel(new BorderLayout());
	private JPanel principalProduto = new JPanel(new BorderLayout());
	private JPanel principalSaida = new JPanel(new BorderLayout());
	private JPanel principalItens = new JPanel(new BorderLayout());
	private JPanel principalEntrada = new JPanel(new BorderLayout());
	private JPanel principalResponsavel = new JPanel(new BorderLayout());
	private JPanel principalDepartamento = new JPanel(new BorderLayout());
	private JPanel principalFornecedor = new JPanel(new BorderLayout());
	private JPanel principalSolicitacaoFORN = new JPanel(new BorderLayout());
	private JPanel principalSolicitacaoDPTO = new JPanel(new BorderLayout());
	
	private ControllerTipo ctrTipo = new ControllerTipo();
	private ControllerProduto ctrProduto = new ControllerProduto();
	private ControllerSaida ctrSaida = new ControllerSaida();
	private ControleItem ctrItem = new ControleItem();
	private ControllerEntrada ctrEntrada = new ControllerEntrada();
	private ControllerResponsavel ctrResponsavel = new ControllerResponsavel();
	private ControllerDepartamento ctrDepartamento = new ControllerDepartamento();
	private ControllerFornecedor ctrFornecedor = new ControllerFornecedor();
	private ControllerSolicitacaoFornecedor ctrSolFornecedor = new ControllerSolicitacaoFornecedor();
	private ControllerSolicitacaoDepartamento ctrSolDepartamento = new ControllerSolicitacaoDepartamento();
	
	private JTable tabelaTipo = new JTable(ctrTipo);
	private JTable tabelaProduto = new JTable(ctrProduto);
	private JTable tabelaSaida = new JTable(ctrSaida);
	private JTable tabelaItem = new JTable(ctrItem);
	private JTable tabelaEntrada = new JTable(ctrEntrada);
	private JTable tabelaResponsavel = new JTable(ctrResponsavel);
	private JTable tabelaDepartamento = new JTable(ctrDepartamento);
	private JTable tabelaFornecedor = new JTable(ctrFornecedor);
	private JTable tabelaSolFornecedor = new JTable(ctrSolFornecedor);
	private JTable tabelaSolDepartamento = new JTable(ctrSolDepartamento);
	
	private JPanel panelTipo = new JPanel(new GridLayout(2, 2));
	private JPanel panelPro = new JPanel(new GridLayout(10, 2));
	private JPanel panelSai = new JPanel(new GridLayout(3, 2));
	private JPanel panelIte = new JPanel(new GridLayout(4, 2));
	private JPanel panelEnt = new JPanel(new GridLayout(7, 2));
	private JPanel panelResp = new JPanel(new GridLayout(3, 2));
	private JPanel panelDep = new JPanel(new GridLayout(6, 2));
	private JPanel panelFor = new JPanel(new GridLayout(8, 2));
	private JPanel panelSolFor = new JPanel(new GridLayout(3, 2));
	private JPanel panelSolDep = new JPanel(new GridLayout(3, 2));
	
	private JTextField codTipo = new JTextField(10);
	private JTextField nomeTipo = new JTextField(30);
	private JButton salvarTipo = new JButton("Salvar Tipo");
	private JButton alterTipo = new JButton("Alterar Tipo");
	private JButton pesqTipo = new JButton("Pesquisar Tipo");
	private JButton remTipo = new JButton("Remover Tipo");
	
	private JTextField codPro = new JTextField(10);
	private JTextField nomePro = new JTextField(30);
	private JTextField usoPro = new JTextField(30);
	private JTextField qtdMinima = new JTextField(10);	
	private JTextField qtdSeguranca = new JTextField(10);
	private JTextField qtdMaxima = new JTextField(10);
	private JTextField consumoPrevisto = new JTextField(10);
	private JTextField preco = new JTextField(10);
	private JTextField peso = new JTextField(10);
	private JTextField codTipoPro = new JTextField(10);
	private JButton salvarPro = new JButton("Salvar Produto");
	private JButton alterPro = new JButton("Alterar Produto");
	private JButton pesqPro = new JButton("Pesquisar Produto");
	private JButton remPro = new JButton("Remover Produto");
	
	private JTextField codSai = new JTextField(10);
	private JFormattedTextField dataSai;
	private JTextField descSaida = new JTextField(30);
	private JButton salvarSai = new JButton("Salvar Saida");
	private JButton alterSai = new JButton("Alterar Saida");
	private JButton pesqSai = new JButton("Pesquisar Saida");
	private JButton remSai = new JButton("Remover Saida");	
	
	private JTextField codItem = new JTextField(10);
	private JTextField codProItem = new JTextField(10);
	private JTextField codSaiItem = new JTextField(10);
	private JTextField codEntItem = new JTextField(10);	
	private JButton salvarItem = new JButton("Salvar Item");
	private JButton alterItem = new JButton("Alterar Item");
	private JButton pesqItem = new JButton("Pesquisar Item");
	private JButton remItem = new JButton("Remover Item");
	
	private JTextField codEnt = new JTextField(10);
	private JFormattedTextField dataEnt;
	private JTextField tipoEnt = new JTextField(30);
	private JTextField NFEEnt = new JTextField(30);
	private JFormattedTextField dataNFEEnt;
	private JTextField tempoEnt = new JTextField(10);
	private JTextField codForEnt = new JTextField(10);
	private JButton salvarEnt = new JButton("Salvar Entrada");
	private JButton alterEnt = new JButton("Alterar Entrada");
	private JButton pesqEnt = new JButton("Pesquisar Entrada");
	private JButton remEnt = new JButton("Remover Entrada");
		
	private JTextField codResp = new JTextField(10);
	private JTextField nomeResp = new JTextField(30);
	private JTextField telResp = new JTextField(20);
	private JButton salvarResp = new JButton("Salvar Responsavel");
	private JButton alterResp = new JButton("Alterar Responsavel");
	private JButton pesqResp = new JButton("Pesquisar Responsavel");
	private JButton remResp = new JButton("Remover Responsavel");
	
	private JTextField codDep = new JTextField(10);
	private JTextField nomeDep = new JTextField(30);
	private JTextField andarDep = new JTextField(10);
	private JTextField predioDep = new JTextField(10);
	private JTextField telDep = new JTextField(10);
	private JTextField codRespDep = new JTextField(10);
	private JButton salvarDep = new JButton("Salvar Departamento");
	private JButton alterDep = new JButton("Alterar Departamento");
	private JButton pesqDep = new JButton("Pesquisar Departamento");
	private JButton remDep = new JButton("Remover Departamento");
	
	private JTextField codFor = new JTextField(10);
	private JTextField ruaFor = new JTextField(30);
	private JTextField numFor = new JTextField(10);
	private JTextField bairFor = new JTextField(30);
	private JTextField cidFor = new JTextField(30);
	private JTextField estFor = new JTextField(20);
	private JTextField nomeFor = new JTextField(30);
	private JTextField telFor = new JTextField(20);
	private JButton salvarFor = new JButton("Salvar Fornecedor");
	private JButton alterFor = new JButton("Alterar Fornecedor");
	private JButton pesqFor = new JButton("Pesquisar Fornecedor");
	private JButton remFor = new JButton("Remover Fornecedor");
	
	private JTextField codSolFor = new JTextField(10);
	private JTextField codForSol = new JTextField(10);
	private JFormattedTextField dtSolFor;
	private JButton salvarSolFor = new JButton("Salvar Solicitacao Fornecedor");
	private JButton alterSolFor = new JButton("Alterar Solicitacao Fornecedor");
	private JButton pesqSolFor = new JButton("Pesquisar Solicitacao Fornecedor");
	private JButton remSolFor = new JButton("Remover Solicitacao Fornecedor");
	
	private JTextField codSolDep = new JTextField(10);
	private JTextField codDepSol = new JTextField(10);
	private JFormattedTextField dtSolDep;
	private JButton salvarSolDep = new JButton("Salvar Solicitacao Departamento");
	private JButton alterSolDep = new JButton("Alterar Solicitacao Departamento");
	private JButton pesqSolDep = new JButton("Pesquisar Solicitacao Departamento");
	private JButton remSolDep = new JButton("Remover Solicitacao Departamento");
	
	private JScrollPane scrollTipo = new JScrollPane();
	private JScrollPane scrollProduto = new JScrollPane();
	private JScrollPane scrollSaida = new JScrollPane();
	private JScrollPane scrollItem = new JScrollPane();
	private JScrollPane scrollEntrada = new JScrollPane();
	private JScrollPane scrollResponsavel = new JScrollPane();
	private JScrollPane scrollDepartamento = new JScrollPane();
	private JScrollPane scrollFornecedor = new JScrollPane();
	private JScrollPane scrollSolFornecedor = new JScrollPane();
	private JScrollPane scrollSolDepartamento = new JScrollPane();
	private JTabbedPane tabs = new JTabbedPane();
	
	public JanelaPrincipal() throws ParseException {
		frame.setSize(1000, 600);
		frame.setContentPane(tabs);

		scrollTipo.getViewport().add(tabelaTipo);
		scrollProduto.getViewport().add(tabelaProduto);
		scrollSaida.getViewport().add(tabelaSaida);
		scrollItem.getViewport().add(tabelaItem);
		scrollEntrada.getViewport().add(tabelaEntrada);
		scrollResponsavel.getViewport().add(tabelaResponsavel);
		scrollDepartamento.getViewport().add(tabelaDepartamento);
		scrollFornecedor.getViewport().add(tabelaFornecedor);
		scrollSolFornecedor.getViewport().add(tabelaSolFornecedor);
		scrollSolDepartamento.getViewport().add(tabelaSolDepartamento);

		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		dataEnt = new JFormattedTextField(mascaraData);
		dataSai = new JFormattedTextField(mascaraData);
		dataNFEEnt = new JFormattedTextField(mascaraData);
		dtSolDep = new JFormattedTextField(mascaraData);
		dtSolFor = new JFormattedTextField(mascaraData);

		panelTipo.add(new JLabel("Código"));
		panelTipo.add(codTipo);
		panelTipo.add(new JLabel("Nome"));
		panelTipo.add(nomeTipo);
		
		panelPro.add(new JLabel("Código"));
		panelPro.add(codPro);
		panelPro.add(new JLabel("Nome"));
		panelPro.add(nomePro);
		panelPro.add(new JLabel("Uso"));
		panelPro.add(usoPro);
		panelPro.add(new JLabel("Quantidade mínima"));
		panelPro.add(qtdMinima);
		panelPro.add(new JLabel("Quantidade de segurança"));
		panelPro.add(qtdSeguranca);
		panelPro.add(new JLabel("Quantidade máxima"));
		panelPro.add(qtdMaxima);
		panelPro.add(new JLabel("Consumo previsto"));
		panelPro.add(consumoPrevisto);
		panelPro.add(new JLabel("Preço"));
		panelPro.add(preco);
		panelPro.add(new JLabel("Peso"));
		panelPro.add(peso);
		panelPro.add(new JLabel("Tipo do Produto"));
		panelPro.add(codTipoPro);
		
		panelSai.add(new JLabel("Código"));
		panelSai.add(codSai);
		panelSai.add(new JLabel("Data"));
		panelSai.add(dataSai);
		panelSai.add(new JLabel("Descrição"));
		panelSai.add(descSaida);

		panelIte.add(new JLabel("Código Item"));
		panelIte.add(codItem);
		panelIte.add(new JLabel("Código Produto"));
		panelIte.add(codProItem);
		panelIte.add(new JLabel("Código Saída"));
		panelIte.add(codSaiItem);
		panelIte.add(new JLabel("Código Entrada"));
		panelIte.add(codEntItem);
		
		panelEnt.add(new JLabel("Código"));
		panelEnt.add(codEnt);
		panelEnt.add(new JLabel("Data da entrada"));
		panelEnt.add(dataEnt);
		panelEnt.add(new JLabel("Tipo de entrada"));
		panelEnt.add(tipoEnt);
		panelEnt.add(new JLabel("Nota Fiscal Eletrônica"));
		panelEnt.add(NFEEnt);
		panelEnt.add(new JLabel("Data da NFE"));
		panelEnt.add(dataNFEEnt);
		panelEnt.add(new JLabel("Tempo de entrada"));
		panelEnt.add(tempoEnt);
		panelEnt.add(new JLabel("Codigo do Fornecedor"));
		panelEnt.add(codForEnt);

		panelResp.add(new JLabel("Código"));
		panelResp.add(codResp);
		panelResp.add(new JLabel("Nome"));
		panelResp.add(nomeResp);
		panelResp.add(new JLabel("Contato"));
		panelResp.add(telResp);
		
		panelDep.add(new JLabel("Código"));
		panelDep.add(codDep);
		panelDep.add(new JLabel("Nome"));
		panelDep.add(nomeDep);
		panelDep.add(new JLabel("Andar"));
		panelDep.add(andarDep);
		panelDep.add(new JLabel("Prédio"));
		panelDep.add(predioDep);
		panelDep.add(new JLabel("Telefone"));
		panelDep.add(telDep);
		panelDep.add(new JLabel("Código do Responsável"));
		panelDep.add(codRespDep);
		
		panelFor.add(new JLabel("Código"));
		panelFor.add(codFor);
		panelFor.add(new JLabel("Nome"));
		panelFor.add(nomeFor);
		panelFor.add(new JLabel("Rua"));
		panelFor.add(ruaFor);
		panelFor.add(new JLabel("Número"));
		panelFor.add(numFor);
		panelFor.add(new JLabel("Bairro"));
		panelFor.add(bairFor);
		panelFor.add(new JLabel("Cidade"));
		panelFor.add(cidFor);
		panelFor.add(new JLabel("Estado"));
		panelFor.add(estFor);
		panelFor.add(new JLabel("Telefone"));
		panelFor.add(telFor);
		
		panelSolFor.add(new JLabel("Código da Solicitação"));
		panelSolFor.add(codSolFor);
		panelSolFor.add(new JLabel("Código do Fornecedor"));
		panelSolFor.add(codForSol);
		panelSolFor.add(new JLabel("Data"));
		panelSolFor.add(dtSolFor);
		
		panelSolDep.add(new JLabel("Código da Solicitação"));
		panelSolDep.add(codSolDep);
		panelSolDep.add(new JLabel("Código do Departamento"));
		panelSolDep.add(codDepSol);
		panelSolDep.add(new JLabel("Data"));
		panelSolDep.add(dtSolDep);
		
		JPanel panelBtnTipo = new JPanel( new FlowLayout());
		panelBtnTipo.add(salvarTipo);
		panelBtnTipo.add(alterTipo);
		panelBtnTipo.add(pesqTipo);
		panelBtnTipo.add(remTipo);
		
		salvarTipo.addActionListener(this);
		alterTipo.addActionListener(this);
		pesqTipo.addActionListener(this);
		remTipo.addActionListener(this);
		
		JPanel panelBtnPro = new JPanel( new FlowLayout());
		panelBtnPro.add(salvarPro);
		panelBtnPro.add(alterPro);
		panelBtnPro.add(pesqPro);
		panelBtnPro.add(remPro);
		
		salvarPro.addActionListener(this);
		alterPro.addActionListener(this);
		pesqPro.addActionListener(this);
		remPro.addActionListener(this);
		
		JPanel panelBtnSai = new JPanel( new FlowLayout());
		panelBtnSai.add(salvarSai);
		panelBtnSai.add(alterSai);
		panelBtnSai.add(pesqSai);
		panelBtnSai.add(remSai);
		salvarSai.addActionListener(this);
		alterSai.addActionListener(this);
		pesqSai.addActionListener(this);
		remSai.addActionListener(this);
		
		JPanel panelBtnIte = new JPanel( new FlowLayout());
		panelBtnIte.add(salvarItem);
		panelBtnIte.add(alterItem);
		panelBtnIte.add(pesqItem);
		panelBtnIte.add(remItem);
		salvarItem.addActionListener(this);
		alterItem.addActionListener(this);
		pesqItem.addActionListener(this);
		remItem.addActionListener(this);
		
		JPanel panelBtnEnt = new JPanel( new FlowLayout());
		panelBtnEnt.add(salvarEnt);
		panelBtnEnt.add(alterEnt);
		panelBtnEnt.add(pesqEnt);
		panelBtnEnt.add(remEnt);
		salvarEnt.addActionListener(this);
		alterEnt.addActionListener(this);
		pesqEnt.addActionListener(this);
		remEnt.addActionListener(this);
		
		JPanel panelBtnResp = new JPanel( new FlowLayout());
		panelBtnResp.add(salvarResp);
		panelBtnResp.add(alterResp);
		panelBtnResp.add(pesqResp);
		panelBtnResp.add(remResp);
		salvarResp.addActionListener(this);
		alterResp.addActionListener(this);
		pesqResp.addActionListener(this);
		remResp.addActionListener(this);
		
		JPanel panelBtnDep = new JPanel( new FlowLayout());
		panelBtnDep.add(salvarDep);
		panelBtnDep.add(alterDep);
		panelBtnDep.add(pesqDep);
		panelBtnDep.add(remDep);
		salvarDep.addActionListener(this);
		alterDep.addActionListener(this);
		pesqDep.addActionListener(this);
		remDep.addActionListener(this);
		
		JPanel panelBtnFor = new JPanel( new FlowLayout());
		panelBtnFor.add(salvarFor);
		panelBtnFor.add(alterFor);
		panelBtnFor.add(pesqFor);
		panelBtnFor.add(remFor);
		salvarFor.addActionListener(this);
		alterFor.addActionListener(this);
		pesqFor.addActionListener(this);
		remFor.addActionListener(this);
		
		JPanel panelBtnSolFor = new JPanel( new FlowLayout());
		panelBtnSolFor.add(salvarSolFor);
		panelBtnSolFor.add(alterSolFor);
		panelBtnSolFor.add(pesqSolFor);
		panelBtnSolFor.add(remSolFor);
		salvarSolFor.addActionListener(this);
		alterSolFor.addActionListener(this);
		pesqSolFor.addActionListener(this);
		remSolFor.addActionListener(this);
		
		JPanel panelBtnSolDep = new JPanel( new FlowLayout());
		panelBtnSolDep.add(salvarSolDep);
		panelBtnSolDep.add(alterSolDep);
		panelBtnSolDep.add(pesqSolDep);
		panelBtnSolDep.add(remSolDep);
		salvarSolDep.addActionListener(this);
		alterSolDep.addActionListener(this);
		pesqSolDep.addActionListener(this);
		remSolDep.addActionListener(this);
		
		principalTipo.add(panelTipo, BorderLayout.NORTH);
		principalProduto.add(panelPro, BorderLayout.NORTH);
		principalSaida.add(panelSai, BorderLayout.NORTH);
		principalItens.add(panelIte, BorderLayout.NORTH);
		principalEntrada.add(panelEnt, BorderLayout.NORTH);
		principalResponsavel.add(panelResp, BorderLayout.NORTH);
		principalDepartamento.add(panelDep, BorderLayout.NORTH);
		principalFornecedor.add(panelFor, BorderLayout.NORTH);
		principalSolicitacaoFORN.add(panelSolFor, BorderLayout.NORTH);
		principalSolicitacaoDPTO.add(panelSolDep, BorderLayout.NORTH);

		principalTipo.add(scrollTipo, BorderLayout.CENTER);
		principalProduto.add(scrollProduto, BorderLayout.CENTER);
		principalSaida.add(scrollSaida, BorderLayout.CENTER);
		principalItens.add(scrollItem, BorderLayout.CENTER);
		principalEntrada.add(scrollEntrada, BorderLayout.CENTER);
		principalResponsavel.add(scrollResponsavel, BorderLayout.CENTER);
		principalDepartamento.add(scrollDepartamento, BorderLayout.CENTER);
		principalFornecedor.add(scrollFornecedor, BorderLayout.CENTER);
		principalSolicitacaoFORN.add(scrollSolFornecedor, BorderLayout.CENTER);
		principalSolicitacaoDPTO.add(scrollSolDepartamento, BorderLayout.CENTER);

		principalTipo.add(panelBtnTipo, BorderLayout.SOUTH);
		principalProduto.add(panelBtnPro, BorderLayout.SOUTH);
		principalSaida.add(panelBtnSai, BorderLayout.SOUTH);
		principalItens.add(panelBtnIte, BorderLayout.SOUTH);
		principalEntrada.add(panelBtnEnt, BorderLayout.SOUTH);
		principalResponsavel.add(panelBtnResp, BorderLayout.SOUTH);
		principalDepartamento.add(panelBtnDep, BorderLayout.SOUTH);
		principalFornecedor.add(panelBtnFor, BorderLayout.SOUTH);
		principalSolicitacaoFORN.add(panelBtnSolFor, BorderLayout.SOUTH);
		principalSolicitacaoDPTO.add(panelBtnSolDep, BorderLayout.SOUTH);
		
		tabs.addTab("Tipos", principalTipo);
		tabs.addTab("Produtos", principalProduto);
		tabs.addTab("Saidas", principalSaida);
		tabs.addTab("Itens", principalItens);
		tabs.addTab("Entradas", principalEntrada);
		tabs.addTab("Responsáveis", principalResponsavel);
		tabs.addTab("Departamentos", principalDepartamento);
		tabs.addTab("Fornecedor", principalFornecedor);
		tabs.addTab("Solicitações Fornecedor", principalSolicitacaoFORN);
		tabs.addTab("Solicitações Departamento", principalSolicitacaoDPTO);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws ParseException {
		new JanelaPrincipal();
	}
	
	public void toTipo(Tipo t){
		codTipo.setText(String.valueOf(t.getId()));
		nomeTipo.setText(t.getNome());
	}
	public Tipo fromTipo(){
		Tipo t = new Tipo();
		try{
			t.setId(Integer.parseInt(codTipo.getText()));
		}
		catch(NumberFormatException e){}
		t.setNome(nomeTipo.getText());
		return t;
	}
	
	public void toProduto(Produto p){
		codPro.setText(String.valueOf(p.getId()));
		nomePro.setText(p.getNome());
		usoPro.setText(p.getUso());
		qtdMinima.setText(String.valueOf(p.getQtdMinima()));
		qtdSeguranca.setText(String.valueOf(p.getQtdSeguranca()));
		qtdMaxima.setText(String.valueOf(p.getQtdMaxima()));
		consumoPrevisto.setText(p.getConsumoPrevisto());
		preco.setText(String.valueOf(p.getPreco()));
		peso.setText(String.valueOf(p.getPeso()));
		codTipoPro.setText(String.valueOf(p.getTipo()));
	}
	public Produto fromProduto(){
		Produto p = new Produto();
		try{
			p.setId(Integer.parseInt(codPro.getText()));
		}
		catch(NumberFormatException e){}
		
		p.setNome(nomePro.getText());
		p.setUso(usoPro.getText());
		
		try{
			p.setQtdMinima(Float.parseFloat(qtdMinima.getText().replace(",", ".")));
		}
		catch(NumberFormatException e){}
		
		try{
			p.setQtdSeguranca(Float.parseFloat(qtdSeguranca.getText().replace(",", ".")));
		}
		catch(NumberFormatException e){}
		
		try{
			p.setQtdMaxima(Float.parseFloat(qtdMaxima.getText().replace(",", ".")));
		}
		catch(NumberFormatException e){}
		
		p.setConsumoPrevisto(consumoPrevisto.getText());
		try{
			p.setPreco(Float.parseFloat(preco.getText().replace(",", ".")));
		}
		catch(NumberFormatException e){}		
		
		try{
			p.setPeso(Float.parseFloat(peso.getText().replace(",", ".")));
		}
		catch(NumberFormatException e){}	
		
		try{
			p.setTipo(Integer.parseInt(codTipoPro.getText()));
		}
		catch(NumberFormatException e){}	
		
		return p;
	}
	
	public void toSaida(Saida s) {
		codSai.setText(String.valueOf(s.getIdSaida()));
		dataSai.setText(in.format(s.getData()));
		descSaida.setText(s.getDescricao());
	}
	public Saida fromSaida() throws ParseException{
		Saida s = new Saida();
		try{
			s.setIdSaida(Integer.parseInt(codSai.getText()));
		}
		catch(NumberFormatException e){}
		try{
			s.setData(in.parse(dataSai.getText()));
		}
		catch(ParseException e){}
		s.setDescricao(descSaida.getText());
		return s;
	}
	
	public void toItem(Item i){
		codItem.setText(String.valueOf(i.getIdItem()));
		codProItem.setText(String.valueOf(i.getIdProduto()));
		codSaiItem.setText(String.valueOf(i.getIdSaida()));
		codEntItem.setText(String.valueOf(i.getIdEntrada()));
	}
	public Item fromItem(){
		Item i = new Item();
		try{
			i.setIdItem(Integer.parseInt(codItem.getText()));
		} catch(NumberFormatException e){}
		try{
			i.setIdProduto(Integer.parseInt(codProItem.getText()));
		} catch(NumberFormatException e){}
		try{
			i.setIdSaida(Integer.parseInt(codSaiItem.getText()));
		} catch(NumberFormatException e){}
		try{
			i.setIdEntrada(Integer.parseInt(codEntItem.getText()));
		} catch(NumberFormatException e){}
		return i;
	}
	
	public void toEntrada(Entrada e){
		codEnt.setText(String.valueOf(e.getIdEntrada()));
		dataEnt.setText(in.format(e.getData()));
		tipoEnt.setText(e.getTipoTransf());
		NFEEnt.setText(e.getNFE());
		dataNFEEnt.setText(in.format(e.getDataEmissarNFE()));
		tempoEnt.setText(String.valueOf(e.getTempo()));
		codForEnt.setText(String.valueOf(e.getIdFornecedor()));
	}
	public Entrada fromEntrada() throws ParseException{
		Entrada e = new Entrada();
		try{
			e.setIdEntrada(Integer.parseInt(codEnt.getText()));
		} catch(NumberFormatException e1){}
		try{
			e.setData(in.parse(dataEnt.getText()));
		}
		catch(ParseException e1){}
		e.setTipoTransf(tipoEnt.getText());
		e.setNFE(NFEEnt.getText());
		try{
			e.setDataEmissarNFE(in.parse(dataNFEEnt.getText()));
		}
		catch(ParseException e1){}
		try{
			e.setTempo(Float.parseFloat(tempoEnt.getText().replace(",", ".")));
		} catch(NumberFormatException e1){}
		try{
			e.setIdFornecedor(Integer.parseInt(codForEnt.getText()));
		} catch(NumberFormatException e1){}
		return e;
	}
	
	public void toResponsavel(Responsavel r){
		codResp.setText(String.valueOf(r.getId()));
		nomeResp.setText(r.getNome());
		telResp.setText(r.getTel());
	}
	public Responsavel fromResponsavel(){
		Responsavel r = new Responsavel();
		try{
			r.setId(Integer.parseInt(codResp.getText()));
		}
		catch(NumberFormatException e){}
		r.setNome(nomeResp.getText());
		r.setTel(telResp.getText());
		return r;
	}	
	
	public void toDepartamento(Departamento d){
		codDep.setText(String.valueOf(d.getId()));
		nomeDep.setText(d.getNome());
		andarDep.setText(d.getAndar());
		predioDep.setText(d.getPredio());
		telDep.setText(d.getTelefone());
		codRespDep.setText(String.valueOf(d.getCodResponsavel()));
	}
	public Departamento fromDepartamento(){
		Departamento d = new Departamento();
		try{
			d.setId(Integer.parseInt(codDep.getText()));
		}
		catch(NumberFormatException e){}
		d.setNome(nomeDep.getText());
		d.setAndar(andarDep.getText());
		d.setPredio(predioDep.getText());
		d.setTelefone(telDep.getText());
		try{
			d.setCodResponsavel(Integer.parseInt(codRespDep.getText()));
		}
		catch(NumberFormatException e){}
		return d;
	}	
	
	public void toFornecedor(Fornecedor f){
		codFor.setText(String.valueOf(f.getId()));
		ruaFor.setText(f.getRua());
		numFor.setText(String.valueOf(f.getNumero()));
		bairFor.setText(f.getBairro());
		cidFor.setText(f.getCidade());
		estFor.setText(f.getEstado());
		nomeFor.setText(f.getNome());
		telFor.setText(f.getTel());
	}
	public Fornecedor fromFornecedor(){
		Fornecedor f = new Fornecedor();
		try{
			f.setId(Integer.parseInt(codFor.getText()));
		}
		catch(NumberFormatException e){}
		f.setRua(ruaFor.getText());
		try{
			f.setNumero(Integer.parseInt(numFor.getText()));
		}
		catch(NumberFormatException e){}
		f.setBairro(bairFor.getText());
		f.setCidade(cidFor.getText());
		f.setEstado(estFor.getText());
		f.setNome(nomeFor.getText());
		f.setTel(telFor.getText());
		return f;
	}		
	
	public void toSolicitacaoFornecedor(SolicitacaoFornecedor sF){
		codSolFor.setText(String.valueOf(sF.getId()));
		codForSol.setText(String.valueOf(sF.getIdFornecedor()));
		dtSolFor.setText(in.format(sF.getData()));		
	}
	public SolicitacaoFornecedor fromSolicitacaoFornecedor() throws ParseException{
		SolicitacaoFornecedor sF = new SolicitacaoFornecedor();
		try{
			sF.setId(Integer.parseInt(codSolFor.getText()));
		}
		catch(NumberFormatException e){}
		try{
			sF.setIdFornecedor(Integer.parseInt(codForSol.getText()));
		}
		catch(NumberFormatException e){}
		try{
			sF.setData(in.parse(dtSolFor.getText()));
		}
		catch(ParseException e){}
		return sF;
	}	
	
	public void toSolicitacaoDepartamento(SolicitacaoDepartamento sD){
		codSolDep.setText(String.valueOf(sD.getId()));
		codDepSol.setText(String.valueOf(sD.getIdDepartamento()));
		dtSolDep.setText(in.format(sD.getData()));		
	}
	public SolicitacaoDepartamento fromSolicitacaoDepartamento() throws ParseException{
		SolicitacaoDepartamento sD = new SolicitacaoDepartamento();
		try{
			sD.setId(Integer.parseInt(codSolDep.getText()));
		}
		catch(NumberFormatException e){}
		try{
			sD.setIdDepartamento(Integer.parseInt(codDepSol.getText()));
		}
		catch(NumberFormatException e){}
		try{
			sD.setData(in.parse(dtSolDep.getText()));
		}
		catch(ParseException e){}
		return sD;		
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		String cmd = action.getActionCommand();
		if(cmd.contains("Salvar Tipo")){
			try {
				Tipo t = fromTipo();
				if(t.getNome().equals("")){
					JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);					
				}
				else{
					ctrTipo.adicionar(t);
					tabelaTipo.revalidate();
					scrollTipo.repaint();
				}
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Tipo")){
			try {
				Tipo t = fromTipo();
				if(t.getNome().equals("")){
					JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);					
				}
				else{
					ctrTipo.atualizar(t);
					tabelaTipo.revalidate();
					scrollTipo.repaint();
				}
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Tipo")){
			try {
				ctrTipo.consultar(fromTipo());
				tabelaTipo.revalidate();
				scrollTipo.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Tipo")){
			try {
				ctrTipo.remover(fromTipo());
				tabelaTipo.revalidate();
				scrollTipo.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Produto")){
			try {
				ctrProduto.adicionar(fromProduto());
				tabelaProduto.revalidate();
				scrollProduto.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Produto")){
			try {
				ctrProduto.atualizar(fromProduto());
				tabelaProduto.revalidate();
				scrollProduto.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Produto")){
			try {
				ctrProduto.consultar(fromProduto());
				tabelaProduto.revalidate();
				scrollProduto.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Produto")){
			try {
				ctrProduto.remover(fromProduto());
				tabelaProduto.revalidate();
				scrollProduto.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Saida")){
			try {
				ctrSaida.adicionar(fromSaida());
				tabelaSaida.revalidate();
				scrollSaida.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro no campo Data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Saida")){
			try {
				ctrSaida.atualizar(fromSaida());
				tabelaSaida.revalidate();
				scrollSaida.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro no campo Data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Saida")){
			try {
				ctrSaida.consultar(fromSaida());
				tabelaSaida.revalidate();
				scrollSaida.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro no campo Data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Saida")){
			try {
				ctrSaida.remover(fromSaida());
				tabelaSaida.revalidate();
				scrollSaida.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro no campo Data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Item")){
			try {
				ctrItem.adicionar(fromItem());
				tabelaItem.revalidate();
				scrollItem.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Item")){
			try {
				ctrItem.atualizar(fromItem());
				tabelaItem.revalidate();
				scrollItem.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Item")){
			try {
				ctrItem.consultar(fromItem());
				tabelaItem.revalidate();
				scrollItem.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Item")){
			try {
				ctrItem.remover(fromItem());
				tabelaItem.revalidate();
				scrollItem.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Entrada")){
			try {
				ctrEntrada.adicionar(fromEntrada());
				tabelaEntrada.revalidate();
				scrollEntrada.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Entrada")){
			try {
				ctrEntrada.atualizar(fromEntrada());
				tabelaEntrada.revalidate();
				scrollEntrada.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Entrada")){
			try {
				ctrEntrada.consultar(fromEntrada());
				tabelaEntrada.revalidate();
				scrollEntrada.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Entrada")){
			try {
				ctrEntrada.remover(fromEntrada());
				tabelaEntrada.revalidate();
				scrollEntrada.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Responsavel")){
			try {
				Responsavel r = fromResponsavel();
				if(r.getNome().equals("") || r.getTel().equals("")){
					JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				}
				else{
					ctrResponsavel.adicionar(r);
					tabelaResponsavel.revalidate();
					scrollResponsavel.repaint();
				}
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Responsavel")){
			try {
				Responsavel r = fromResponsavel();
				if(r.getNome().equals("") || r.getTel().equals("")){
					JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				}
				else{
					ctrResponsavel.atualizar(r);
					tabelaResponsavel.revalidate();
					scrollResponsavel.repaint();
				}
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Responsavel")){
			try {
				ctrResponsavel.consultar(fromResponsavel());
				tabelaResponsavel.revalidate();
				scrollResponsavel.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Responsavel")){
			try {
				ctrResponsavel.remover(fromResponsavel());
				tabelaResponsavel.revalidate();
				scrollResponsavel.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Departamento")){
			try {
				ctrDepartamento.adicionar(fromDepartamento());
				tabelaDepartamento.revalidate();
				scrollDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Departamento")){
			try {
				ctrDepartamento.atualizar(fromDepartamento());
				tabelaDepartamento.revalidate();
				scrollDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Departamento")){
			try {
				ctrDepartamento.consultar(fromDepartamento());
				tabelaDepartamento.revalidate();
				scrollDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Departamento")){
			try {
				ctrDepartamento.remover(fromDepartamento());
				tabelaDepartamento.revalidate();
				scrollDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Fornecedor")){
			try {
				ctrFornecedor.adicionar(fromFornecedor());
				tabelaFornecedor.revalidate();
				scrollFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Fornecedor")){
			try {
				ctrFornecedor.atualizar(fromFornecedor());
				tabelaFornecedor.revalidate();
				scrollFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Fornecedor")){
			try {
				ctrFornecedor.consultar(fromFornecedor());
				tabelaFornecedor.revalidate();
				scrollFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Fornecedor")){
			try {
				ctrFornecedor.remover(fromFornecedor());
				tabelaFornecedor.revalidate();
				scrollFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Solicitacao Fornecedor")){
			try {
				ctrSolFornecedor.adicionar(fromSolicitacaoFornecedor());
				tabelaSolFornecedor.revalidate();
				scrollSolFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Solicitacao Fornecedor")){
			try {
				ctrSolFornecedor.atualizar(fromSolicitacaoFornecedor());
				tabelaSolFornecedor.revalidate();
				scrollSolFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Solicitacao Fornecedor")){
			try {
				ctrSolFornecedor.consultar(fromSolicitacaoFornecedor());
				tabelaSolFornecedor.revalidate();
				scrollSolFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Solicitacao Fornecedor")){
			try {
				ctrSolFornecedor.remover(fromSolicitacaoFornecedor());
				tabelaSolFornecedor.revalidate();
				scrollSolFornecedor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Salvar Solicitacao Departamento")){
			try {
				ctrSolDepartamento.adicionar(fromSolicitacaoDepartamento());
				tabelaSolDepartamento.revalidate();
				scrollSolDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Alterar Solicitacao Departamento")){
			try {
				ctrSolDepartamento.atualizar(fromSolicitacaoDepartamento());
				tabelaSolDepartamento.revalidate();
				scrollSolDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Pesquisar Solicitacao Departamento")){
			try {
				ctrSolDepartamento.consultar(fromSolicitacaoDepartamento());
				tabelaSolDepartamento.revalidate();
				scrollSolDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if (cmd.contains("Remover Solicitacao Departamento")){
			try {
				ctrSolDepartamento.remover(fromSolicitacaoDepartamento());
				tabelaSolDepartamento.revalidate();
				scrollSolDepartamento.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro com data","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}	
}