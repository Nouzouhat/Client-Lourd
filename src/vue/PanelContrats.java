package vue;
 
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 

 
import controleur.Controleur;
import controleur.Client;
import controleur.Contrat;
import controleur.Tableau;
 
public class PanelContrats extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel();
	private JTextField txtDebutcontrat = new JTextField();
	private JTextField txtFincontrat = new JTextField();
	private JTextField txtMontant = new JTextField();
	private JComboBox<String> txtStatut = new JComboBox<String>();
	
	private static JComboBox<String> txtIdclient = new JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableContrats ; //table des contrats
	private JScrollPane uneScroll ;
	
	//panel de filtrage
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private Tableau unTableau ;
	
	private int nbContrats ;
	private JLabel lbTitre = new JLabel("Nombre de contrats : ");
	
	public PanelContrats()
	{
		super();
		//construction du formulaire Contrat
		this.panelForm.setLayout(new GridLayout(7,2));
		this.panelForm.setBackground(new Color (192, 57, 43));
		this.panelForm.setBounds(10, 60, 250, 300);
		this.panelForm.add(new JLabel("Debut Contrat :"));
		this.panelForm.add(this.txtDebutcontrat);
		this.panelForm.add(new JLabel("Fin Contrat :"));
		this.panelForm.add(this.txtFincontrat);
		this.panelForm.add(new JLabel("Montant"));
		this.panelForm.add(this.txtMontant);
		this.panelForm.add(new JLabel("Statut"));
		this.panelForm.add(this.txtStatut);
		this.panelForm.add(new JLabel("Client"));
		this.panelForm.add(this.txtIdclient);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des étudiants
		String entetes [] = {"ID Contrat", "Debutcontrat ", "Fincontrat ", "Montant", "Statut", "Client"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees(""));
		
		this.tableContrats = new JTable(this.unTableau);
		this.tableContrats.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableContrats);
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre
		this.panelFiltre.setBounds(400, 30, 400, 30);
		this.panelFiltre.setBackground(new Color (192, 57, 43));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les contrats par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		
		//placement des titres
		this.lbTitre.setBounds(200, 330, 300, 20);
		this.nbContrats = this.unTableau.getRowCount();
		this.lbTitre.setText("Nombre de contrats : " + this.nbContrats);
		this.add(this.lbTitre);
		
		//remplir le statut
		this.txtStatut.addItem("en cours");
		this.txtStatut.addItem("termine");
		
		//remplir les clients dans le comboBox
		this.remplirCBXClients ();
		
		//suppression d'un contrat
		this.tableContrats.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne ;
				int idContrat ;
				if (e.getClickCount() >= 2) {
					numLigne = tableContrats.getSelectedRow();
					idContrat = Integer.parseInt(tableContrats.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null,  "Voulez vous confirmer la suppression ?", "Suppression du contrat",
							JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						//confirmation de la suppression dans la bdd
						Controleur.deleteContrat(idContrat);
						//confirmation de la suppression dans l'affichage des contrats
						unTableau.supprimerLigne(numLigne);
						nbContrats = unTableau.getRowCount();
						lbTitre.setText("Nombre de contrats : " + nbContrats);
					}
				}
				else if (e.getClickCount() == 1) {
					numLigne = tableContrats.getSelectedRow();
					txtDebutcontrat.setText(tableContrats.getValueAt(numLigne,1).toString());
					txtFincontrat.setText(tableContrats.getValueAt(numLigne,2).toString());
					txtMontant.setText(tableContrats.getValueAt(numLigne,3).toString());
					//txtStatut.setText(tableContrats.getValueAt(numLigne,4).toString());
					//txtIdClient.setText(tableContrats.getValueAt(numLigne,5).toString());
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public static void remplirCBXClients() {
		txtIdclient.removeAllItems();
		ArrayList<Client> lesClients = Controleur.selectAllClients("");
		for (Client unClient : lesClients) {
			txtIdclient.addItem(unClient.getIdclient()+ "-"
					+ unClient.getNom());
		}
		
	}
		public Object [][] remplirDonnees (String filtre){
			//permet de convertir l'arraylist en une matrice de données
			ArrayList<Contrat> lesContrats = Controleur.selectAllContrats(filtre);
			Object [][] matrice = new Object [lesContrats.size()][6];
			int i =0;
			for (Contrat unContrat : lesContrats) {
				matrice [i][0] = unContrat.getIdcontrat();
				matrice [i][1] = unContrat.getDebutcontrat();
				matrice [i][2] = unContrat.getFincontrat();
				matrice [i][3] = unContrat.getMontant();
				matrice [i][4] = unContrat.getStatut();
				matrice [i][5] = unContrat.getIdclient();
				i++;
			}
			return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtDebutcontrat.setText("");
			this.txtFincontrat.setText("");
			this.txtMontant.setText("");
			//this.txtStatut.setText("");
			//this.txtIdClient.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String debutcontrat = this.txtDebutcontrat.getText();
			String fincontrat = this.txtFincontrat.getText();
			String montant = this.txtMontant.getText();
			String statut = this.txtStatut.getSelectedItem().toString();
			String chaine = this.txtIdclient.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			//instanciation d'un contrat
			Contrat unContrat = new Contrat (debutcontrat, fincontrat, montant, statut, idclient);
			
			//insertion dans la base de données
			Controleur.insertContrat(unContrat);
			JOptionPane.showMessageDialog(this, "Contrat ajoutée avec succès");
			
			//recupération de l'id de la contrat de la BDD
			unContrat = Controleur.selectWhereContrat ( debutcontrat,  fincontrat, montant);
			
			//mettre à jour l'affichage
			Object ligne[]= {unContrat.getIdcontrat(), debutcontrat, fincontrat, montant, statut, idclient};
			this.unTableau.ajouterLigne(ligne);
			
			this.nbContrats = unTableau.getRowCount();
			this.lbTitre.setText("Nombre de contrats : " + this.nbContrats);
			
			this.txtDebutcontrat.setText("");
			this.txtFincontrat.setText("");
			this.txtMontant.setText("");
			//this.txtStatut.setText("");
			//this.txtIdclient.setText("");
		}
		else if (e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			Object matrice [][] = this.remplirDonnees(filtre);
			this.unTableau.setDonnees(matrice);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String debutcontrat = this.txtDebutcontrat.getText();
			String fincontrat = this.txtFincontrat.getText();
			String montant = this.txtMontant.getText();
			String statut = this.txtStatut.getSelectedItem().toString();
			String chaine = this.txtIdclient.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			int numLigne = this.tableContrats.getSelectedRow();
			int idLigne = Integer.parseInt(this.tableContrats.getValueAt(numLigne, 0).toString());
			//instanciation du contrat
			Contrat unContrat = new Contrat(idLigne, debutcontrat, fincontrat, montant, statut, idclient);
			//modification dans la base de donnée :
			Controleur.updateContrat(unContrat);
			//modification dans le tableau d'affichage
			Object ligne[] = {idLigne, debutcontrat, fincontrat, montant, statut, idclient};
			this.unTableau.modifierLigne(numLigne, ligne);
			//on vide le formulaire
			this.txtDebutcontrat.setText("");
			this.txtFincontrat.setText("");
			this.txtMontant.setText("");
			//this.txtStatut.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
	}
}
 