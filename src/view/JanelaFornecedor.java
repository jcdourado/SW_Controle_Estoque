package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ControllerProduto;
import controller.ControllerSolicitacaoFornecedor;
import model.Fornecedor;
import model.Produto;
import model.SolicitacaoFornecedor;
import utilities.EstoqueException;


public class JanelaFornecedor implements ActionListener{
	private ControllerProduto ctrProduto = new ControllerProduto();
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
	private JFrame janela = new JFrame("Fornecedor");
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JPanel panelFor;
	private JScrollPane scroll;
	private ControllerSolicitacaoFornecedor ctrSolForn = new ControllerSolicitacaoFornecedor();
	private JTable tableSolicitacoes = new JTable(ctrSolForn);
	private Fornecedor d;
	
	public JanelaFornecedor(Fornecedor f) throws EstoqueException{
		d=f;
		janela.setSize(1200, 600);
		panelFor = new JPanel(new GridLayout(12,2));
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
		panelFor.add(new JLabel("Quantidade de produtos pedidos"));
		panelFor.add(qtdProdutosPedidosFornecedor);
		panelFor.add(new JLabel("Quantidade de produtos entregues"));
		panelFor.add(qtdEntregueTotal);
		panelFor.add(new JLabel("Resta algo"));
		panelFor.add(restaAlgoGeral);
		panelFor.add(new JLabel("Quantidade restante"));
		panelFor.add(qtdRestanteGeral);
		
		SolicitacaoFornecedor solFor = new SolicitacaoFornecedor();
		solFor.setIdFornecedor(d.getId());
 		ctrSolForn.consultar(solFor);
		tableSolicitacoes.revalidate();
		scroll.repaint();
		
		panelPrincipal.add(panelFor,BorderLayout.NORTH);
		panelPrincipal.add(scroll,BorderLayout.CENTER);
		
		JButton qtdPedidosPorProduto = new JButton("Calcular qtd pedidos Produto");
		JButton calcQtdTempo = new JButton("Calcular tempo medio entrega Produto");
		JButton qtdEntregueProduto = new JButton("Calcular qtd entregue Produto");
		JButton qtdRestante = new JButton("Calcular qtd restante Produto");
		JButton calcPesoPorProduto = new JButton("Calcular peso entregue Produto");
		JButton calcPrecoPorProduto = new JButton("Calcular preço entregue Produto");
		
		qtdPedidosPorProduto.addActionListener(this);
		calcQtdTempo.addActionListener(this);
		qtdEntregueProduto.addActionListener(this);
		qtdRestante.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		
		panelBtn.add(qtdPedidosPorProduto);
		panelBtn.add(calcQtdTempo);
		panelBtn.add(qtdEntregueProduto);
		panelBtn.add(qtdRestante);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setContentPane(panelPrincipal);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.equals("Calcular qtd pedidos Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.qtdPedidosPorProduto(rs), "Quantidade Pedidos Produto", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular tempo medio entrega Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.calcQtdTempo(rs), "Quantidade Tempo Produto", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
				
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular qtd entregue Produto")){
			try{
				int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.qtdEntregueProduto(rs), "Quantidade Produto Entregue", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular qtd restante Produto")){
			int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
			try{
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.qtdRestante(rs), "Quantidade Restante Produto", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular peso entregue Produto")){
			int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
			try{
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.calcPesoPorProduto(rs), "Peso por Produto", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else{
			int indexPro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
			try{
				Produto p = new Produto();
				p.setId(indexPro);
				List<Produto> list;
				try {
					list = ctrProduto.consultar(p);
					Produto rs = list.get(0);
					JOptionPane.showMessageDialog(null, d.calcPrecoPorProduto(rs), "Preco por Produto", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
	}
}
