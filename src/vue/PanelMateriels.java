package vue;

import java.awt.Color;


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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.RowData;

import controleur.Contrat;
import controleur.Controleur;
import controleur.Materiel;
import controleur.Tableau;

public class PanelMateriels extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel ();
	private JComboBox<String> txtType = new JComboBox<String>();
	private JTextField txtNbrejourloues = new JTextField();
	private JTextField txtMontant = new JTextField();
	private JTextField txtNoticeentretien = new JTextField();
	
	private static JComboBox<String> txtIdcontrat = new  JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//table des classes
	private JTable tableMateriels ; 
	private int nbMateriels;
	private JLabel lbTitre = new JLabel("Nombre de materiels : ");
	
	private JScrollPane uneScroll ;
	
	//panel de filtrage
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private Tableau unTableau ;
	
	public PanelMateriels () {
	super ();
	//Constructeur du formulaire Classe.
	this.panelForm.setLayout(new GridLayout(6,2));
	this.panelForm.setBackground(new Color (192, 57, 43));
	this.panelForm.setBounds(10, 10, 300, 300);
	this.panelForm.add(new JLabel("Type :"));
	this.panelForm.add(this.txtType);
	this.panelForm.add(new JLabel("Nombre de jours loués :"));
	this.panelForm.add(this.txtNbrejourloues);
	this.panelForm.add(new JLabel("Montant  :"));
	this.panelForm.add(this.txtMontant);
	this.panelForm.add(new JLabel("Notice de l'entretien :"));
	this.panelForm.add(this.txtNoticeentretien);
	this.panelForm.add(new JLabel("Idcontrat :"));
	this.panelForm.add(this.txtIdcontrat);
	this.panelForm.add(this.btAnnuler);
	this.panelForm.add(this.btEnregistrer);
	this.panelForm.setVisible(true);
	this.add(this.panelForm);
	
	
	//construction de la table des classes
	String entetes [] = {"ID", "Type ", " Nbre de jours loués "," Montant "," Notice entretien "," Contrat"};
	this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
	
	this.tableMateriels = new JTable(this.unTableau);
	this.tableMateriels.getTableHeader().setReorderingAllowed(false);
	this.uneScroll = new JScrollPane(this.tableMateriels);
	this.uneScroll.setBounds(350, 80, 460, 230);
	this.add(this.uneScroll);
	
	//construction du panel filtre
	this.panelFiltre.setBounds(400, 30, 400, 30);
	this.panelFiltre.setBackground(new Color (192, 57, 43));
	this.panelFiltre.setLayout(new GridLayout(1, 3));
	this.panelFiltre.add(new JLabel("Filtrer les materiels par :"));
	this.panelFiltre.add(this.txtFiltre);
	this.panelFiltre.add(btFiltrer);
	this.add(this.panelFiltre);
	
	
	//rendre les boutons ecoutables
    this.btAnnuler.addActionListener(this);
    this.btEnregistrer.addActionListener(this);
    this.btFiltrer.addActionListener(this);
    
    //placement du titre 
    this.lbTitre.setBounds(200 , 330, 300, 20);
    this.nbMateriels = this.unTableau.getRowCount();
    this.lbTitre.setText("Nombre de materiels : " + this.nbMateriels);
    this.add(this.lbTitre);
    
    //remplir le statut
    this.txtType.addItem("peinture");
    this.txtType.addItem("ponçage");
    this.txtType.addItem("perforation");
    this.txtType.addItem("sciage");
    this.txtType.addItem("nettoyage");
    this.txtType.addItem("soudure");
    this.txtType.addItem("terrassement");
    this.txtType.addItem("plomberie");
    
    
    //remplir les contrats dans le comboBox
    this.remplirCBXContrats ();
    
    //suppresion d'un etudiant
    this.tableMateriels.addMouseListener(new MouseListener() {
		
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
		
		public void mouseClicked(MouseEvent e) {
        int numLigne ;
        int idMateriel;
        if (e.getClickCount()>= 2) {
        	numLigne = tableMateriels.getSelectedRow();
        	idMateriel = Integer.parseInt(tableMateriels.getValueAt(numLigne, 0).toString());
        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression du materiel", JOptionPane.YES_NO_OPTION);
        	if (reponse == 0) {
        		// confirmation de la suppresion dans la BDD
        		Controleur.deleteMateriel(idMateriel);
        		//confirmation de la suppression dans l'affichage des classes
        		unTableau.supprimerLigne(numLigne);
        		nbMateriels = unTableau.getRowCount();
        		lbTitre.setText("Nombre de materiels : " + nbMateriels);
        	}
        }
        else if (e.getClickCount()==1) {
        	numLigne = tableMateriels.getSelectedRow();
        	//txtStatut.setText(tableMateriels.getValueAt(numLigne,1).toString());
        	txtNbrejourloues.setText(tableMateriels.getValueAt(numLigne,2).toString());
        	txtMontant.setText(tableMateriels.getValueAt(numLigne,3).toString());
        	txtNoticeentretien.setText(tableMateriels.getValueAt(numLigne,4).toString());
        	
        	//txtIdcontrat.setText(tableMateriels.getValueAt(numLigne,5).toString());
        	btEnregistrer.setText("Modifier");
        }
			
		}
	});

  }
	public static void remplirCBXContrats() {
		txtIdcontrat.removeAllItems();
		ArrayList<Contrat> lesContrats = Controleur.selectAllContrats("");
		for (Contrat unContrat : lesContrats) {
			txtIdcontrat.addItem(unContrat.getIdcontrat()+ "-"
					+ unContrat.getStatut());
		}
		
	}
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
		ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels(filtre);
		Object [][] matrice = new Object [lesMateriels.size()][6];
		int i =0;
		for (Materiel unMateriel: lesMateriels) {
			matrice [i][0] = unMateriel.getIdmateriel();
			matrice [i][1] = unMateriel.getType();
			matrice [i][2] = unMateriel.getNbrejourloues();
			matrice [i][3] = unMateriel.getMontant();
			matrice [i][4] = unMateriel.getNoticeentretien();
			matrice [i][5] = unMateriel.getIdcontrat();
			
			i++;
		}
		return matrice;
	}
@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == this.btAnnuler) {
		//this.txtType.setText("");
		this.txtNbrejourloues.setText("");
		this.txtMontant.setText("");
		this.txtNoticeentretien.setText("");
		//this.txtIdcontrat.setText("");
		this.btEnregistrer.setText("Enregistrer");
		}
	else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
		String type = this.txtType.getSelectedItem().toString();
		String nbrejourloues = this.txtNbrejourloues.getText();
		String montant = this.txtMontant.getText();
		String noticeentretien = this.txtNoticeentretien.getText();
		
		String chaine = this.txtIdcontrat.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idcontrat = Integer.parseInt(tab[0]);
		
		//instanciation d'une classe
		
		Materiel unMateriel = new Materiel( type, nbrejourloues, montant, noticeentretien , idcontrat);
		
		
		//insertion dans la base de données
		Controleur.insertMateriel(unMateriel);
		JOptionPane.showMessageDialog(this, "Materiel ajouter avec succès");
		
		//recuperation de l'id de l'etudiant  de la BDD
		unMateriel = Controleur.selectWhereMateriel (type, nbrejourloues ,montant);
		 
		
		//mettre à jour l'affichage
		Object ligne[]= {unMateriel.getIdmateriel(), type, nbrejourloues, montant, noticeentretien, idcontrat};
		this.unTableau.ajouterLigne(ligne);
		this.nbMateriels = unTableau.getRowCount();
		
		this.lbTitre.setText("Nombre de materiels : " + this.nbMateriels);
		//this.txtType.setText("");
		this.txtNbrejourloues.setText("");
		this.txtMontant.setText("");
		this.txtNoticeentretien.setText("");
		//this.txtIdcontrat.setText("");
	}
	else if (e.getSource()== this.btFiltrer) {
		String filtre = this.txtFiltre.getText();
		Object matrice[][] = this.remplirDonnees(filtre);
		this.unTableau.setDonnees(matrice);
	}
	else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
		String type = this.txtType.getSelectedItem().toString();
		String nbrejourloues = this.txtNbrejourloues.getText();
		String montant = this.txtMontant.getText();
		String noticeentretien = this.txtNoticeentretien.getText();
		String chaine = this.txtIdcontrat.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idcontrat = Integer.parseInt(tab[0]);
		
		int numLigne = this.tableMateriels.getSelectedRow();
		int idLigne = Integer.parseInt(this.tableMateriels.getValueAt(numLigne, 0).toString());
		//instaciation de la classe 
		Materiel unMateriel = new Materiel(idLigne, type, nbrejourloues, montant, noticeentretien, idcontrat);
		//modification de la base de données :
		Controleur.updateMateriel(unMateriel);
		//modification de la table d'affichage
		Object ligne[] = {idLigne, type, nbrejourloues, montant, noticeentretien, idcontrat};
		this.unTableau.modifierLigne(numLigne, ligne);
		//on vide le formulaire
		//this.txtContrat.setText("");
		this.txtNbrejourloues.setText("");
		this.txtMontant.setText("");
		this.txtNoticeentretien.setText("");
		//this.txtIdcontrat.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
 }
}






