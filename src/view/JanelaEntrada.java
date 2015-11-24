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
import utilities.EstoqueException;
import model.Entrada;
import model.Produto;
import model.ProdutoSolicitacaoEntrada;

public class JanelaEntrada implements ActionListener{
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
	private ControllerProduto ctrProduto = new ControllerProduto();
	private JFrame janela = new JFrame("Entrada");
	private JTextField codEnt = new JTextField(10);
	private JFormattedTextField dataEnt;
	private JTextField tipoEnt = new JTextField(30);
	private JTextField NFEEnt = new JTextField(30);
	private JFormattedTextField dataNFEEnt;
	private JTextField tempoEnt = new JTextField(10);
	private JTextField codForEnt = new JTextField(10);
	private JTextField contaisDoc = new JTextField(30);
	private JTextField isDoc = new JTextField(30);
	private JTextField qtdProDoados = new JTextField(30);
	private ControllerProdutoEntradaSolicitacao ctrSolFor = new ControllerProdutoEntradaSolicitacao();
	private JTable tableSolFor = new JTable(ctrSolFor);
	private JScrollPane scrollTable = new JScrollPane();
	private JPanel panelEnt;
	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private Entrada e;
	
	public JanelaEntrada(Entrada e) throws ParseException, EstoqueException{
		this.e = e;
		scrollTable.getViewport().add(tableSolFor);
		janela.setSize(1000, 600);
		panelEnt = new JPanel(new GridLayout(10,2));
		panelBtn = new JPanel(new FlowLayout());
		panelPrincipal = new JPanel(new BorderLayout());
		
		MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		dataEnt = new JFormattedTextField(mascaraData);
		dataNFEEnt = new JFormattedTextField(mascaraData);
	
		codEnt.setText(String.valueOf(e.getIdEntrada()));
		dataEnt.setText(in.format(e.getData()));
		tipoEnt.setText(e.getTipoTransf());
		NFEEnt.setText(e.getNFE());
		dataNFEEnt.setText(in.format(e.getData()));
		tempoEnt.setText(String.valueOf(e.getTempo()));
		codForEnt.setText(String.valueOf(e.getIdFornecedor()));
		if(e.containsDoacao()){
			contaisDoc.setText("Sim");
		}
		else{
			contaisDoc.setText("Não");
		}
		if(e.isDoacao()){
			isDoc.setText("Sim");
		}
		else{
			isDoc.setText("Não");
		}
		qtdProDoados.setText(String.valueOf(e.qtdProdutosDoadosTotal()));
		
		codEnt.setEditable(false);
		dataEnt.setEditable(false);
		tipoEnt.setEditable(false);
		NFEEnt.setEditable(false);
		dataNFEEnt.setEditable(false);
		tempoEnt.setEditable(false);
		codForEnt.setEditable(false);
		isDoc.setEditable(false);
		qtdProDoados.setEditable(false);
		contaisDoc.setEditable(false);
		
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
		panelEnt.add(new JLabel("É uma doação"));
		panelEnt.add(isDoc);
		panelEnt.add(new JLabel("Tem doação"));
		panelEnt.add(contaisDoc);
		panelEnt.add(new JLabel("Quantidade de produtos doados"));
		panelEnt.add(qtdProDoados);
		panelPrincipal.add(panelEnt,BorderLayout.NORTH);

		JButton qtdTotalItemProduto = new JButton("Calcular a quantidade de items por produto");
		JButton qtdProdutosDoadosProduto = new JButton("Calcular a quantidade de produtos doados");
		JButton calcPesoPorProduto = new JButton("Calcular o peso por produto");
		JButton calcPrecoPorProduto = new JButton("Calcular o preço por produto");
		JButton adicionarSaida = new JButton("Adicionar saida");
		JButton removerSaida = new JButton("Remover saida");
		
		panelBtn.add(qtdTotalItemProduto);
		panelBtn.add(qtdProdutosDoadosProduto);
		panelBtn.add(calcPesoPorProduto);
		panelBtn.add(calcPrecoPorProduto);
		panelBtn.add(adicionarSaida);
		panelBtn.add(removerSaida);
		
		ProdutoSolicitacaoEntrada pEnt = new ProdutoSolicitacaoEntrada();
		pEnt.setIdEntrada(e.getIdEntrada());
		ctrSolFor.consultar(pEnt);
		panelPrincipal.add(scrollTable,BorderLayout.CENTER);
		scrollTable.repaint();
		tableSolFor.revalidate();
		
		qtdProdutosDoadosProduto.addActionListener(this);
		qtdTotalItemProduto.addActionListener(this);
		calcPesoPorProduto.addActionListener(this);
		calcPrecoPorProduto.addActionListener(this);
		adicionarSaida.addActionListener(this);
		removerSaida.addActionListener(this);
		
		panelPrincipal.add(panelBtn,BorderLayout.SOUTH);
		janela.setContentPane(panelPrincipal);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
					JOptionPane.showMessageDialog(null, e.qtdTotalItemProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null, e.qtdProdutosDoadosProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null, e.calcPesoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
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
			
			idEntEntrada.setText(String.valueOf(e.getIdEntrada()));
			
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
				tableSolFor.revalidate();
				scrollTable.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}			
		}
		else if(cmd.equals("Add")){
			try {
				ctrSolFor.adicionar(fromSolEntrada());
				ProdutoSolicitacaoEntrada sPro = new ProdutoSolicitacaoEntrada();
				sPro.setIdEntrada(Integer.parseInt(idEntEntrada.getText()));
				ctrSolFor.consultar(sPro);
				tableSolFor.revalidate();
				scrollTable.repaint();
			} catch (EstoqueException e) {
				JOptionPane.showMessageDialog(null, "Erro!","Erro!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(cmd.equals("Cancel")){
			jSolEntrada.setVisible(false);
		}
		else{
			int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
			Produto prod = new Produto();
			prod.setId(idProduto);
			try {
				List<Produto> list = ctrProduto.consultar(prod);
				JOptionPane.showMessageDialog(null, e.calcPrecoPorProduto(list.get(0)), "Preco", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstoqueException e) {
				e.printStackTrace();
			}
		}
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