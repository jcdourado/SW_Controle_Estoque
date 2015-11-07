package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JanelaPrincipal {
	private JFrame frame = new JFrame("Tela Principal");
	private JPanel panelCli = new JPanel(new GridLayout(11, 2));
	private JPanel panelDep = new JPanel(new GridLayout(6, 2));
	private JPanel panelFor = new JPanel(new GridLayout(5, 2));
	private JPanel panelEnt = new JPanel(new GridLayout(10, 2));
	private JPanel panelSai = new JPanel(new GridLayout(7, 2));
	
	private JTextField codCli = new JTextField();
	private JTextField nomeCli = new JTextField();
	private JTextField uso = new JTextField();
	private JTextField qtdEstoque = new JTextField();
	private JTextField qtdMinima = new JTextField();
	private JTextField qtdSeguranca = new JTextField();
	private JTextField qtdMaxima = new JTextField();
	private JTextField consumo = new JTextField();
	private JTextField consumoPrevisto = new JTextField();
	private JTextField mediaEntrega = new JTextField();
	private JButton salvarCli = new JButton("Salvar");
	private JButton limparCli = new JButton("Limpar");
	
	private JTextField codFor = new JTextField();
	private JTextField local = new JTextField();
	private JTextField nomeFor = new JTextField();
	private JTextField contato = new JTextField();
	private JButton salvarFor = new JButton("Salvar");
	private JButton limparFor = new JButton("Limpar");
	
	private JTextField codDep = new JTextField();
	private JTextField nomeDep = new JTextField();
	private JTextField localDep = new JTextField();
	private JTextField contatoDep = new JTextField();
	private JTextField consumoDep = new JTextField();
	private JButton salvarDep = new JButton("Salvar");
	private JButton limparDep = new JButton("Limpar");
	
	private JTextField codEnt = new JTextField();
	private JTextField dataEnt = new JTextField();
	private JTextField tipoEnt = new JTextField();
	private JTextField NFEEnt = new JTextField();
	private JTextField dataNFEEnt = new JTextField();
	private JTextField qtdEnt = new JTextField();
	private JTextField tempoEnt = new JTextField();
	private JTextField precoEnt = new JTextField();
	private JTextField pesoEnt = new JTextField();
	private JButton salvarEnt = new JButton("Salvar");
	private JButton limparEnt = new JButton("Limpar");
	
	private JTextField codSai = new JTextField();
	private JTextField tipoSai = new JTextField();
	private JTextField dataSai = new JTextField();
	private JTextField precoSai = new JTextField();
	private JTextField tempoSai = new JTextField();
	private JTextField pesoSai = new JTextField();
	private JButton salvarSai = new JButton("Salvar");
	private JButton limparSai = new JButton("Limpar");
	
	private JTabbedPane tabs = new JTabbedPane();
	
	public JanelaPrincipal() {
		frame.setSize(500, 400);
		tabs.setBounds(0, 0, 502, 371);
		frame.getContentPane().add(tabs);
		
		panelCli.add(new JLabel("Código"));
		panelCli.add(codCli);
		panelCli.add(new JLabel("Nome"));
		panelCli.add(nomeCli);
		panelCli.add(new JLabel("Uso"));
		panelCli.add(uso);
		panelCli.add(new JLabel("Qtd em Estoque"));
		panelCli.add(qtdEstoque);
		panelCli.add(new JLabel("Qtd Minima"));
		panelCli.add(qtdMinima);
		panelCli.add(new JLabel("Qtd de segurança"));
		panelCli.add(qtdSeguranca);
		panelCli.add(new JLabel("Qtd Máxima"));
		panelCli.add(qtdMaxima);
		panelCli.add(new JLabel("Consumo"));
		panelCli.add(consumo);
		panelCli.add(new JLabel("Consumo Previsto"));
		panelCli.add(consumoPrevisto);
		panelCli.add(new JLabel("Media de Entrega"));
		panelCli.add(mediaEntrega);
		panelCli.add(salvarCli);
		panelCli.add(limparCli);
		
		panelFor.add(new JLabel("Codigo"));
		panelFor.add(codFor);
		panelFor.add(new JLabel("Nome"));
		panelFor.add(nomeFor);
		panelFor.add(new JLabel("Contato"));
		panelFor.add(contato);
		panelFor.add(new JLabel("Localização"));
		panelFor.add(local);
		panelFor.add(salvarFor);
		panelFor.add(limparFor);
		
		panelDep.add(new JLabel("Codigo"));
		panelDep.add(codDep);
		panelDep.add(new JLabel("Nome"));
		panelDep.add(nomeDep);
		panelDep.add(new JLabel("Localização"));
		panelDep.add(localDep);
		panelDep.add(new JLabel("Contato"));
		panelDep.add(contatoDep);
		panelDep.add(new JLabel("Media de Consumo"));
		panelDep.add(consumoDep);
		panelDep.add(salvarDep);
		panelDep.add(limparDep);
		
		panelEnt.add(new JLabel("Codigo"));
		panelEnt.add(codEnt);
		panelEnt.add(new JLabel("Data da Entrada"));
		panelEnt.add(dataEnt);
		panelEnt.add(new JLabel("Tipo de Entrada"));
		panelEnt.add(tipoEnt);
		panelEnt.add(new JLabel("NFE"));
		panelEnt.add(NFEEnt);
		panelEnt.add(new JLabel("Data da NFE"));
		panelEnt.add(dataNFEEnt);
		panelEnt.add(new JLabel("Quantidade de Entrada"));
		panelEnt.add(qtdEnt);
		panelEnt.add(new JLabel("Tempo de Entrada"));
		panelEnt.add(tempoEnt);
		panelEnt.add(new JLabel("Preço Total"));
		panelEnt.add(precoEnt);
		panelEnt.add(new JLabel("Peso Total"));
		panelEnt.add(pesoEnt);
		panelEnt.add(salvarEnt);
		panelEnt.add(limparEnt);
		
		panelSai.add(new JLabel("Codigo de Saida"));
		panelSai.add(codSai);
		panelSai.add(new JLabel("Tipo de Saida"));
		panelSai.add(tipoSai);
		panelSai.add(new JLabel("Data da Saida"));
		panelSai.add(dataSai);
		panelSai.add(new JLabel("Preço da Saida"));
		panelSai.add(precoSai);
		panelSai.add(new JLabel("Tempo da Saida"));
		panelSai.add(tempoSai);
		panelSai.add(new JLabel("Peso da Saida"));
		panelSai.add(pesoSai);
		panelSai.add(salvarSai);
		panelSai.add(limparSai);
		
		tabs.addTab("Cliente", panelCli);
		tabs.addTab("Fornecedor", panelFor);
		tabs.addTab("Departamento", panelDep);
		tabs.addTab("Entrada", panelEnt);
		tabs.addTab("Saida", panelSai);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JanelaPrincipal();
	}
}
