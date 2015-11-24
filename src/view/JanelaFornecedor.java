package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ControllerSolicitacaoFornecedor;
import model.Fornecedor;


public class JanelaFornecedor implements ActionListener{
	private JTextField codFor = new JTextField(10);
	private JTextField ruaFor = new JTextField(30);
	private JTextField numFor = new JTextField(10);
	private JTextField bairFor = new JTextField(30);
	private JTextField cidFor = new JTextField(30);
	private JTextField estFor = new JTextField(20);
	private JTextField nomeFor = new JTextField(30);
	private JTextField telFor = new JTextField(20);
	private JTextField qtdProdutosPedidosFornecedor = new JTextField(10);
	private JTextField qtdEntregueTotal = new JTextField(10);
	private JTextField restaAlgoGeral = new JTextField(10);
	private JTextField qtdRestanteGeral = new JTextField(10);
	private JTextField calcPesoGeral = new JTextField(10);
	private JTextField calcPrecoGeral = new JTextField(10);
	private JFrame janela = new JFrame("Fornecedor");
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JPanel panelFor;
	private JScrollPane scroll;
	private ControllerSolicitacaoFornecedor ctrSolForn = new ControllerSolicitacaoFornecedor();
	private JTable tableSolicitacoes = new JTable(ctrSolForn);
	private Fornecedor d;
	
	public JanelaFornecedor(Fornecedor f){
		d=f;
		janela.setSize(1200, 600);
		panelFor = new JPanel(new GridLayout(14,2));
		panelPrincipal = new JPanel(new BorderLayout());
		panelBtn = new JPanel(new FlowLayout());
		scroll = new JScrollPane();
		scroll.setViewportView(tableSolicitacoes);
		
		codFor.setText(String.valueOf(f.getId()));
		ruaFor.setText(f.getRua());
		numFor.setText(String.valueOf(f.getNumero()));
		bairFor.setText(f.getBairro());
		cidFor.setText(f.getCidade());
		estFor.setText(f.getEstado());
		nomeFor.setText(f.getNome());
		telFor.setText(f.getTel());
		qtdProdutosPedidosFornecedor.setText(String.valueOf(f.qtdProdutosPedidosFornecedor()));
		qtdEntregueTotal.setText(String.valueOf(f.qtdEntregueTotal()));
		if(f.restaAlgoGeral()){
			restaAlgoGeral.setText("Sim");
		}
		else{
			restaAlgoGeral.setText("Não");
		}
		qtdRestanteGeral.setText(String.valueOf(f.qtdRestanteGeral()));
		calcPesoGeral.setText(String.valueOf(f.calcPesoGeral()));
		calcPrecoGeral.setText(String.valueOf(f.calcPrecoGeral()));
		
		codFor.setEditable(false);
		ruaFor.setEditable(false);
		numFor.setEditable(false);
		bairFor.setEditable(false);
		cidFor.setEditable(false);
		estFor.setEditable(false);
		nomeFor.setEditable(false);
		telFor.setEditable(false);
		qtdProdutosPedidosFornecedor.setEditable(false);
		qtdEntregueTotal.setEditable(false);
		restaAlgoGeral.setEditable(false);
		qtdRestanteGeral.setEditable(false);
		calcPesoGeral.setEditable(false);
		calcPrecoGeral.setEditable(false);
		
		panelFor.add(codFor);
		panelFor.add(nomeFor);
		panelFor.add(ruaFor);
		panelFor.add(numFor);
		panelFor.add(bairFor);
		panelFor.add(cidFor);
		panelFor.add(estFor);
		panelFor.add(telFor);
		panelFor.add(qtdProdutosPedidosFornecedor);
		panelFor.add(qtdEntregueTotal);
		panelFor.add(restaAlgoGeral);
		panelFor.add(qtdRestanteGeral);
		panelFor.add(calcPesoGeral);
		panelFor.add(calcPrecoGeral);
		
		panelPrincipal.add(panelFor,BorderLayout.NORTH);
		panelPrincipal.add(scroll,BorderLayout.CENTER);
		
		JButton qtdPedidosPorProduto = new JButton("Calcular qtd pedidos Produto");
		JButton calcQtdTempo = new JButton("Calcular tempo medio entrega Produto");
		JButton qtdEntregueProduto = new JButton("Calcular qtd entregue Produto");
		JButton qtdRestante = new JButton("Calcular qtd restante Produto");
		JButton calcPesoPorProduto = new JButton("Calcular peso entregue Produto");
		JButton calcPrecoPorProduto = new JButton("Calcular preço entregue Produto");
		
		
		janela.setContentPane(panelPrincipal);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
