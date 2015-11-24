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

import utilities.EstoqueException;
import controller.ControllerProduto;
import controller.ControllerProdutoSaidaSolicitacao;
import model.Produto;
import model.ProdutoSolicitacaoSaida;
import model.Saida;

public class JanelaSaida implements ActionListener{

	private JFrame jSolSaida;
	private JPanel princSolSaida;
	private JPanel camposSolSaida;
	private JPanel btnSolSaida;
	private JTextField idProdutoSolSaida;
	private JTextField idSolicitacaoSolSaida;
	private JTextField idSaidaSolSaida;
	private JTextField usoSolSaida;
	private JTextField qtdSolSaida;
	
	private JTextField codSai = new JTextField(10);
	private ControllerProduto ctrProduto = new ControllerProduto();
	private JFormattedTextField dataSai;
	private JTextField descSaida = new JTextField(30);
	private JTextField precoGeral = new JTextField(30);
	private JTextField pesoGeral = new JTextField(30);
	private JTextField contaisDoc = new JTextField(30);
	private JTextField isDoc = new JTextField(30);
	private JTextField qtdProDoados = new JTextField(30);
	private JFrame frame = new JFrame("Saida");
	private JPanel panelSai;
	private JPanel panelBtn;
	private JPanel panelPrinc;
	private ControllerProdutoSaidaSolicitacao ctrSolFor = new ControllerProdutoSaidaSolicitacao();
	private JScrollPane scrollTableSolFor;
	private JTable tableSolFor = new JTable(ctrSolFor);
	private Saida s;
	SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
	
	public JanelaSaida(Saida s) throws ParseException{
		this.s = s;
		frame.setSize(1400, 600);
		panelPrinc = new JPanel(new BorderLayout());
		panelSai = new JPanel(new GridLayout(8,2));
		scrollTableSolFor = new JScrollPane();
		
		scrollTableSolFor.getViewport().add(tableSolFor);
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		dataSai = new JFormattedTextField(mascaraData);
		codSai.setText(String.valueOf(s.getIdSaida()));
		dataSai.setText(in.format(s.getData()));
		descSaida.setText(s.getDescricao());
		if(s.isDoacao()){
			isDoc.setText("Sim");			
		}
		else{
			isDoc.setText("Não");
		}
		if(s.containsDoacao()){
			contaisDoc.setText("Sim");			
		}
		else{
			contaisDoc.setText("Não");
		}
		qtdProDoados.setText(String.valueOf(s.qtdProdutosDoadosTotal()));
		precoGeral.setText(String.valueOf(s.calcPrecoGeral()));
		pesoGeral.setText(String.valueOf(s.calcPesoGeral()));
		
		codSai.setEditable(false);
		dataSai.setEditable(false);
		descSaida.setEditable(false);
		isDoc.setEditable(false);
		contaisDoc.setEditable(false);
		qtdProDoados.setEditable(false);
		precoGeral.setEditable(false);
		pesoGeral.setEditable(false);
		
		panelSai.add(new JLabel("Código"));
		panelSai.add(codSai);
		panelSai.add(new JLabel("Data"));
		panelSai.add(dataSai);
		panelSai.add(new JLabel("Descrição"));
		panelSai.add(descSaida);
		panelSai.add(new JLabel("É uma doação"));
		panelSai.add(isDoc);
		panelSai.add(new JLabel("Tem doação"));
		panelSai.add(contaisDoc);
		panelSai.add(new JLabel("Quantidade de produtos doados"));
		panelSai.add(qtdProDoados);
		panelSai.add(new JLabel("Preço geral"));
		panelSai.add(precoGeral);
		panelSai.add(new JLabel("Peso geral"));
		panelSai.add(pesoGeral);
		panelPrinc.add(panelSai,BorderLayout.NORTH);

		panelPrinc.add(scrollTableSolFor,BorderLayout.CENTER);
		panelBtn = new JPanel(new FlowLayout());
		
		JButton qtdTotalItemProduto = new JButton("Calcular a quantidade de items por produto");
		JButton qtdProdutosDoadosProduto = new JButton("Calcular a quantidade de produtos doados");
		JButton calcPesoPorProduto = new JButton("Calcular o peso por produto");
		JButton calcPrecoPorProduto = new JButton("Calcular o preço por produto");
		JButton addSol = new JButton("Adicionar Solicitacao");
		JButton remSol = new JButton("Remover Solicitacao");
		panelBtn.add(qtdTotalItemProduto);
		panelBtn.add(qtdProdutosDoadosProduto);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		panelBtn.add(addSol);
		panelBtn.add(remSol);
		
		qtdProdutosDoadosProduto.addActionListener(this);
		qtdTotalItemProduto.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		addSol.addActionListener(this);
		remSol.addActionListener(this);
		panelPrinc.add(panelBtn,BorderLayout.SOUTH);
		
		frame.setContentPane(panelPrinc);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.contains("items por")){
			try{
				int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
				Produto prod = new Produto();
				prod.setId(idProduto);
				try {
					List<Produto> list = ctrProduto.consultar(prod);
					JOptionPane.showMessageDialog(null, s.qtdTotalItemProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
				} catch (EstoqueException e) {
					e.printStackTrace();
				}
				
			}
			catch(NumberFormatException e){}
		}
		else if(cmd.contains("doados")){
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.qtdProdutosDoadosProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.contains("peso")){
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.calcPesoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.contains("Adicionar")){
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
			
			idSaidaSolSaida.setText(String.valueOf(s.getIdSaida()));
			
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
			ProdutoSolicitacaoSaida sPro = new ProdutoSolicitacaoSaida();
			sPro.setIdSaida(s.getIdSaida());
			try {
				ctrSolFor.consultar(sPro);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
			tableSolFor.revalidate();
			scrollTableSolFor.repaint();
		}
		else if(cmd.contains("Remover")){
			int linha = tableSolFor.getSelectedRow();
			ProdutoSolicitacaoSaida sPro;
			try {
				sPro = ctrSolFor.getSol().get(linha);
				ctrSolFor.removerPorSaida(sPro);
				tableSolFor.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}			
		}
		else if(cmd.equals("Add")){
			try {
				ctrSolFor.adicionarPorSaida(fromSolSaida());
				ProdutoSolicitacaoSaida sPro = new ProdutoSolicitacaoSaida();
				sPro.setIdSaida(Integer.parseInt(idSaidaSolSaida.getText()));
				ctrSolFor.consultar(sPro);
				tableSolFor.revalidate();
				scrollTableSolFor.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancel")){
			jSolSaida.setVisible(false);
		}
		else {
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, s.calcPrecoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
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
