package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControllerProduto;
import controller.ControllerProdutoSaidaSolicitacao;
import controller.ControllerSolDepartamento;
import model.Produto;
import model.ProdutoSolicitacaoSaida;
import model.SolicitacaoDepartamento;
import model.SolicitacaoProdutoDepartamento;
import utilities.EstoqueException;

public class JanelaSolDepartamento implements ActionListener{
	SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField codSolDep = new JTextField(10);
	private JTextField codDepSol = new JTextField(10);
	private JTextField qtdRestanteGeral = new JTextField(10);
	private JFormattedTextField dtSolDep;
	private SolicitacaoDepartamento solD;
	private JFrame janela = new JFrame("Solicitação Departamento");
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JPanel panelSolFor;
	private JPanel panelTables;
	private JScrollPane scrollTableSolFor;
	private JScrollPane scrollTableProEnt;
	
	private ControllerSolDepartamento cSolicitacaoProdutoFornecedor = new ControllerSolDepartamento();
	private ControllerProdutoSaidaSolicitacao ctrSolFor = new ControllerProdutoSaidaSolicitacao();
	private ControllerProduto ctrProdutos = new ControllerProduto();
	
	private JTable tableSolFor = new JTable(ctrSolFor);
	private JTable tableSolPro = new JTable(cSolicitacaoProdutoFornecedor);
	
	public JanelaSolDepartamento(SolicitacaoDepartamento solDep) throws ParseException, EstoqueException{
		this.solD = solDep;
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		dtSolDep = new JFormattedTextField(mascaraData);
		panelPrincipal = new JPanel(new BorderLayout());
		panelBtn = new JPanel(new FlowLayout());
		panelSolFor = new JPanel(new GridLayout(4,2));
		
		codSolDep.setText(String.valueOf(solD.getId()));
		codDepSol.setText(String.valueOf(solD.getIdDepartamento()));
		dtSolDep.setText(in.format(solD.getData()));
		qtdRestanteGeral.setText(String.valueOf(solD.qtdRestanteGeral()));

		codSolDep.setEditable(false);
		codDepSol.setEditable(false);
		dtSolDep.setEditable(false);
		qtdRestanteGeral.setEditable(false);
		
		panelSolFor.add(new JLabel("Código da Solicitação"));
		panelSolFor.add(codSolDep);
		panelSolFor.add(new JLabel("Código do Departamento"));
		panelSolFor.add(codDepSol);
		panelSolFor.add(new JLabel("Data"));
		panelSolFor.add(dtSolDep);
		panelSolFor.add(new JLabel("Quantidade restante"));
		panelSolFor.add(qtdRestanteGeral);
		panelPrincipal.add(panelSolFor,BorderLayout.NORTH);
		
		scrollTableProEnt = new JScrollPane();
		scrollTableSolFor = new JScrollPane();
		
		scrollTableSolFor.setViewportView(tableSolPro);
		scrollTableProEnt.setViewportView(tableSolFor);
		
		SolicitacaoProdutoDepartamento pSol = new SolicitacaoProdutoDepartamento();
		ProdutoSolicitacaoSaida pEnt = new ProdutoSolicitacaoSaida();
		
		panelTables = new JPanel(new FlowLayout());
		panelTables.add(scrollTableProEnt);
		panelTables.add(scrollTableSolFor);
		
		pEnt.setIdSolicitacao(solD.getId());
		pSol.setIdSolicitacao(solD.getId());
		ctrSolFor.consultar(pEnt);
		cSolicitacaoProdutoFornecedor.consultar(pSol);
		scrollTableSolFor.repaint();
		scrollTableProEnt.repaint();
		tableSolPro.revalidate();
		tableSolFor.revalidate();
		
		JButton qtdRestante = new JButton("Calcular qtd restante");
		JButton calcPesoPorProduto = new JButton("Calcular peso");
		JButton calcPrecoPorProduto = new JButton("Calcular preço");
		JButton adicionarQtdproduto = new JButton("Adicionar solicitação produto");
		JButton removerQtdProduto = new JButton("Remover solicitação produto");
		
		qtdRestante.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		adicionarQtdproduto.addActionListener(this);
		removerQtdProduto.addActionListener(this);
		
		panelBtn.add(qtdRestante);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		panelBtn.add(adicionarQtdproduto);
		panelBtn.add(removerQtdProduto);
		
		panelPrincipal.add(panelTables,BorderLayout.CENTER);
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setSize(1000, 600);
		janela.setContentPane(panelPrincipal);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.equals("Calcular qtd restante")){
			try{
				int index = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(index);
				List<Produto> list;
				try {
					list = ctrProdutos.consultar(p);
					JOptionPane.showMessageDialog(null, solD.qtdRestante(list.get(0)), "Quantidade Restante", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular peso")){
			try{
				int index = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(index);
				List<Produto> list;
				try {
					list = ctrProdutos.consultar(p);
					JOptionPane.showMessageDialog(null, solD.calcPesoPorProduto(list.get(0)), "Peso", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Calcular preço")){
			try{
				int index = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));
				Produto p = new Produto();
				p.setId(index);
				List<Produto> list;
				try {
					list = ctrProdutos.consultar(p);
					JOptionPane.showMessageDialog(null, solD.calcPrecoPorProduto(list.get(0)), "Preço", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Adicionar solicitação produto")){
			
		}
		else{
			
		}
	}
}
