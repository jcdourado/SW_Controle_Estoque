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
import controller.ControllerProdutoEntradaSolicitacao;
import controller.ControllerSolFornecedor;
import model.Produto;
import model.ProdutoSolicitacaoEntrada;
import model.SolicitacaoFornecedor;
import model.SolicitacaoProdutoFornecedor;
import utilities.EstoqueException;

public class JanelaSolFornecedor implements ActionListener{
	private JFrame j;
	private JPanel princ;
	private JPanel campos;
	private JPanel btn;
	private JTextField idProduto;
	private JTextField idSolicitacao;
	private JTextField qtd;
	
	private JFrame jSolEntrada;
	private JPanel princSolEntrada;
	private JPanel camposSolEntrada;
	private JPanel btnSolEntrada;
	private JTextField codEntrada;
	private JTextField qtdEntrada;
	private JTextField uso;
	private JTextField idProdutoEntrada;
	private JTextField idEntEntrada;
	private JTextField idSolEntrada;
	
	SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField codSolFor = new JTextField(10);
	private JTextField codForSol = new JTextField(10);
	private JTextField qtdRestanteGeral = new JTextField(10);
	private JFormattedTextField dtSolFor;
	private SolicitacaoFornecedor solF;
	private JFrame janela = new JFrame("Solicitação Fornecedor");
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JPanel panelSolFor;
	private JPanel panelTables;
	private JScrollPane scrollTableSolFor;
	private JScrollPane scrollTableProEnt;
	
	private ControllerSolFornecedor cSolicitacaoProdutoFornecedor = new ControllerSolFornecedor();
	private ControllerProdutoEntradaSolicitacao ctrSolFor = new ControllerProdutoEntradaSolicitacao();
	private ControllerProduto ctrProdutos = new ControllerProduto();
	
	private JTable tableSolFor = new JTable(ctrSolFor);
	private JTable tableSolPro = new JTable(cSolicitacaoProdutoFornecedor);
	
	public JanelaSolFornecedor(SolicitacaoFornecedor solFornecedor) throws ParseException, EstoqueException{
		this.solF = solFornecedor;
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		dtSolFor = new JFormattedTextField(mascaraData);
		panelPrincipal = new JPanel(new BorderLayout());
		panelBtn = new JPanel(new FlowLayout());
		panelSolFor = new JPanel(new GridLayout(4,2));
		
		codSolFor.setText(String.valueOf(solF.getId()));
		codForSol.setText(String.valueOf(solF.getIdFornecedor()));
		dtSolFor.setText(in.format(solF.getData()));
		qtdRestanteGeral.setText(String.valueOf(solF.qtdRestanteGeral()));

		codSolFor.setEditable(false);
		codForSol.setEditable(false);
		dtSolFor.setEditable(false);
		qtdRestanteGeral.setEditable(false);
		
		panelSolFor.add(new JLabel("Código da Solicitação"));
		panelSolFor.add(codSolFor);
		panelSolFor.add(new JLabel("Código do Fornecedor"));
		panelSolFor.add(codForSol);
		panelSolFor.add(new JLabel("Data"));
		panelSolFor.add(dtSolFor);
		panelSolFor.add(new JLabel("Quantidade restante"));
		panelSolFor.add(qtdRestanteGeral);
		panelPrincipal.add(panelSolFor,BorderLayout.NORTH);
		
		scrollTableProEnt = new JScrollPane();
		scrollTableSolFor = new JScrollPane();
		
		scrollTableSolFor.setViewportView(tableSolPro);
		scrollTableProEnt.setViewportView(tableSolFor);
		
		SolicitacaoProdutoFornecedor pSol = new SolicitacaoProdutoFornecedor();
		ProdutoSolicitacaoEntrada pEnt = new ProdutoSolicitacaoEntrada();
		
		panelTables = new JPanel(new FlowLayout());
		panelTables.add(scrollTableProEnt);
		panelTables.add(scrollTableSolFor);
		
		pEnt.setIdSolicitacao(solF.getId());
		pSol.setIdSolicitacao(solF.getId());
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
		JButton adicionarEntrada = new JButton("Adicionar entrada");
		JButton removerEntrada = new JButton("Remover entrada");
		
		qtdRestante.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		adicionarQtdproduto.addActionListener(this);
		removerQtdProduto.addActionListener(this);
		adicionarEntrada.addActionListener(this);
		removerEntrada.addActionListener(this);
		
		panelBtn.add(qtdRestante);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		panelBtn.add(adicionarQtdproduto);
		panelBtn.add(removerQtdProduto);
		panelBtn.add(adicionarEntrada);
		panelBtn.add(removerEntrada);
		
		panelPrincipal.add(panelTables,BorderLayout.CENTER);
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setSize(1400, 600);
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
					JOptionPane.showMessageDialog(null, solF.qtdRestante(list.get(0)), "Quantidade Restante", JOptionPane.INFORMATION_MESSAGE);;
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
					JOptionPane.showMessageDialog(null, solF.calcPesoPorProduto(list.get(0)), "Peso", JOptionPane.INFORMATION_MESSAGE);;
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
					JOptionPane.showMessageDialog(null, solF.calcPrecoPorProduto(list.get(0)), "Preço", JOptionPane.INFORMATION_MESSAGE);;
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.equals("Adicionar solicitação produto")){
			j = new JFrame("Solicitacao");
			princ = new JPanel(new BorderLayout());
			campos = new JPanel(new GridLayout(3, 2));
			btn = new JPanel(new FlowLayout());
			j.setSize(200,200);
			idProduto = new JTextField(10);
			idSolicitacao = new JTextField(10);
			qtd = new JTextField(10);
			
			idSolicitacao.setText(String.valueOf(solF.getId()));
			
			campos.add(new JLabel("Codigo Produto"));
			campos.add(idProduto);
			campos.add(new JLabel("Codigo Solicitacao"));
			campos.add(idSolicitacao);
			campos.add(new JLabel("Quantidade"));
			campos.add(qtd);
			
			princ.add(campos, BorderLayout.NORTH);
			
			JButton btnOk = new JButton("Ok");
			JButton btnCancelar = new JButton("Cancelar");
			btnOk.addActionListener(this);
			btnCancelar.addActionListener(this);
			
			btn.add(btnOk);
			btn.add(btnCancelar);
			princ.add(btn, BorderLayout.SOUTH);
			
			j.setContentPane(princ);
			j.setVisible(true);
			j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else if(cmd.equals("Remover solicitação produto")){
			int linha = tableSolPro.getSelectedRow();
			SolicitacaoProdutoFornecedor sPro;
			try {
				sPro = cSolicitacaoProdutoFornecedor.getSol().get(linha);
				cSolicitacaoProdutoFornecedor.remover(sPro);
				tableSolPro.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Ok")){
			try {
				cSolicitacaoProdutoFornecedor.adicionar(fromSolPro());
				SolicitacaoProdutoFornecedor sPro = new SolicitacaoProdutoFornecedor();
				sPro.setIdSolicitacao(Integer.parseInt(idSolicitacao.getText()));
				cSolicitacaoProdutoFornecedor.consultar(sPro);
				tableSolPro.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancelar")){
			j.setVisible(false);
		}
		else if(cmd.equals("Adicionar entrada")){
			jSolEntrada= new JFrame("Entrada");
			princSolEntrada = new JPanel(new BorderLayout());
			camposSolEntrada = new JPanel(new GridLayout(6, 2));
			btnSolEntrada = new JPanel(new FlowLayout());
			jSolEntrada.setSize(500,200);
			
			codEntrada = new JTextField(10);
			idEntEntrada = new JTextField(10);
			qtdEntrada = new JTextField(10);
			uso = new JTextField(30);
			idProdutoEntrada = new JTextField(10);
			idSolEntrada = new JTextField(10);
			
			idSolEntrada.setText(String.valueOf(solF.getId()));
			
			camposSolEntrada.add(new JLabel("Codigo"));
			camposSolEntrada.add(codEntrada);
			camposSolEntrada.add(new JLabel("Codigo Entrada"));
			camposSolEntrada.add(idEntEntrada);
			camposSolEntrada.add(new JLabel("Quantidade"));
			camposSolEntrada.add(qtdEntrada);
			camposSolEntrada.add(new JLabel("Uso"));
			camposSolEntrada.add(uso);
			camposSolEntrada.add(new JLabel("Código Produto"));
			camposSolEntrada.add(idProdutoEntrada);
			camposSolEntrada.add(new JLabel("Código Fornecedor"));
			camposSolEntrada.add(idSolEntrada);
			
			princSolEntrada.add(camposSolEntrada, BorderLayout.NORTH);
			
			JButton btnOk = new JButton("Add");
			JButton btnCancelar = new JButton("Cancel");
			btnOk.addActionListener(this);
			btnCancelar.addActionListener(this);
			
			btnSolEntrada.add(btnOk);
			btnSolEntrada.add(btnCancelar);
			princSolEntrada.add(btnSolEntrada, BorderLayout.SOUTH);
			
			jSolEntrada.setContentPane(princSolEntrada);
			jSolEntrada.setVisible(true);
			jSolEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else if(cmd.equals("Remover entrada")){
			int linha = tableSolFor.getSelectedRow();
			ProdutoSolicitacaoEntrada sPro;
			try {
				sPro = ctrSolFor.getSol().get(linha);
				ctrSolFor.remover(sPro);
				tableSolPro.revalidate();
				scrollTableProEnt.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}			
		}
		else if(cmd.equals("Add")){
			try {
				ctrSolFor.adicionar(fromSolEntrada());
				ProdutoSolicitacaoEntrada sPro = new ProdutoSolicitacaoEntrada();
				sPro.setIdSolicitacao(Integer.parseInt(idSolEntrada.getText()));
				ctrSolFor.consultar(sPro);
				tableSolPro.revalidate();
				scrollTableProEnt.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancel")){
			jSolEntrada.setVisible(false);
		}
	}
	
	private SolicitacaoProdutoFornecedor fromSolPro(){
		SolicitacaoProdutoFornecedor solPro = new SolicitacaoProdutoFornecedor();
		solPro.setIdProduto(Integer.parseInt(idProduto.getText()));
		solPro.setIdSolicitacao(Integer.parseInt(idSolicitacao.getText()));
		solPro.setQuantidade(Float.parseFloat(qtd.getText()));
		return solPro;
	}
	
	private ProdutoSolicitacaoEntrada fromSolEntrada(){
		ProdutoSolicitacaoEntrada solPro = new ProdutoSolicitacaoEntrada();
		solPro.setCodSolicitacaoEntrada(Integer.parseInt(codEntrada.getText()));
		solPro.setIdEntrada(Integer.parseInt(idEntEntrada.getText()));
		solPro.setQuantidade(Float.parseFloat(qtdEntrada.getText()));
		solPro.setUso(uso.getText());
		solPro.setIdProduto(Integer.parseInt(idProdutoEntrada.getText()));
		solPro.setIdSolicitacao(Integer.parseInt(idSolEntrada.getText()));
		return solPro;
	}
}
