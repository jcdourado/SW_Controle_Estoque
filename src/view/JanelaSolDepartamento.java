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
	private JFrame j;
	private JPanel princ;
	private JPanel campos;
	private JPanel btn;
	private JTextField idProduto;
	private JTextField idSolicitacao;
	private JTextField qtd;
	
	private JFrame jSolSaida;
	private JPanel princSolSaida;
	private JPanel camposSolSaida;
	private JPanel btnSolSaida;
	private JTextField idProdutoSolSaida;
	private JTextField idSolicitacaoSolSaida;
	private JTextField idSaidaSolSaida;
	private JTextField usoSolSaida;
	private JTextField qtdSolSaida;
		
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
		
		scrollTableSolFor.getViewport().add(tableSolPro);
		scrollTableProEnt.getViewport().add(tableSolFor);
		
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
		JButton adicionarSaida = new JButton("Adicionar saida");
		JButton removerSaida = new JButton("Remover saida");
		
		qtdRestante.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		adicionarQtdproduto.addActionListener(this);
		removerQtdProduto.addActionListener(this);
		adicionarSaida.addActionListener(this);
		removerSaida.addActionListener(this);
		
		panelBtn.add(qtdRestante);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		panelBtn.add(adicionarQtdproduto);
		panelBtn.add(removerQtdProduto);
		panelBtn.add(adicionarSaida);
		panelBtn.add(removerSaida);
		
		panelPrincipal.add(panelTables,BorderLayout.CENTER);
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		
		janela.setSize(1100, 600);
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
			j = new JFrame("Solicitacao");
			princ = new JPanel(new BorderLayout());
			campos = new JPanel(new GridLayout(3, 2));
			btn = new JPanel(new FlowLayout());
			j.setSize(200,200);
			idProduto = new JTextField(10);
			idSolicitacao = new JTextField(10);
			qtd = new JTextField(10);
			
			idSolicitacao.setText(String.valueOf(solD.getId()));
			
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
			SolicitacaoProdutoDepartamento sPro;
			try {
				sPro = cSolicitacaoProdutoFornecedor.getSol().get(linha);
				cSolicitacaoProdutoFornecedor.remover(sPro);
				tableSolFor.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Ok")){
			try {
				cSolicitacaoProdutoFornecedor.adicionar(fromSolPro());
				SolicitacaoProdutoDepartamento sPro = new SolicitacaoProdutoDepartamento();
				sPro.setIdSolicitacao(Integer.parseInt(idSolicitacao.getText()));
				cSolicitacaoProdutoFornecedor.consultar(sPro);
				tableSolFor.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancelar")){
			j.setVisible(false);
		}
		else if(cmd.equals("Adicionar saida")){
			jSolSaida = new JFrame("Saida");
			princSolSaida = new JPanel(new BorderLayout());
			camposSolSaida = new JPanel(new GridLayout(5, 2));
			btnSolSaida = new JPanel(new FlowLayout());
			jSolSaida.setSize(500,200);
			
			idProdutoSolSaida = new JTextField(10);
			idSolicitacaoSolSaida = new JTextField(10);
			qtdSolSaida = new JTextField(10);
			idSaidaSolSaida = new JTextField(10);
			usoSolSaida = new JTextField(10);
			
			idSolicitacaoSolSaida.setText(String.valueOf(solD.getId()));
			
			camposSolSaida.add(new JLabel("Codigo Produto"));
			camposSolSaida.add(idProdutoSolSaida);
			camposSolSaida.add(new JLabel("Codigo Solicitacao Departamento"));
			camposSolSaida.add(idSolicitacaoSolSaida);
			camposSolSaida.add(new JLabel("Quantidade"));
			camposSolSaida.add(qtdSolSaida);
			camposSolSaida.add(new JLabel("Codigo Saida"));
			camposSolSaida.add(idSaidaSolSaida);
			camposSolSaida.add(new JLabel("Uso"));
			camposSolSaida.add(usoSolSaida);
			
			princSolSaida.add(camposSolSaida, BorderLayout.NORTH);
			
			JButton btnOk = new JButton("Add");
			JButton btnCancelar = new JButton("Cancel");
			btnOk.addActionListener(this);
			btnCancelar.addActionListener(this);
			
			btnSolSaida.add(btnOk);
			btnSolSaida.add(btnCancelar);
			princSolSaida.add(btnSolSaida, BorderLayout.SOUTH);
			
			jSolSaida.setContentPane(princSolSaida);
			jSolSaida.setVisible(true);
			jSolSaida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else if(cmd.equals("Remover saida")){
			int linha = tableSolFor.getSelectedRow();
			ProdutoSolicitacaoSaida sPro;
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
				ctrSolFor.adicionar(fromSolSaida());
				ProdutoSolicitacaoSaida sPro = new ProdutoSolicitacaoSaida();
				sPro.setIdSolicitacao(Integer.parseInt(idSolicitacaoSolSaida.getText()));
				ctrSolFor.consultar(sPro);
				tableSolPro.revalidate();
				scrollTableProEnt.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancel")){
			jSolSaida.setVisible(false);
		}
	}
	
	private SolicitacaoProdutoDepartamento fromSolPro(){
		SolicitacaoProdutoDepartamento solPro = new SolicitacaoProdutoDepartamento();
		solPro.setIdProduto(Integer.parseInt(idProduto.getText()));
		solPro.setIdSolicitacao(Integer.parseInt(idSolicitacao.getText()));
		solPro.setQuantidade(Float.parseFloat(qtd.getText()));
		return solPro;
	}
	
	private ProdutoSolicitacaoSaida fromSolSaida(){
		ProdutoSolicitacaoSaida solPro = new ProdutoSolicitacaoSaida();
		solPro.setIdProduto(Integer.parseInt(idProdutoSolSaida.getText()));
		solPro.setIdSolicitacao(Integer.parseInt(idSolicitacaoSolSaida.getText()));
		solPro.setQuantidade(Float.parseFloat(qtdSolSaida.getText()));
		solPro.setIdSaida(Integer.parseInt(idSaidaSolSaida.getText()));
		solPro.setUso(usoSolSaida.getText());
		return solPro;
	}
}
