package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 

import controleur.Controleur;
import controleur.Tableau;
import controleur.Technicien;
 
public class PanelTechniciens extends PanelPrincipal implements ActionListener{
 
 
		private JPanel panelForm = new JPanel ();
		private JTextField txtNom = new JTextField();
		private JTextField txtPrenom = new JTextField();
		private JTextField txtAdresse = new JTextField();
		private JTextField txtCP = new JTextField();
		private JTextField txtVille = new JTextField();
		private JTextField txtTelephone = new JTextField();
		private JTextField txtEmail = new JTextField();
		private JButton btAnnuler = new JButton("Annuler");
		private JButton btEnregistrer = new JButton("Enregistrer");
		//table des classes
		private JTable tableTechniciens ; 
		private int nbTechniciens;
		private JLabel lbTitre = new JLabel("Nombre de techniciens : ");
		private JScrollPane uneScroll ;
		//panel de filtrage
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer = new JButton("Filtrer");
		private Tableau unTableau ;
		public PanelTechniciens() {
		super ();
		//Constructeur du formulaire Classe.
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.setBackground(new Color (192, 57, 43));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom technicien :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prenom  :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse :"));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Telephone :"));
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(new JLabel("CP :"));
		this.panelForm.add(this.txtCP);
		this.panelForm.add(new JLabel("Ville :"));
		this.panelForm.add(this.txtVille);
		this.panelForm.add(new JLabel("Email :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);

		//construction de la table des classes
		String entetes [] = {"ID Technicien", "Nom technicien", "Prenom technicien","Adresse","CP","Telephone", "Ville","Email"};
		this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
		this.tableTechniciens = new JTable(this.unTableau);
		this.tableTechniciens.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableTechniciens);
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		//construction du panel filtre
		this.panelFiltre.setBounds(400, 30, 400, 30);
		this.panelFiltre.setBackground(new Color (192, 57, 43));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les techniciens par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(btFiltrer);
		this.add(this.panelFiltre);

		//rendre les boutons ecoutables
	    this.btAnnuler.addActionListener(this);
	    this.btEnregistrer.addActionListener(this);
	    this.btFiltrer.addActionListener(this);
	    //placement du titre 
	    this.lbTitre.setBounds(200 , 330, 300, 20);
	    this.nbTechniciens = this.unTableau.getRowCount();
	    this.lbTitre.setText("Nombre de la techniciens : " + this.nbTechniciens);
	    this.add(this.lbTitre);
	    //suppresion d'une classe
	    this.tableTechniciens.addMouseListener(new MouseListener() {
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
	        int idTechnicien;
	        if (e.getClickCount()>= 2) {
	        	numLigne = tableTechniciens.getSelectedRow();
	        	idTechnicien = Integer.parseInt(tableTechniciens.getValueAt(numLigne, 0).toString());
	        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression de la technicien", JOptionPane.YES_NO_OPTION);
	        	if (reponse == 0) {
	        		// confirmation de la suppresion dans la BDD
	        		Controleur.deleteTechnicien(idTechnicien);
	        		//confirmation de la suppression dans l'affichage des classes
	        		unTableau.supprimerLigne(numLigne);
	        		nbTechniciens = unTableau.getRowCount();
	        		lbTitre.setText("Nombre de la techniciens : " + nbTechniciens);
	        	}
	        }
	        else if (e.getClickCount()==1) {
	        	numLigne = tableTechniciens.getSelectedRow();
	        	txtNom.setText(tableTechniciens.getValueAt(numLigne,1).toString());
	        	txtPrenom.setText(tableTechniciens.getValueAt(numLigne,2).toString());
	        	txtAdresse.setText(tableTechniciens.getValueAt(numLigne,3).toString());
	        	txtCP.setText(tableTechniciens.getValueAt(numLigne,4).toString());
	        	txtTelephone.setText(tableTechniciens.getValueAt(numLigne,5).toString());
	        	txtVille.setText(tableTechniciens.getValueAt(numLigne,6).toString());
	        	txtEmail.setText(tableTechniciens.getValueAt(numLigne,7).toString());
	        	btEnregistrer.setText("Modifier");
	        }
			}
		});
 
	  }
		public Object [][] remplirDonnees (String filtre){
			//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
			ArrayList<Technicien> lesTechniciens = Controleur.selectAllTechniciens(filtre);
			Object [][] matrice = new Object [lesTechniciens.size()][8];
			int i =0;
			for (Technicien unTechnicien: lesTechniciens) {
				matrice [i][0] = unTechnicien.getIdtechnicien();
				matrice [i][1] = unTechnicien.getNom();
				matrice [i][2] = unTechnicien.getPrenom();
				matrice [i][3] = unTechnicien.getAdresse();
				matrice [i][4] = unTechnicien.getCp();
				matrice [i][5] = unTechnicien.getTelephone();
				matrice [i][6] = unTechnicien.getVille();
				matrice [i][7] = unTechnicien.getEmail();
				i++;
			}
			return matrice;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtCP.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			this.txtVille.setText("");
			this.btEnregistrer.setText("Enregistrer");
			}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String cp = this.txtCP.getText();
			String telephone = this.txtTelephone.getText();
			String ville = this.txtVille.getText();
			String email = this.txtEmail.getText();
			//instanciation d'une classe
			Technicien unTechnicien = new Technicien (nom, prenom, adresse, cp, telephone, ville, email );

			//insertion dans la base de données
			Controleur.insertTechnicien(unTechnicien);
			JOptionPane.showMessageDialog(this, "technicien ajouter avec succès");
			//recuperetion de l'id de la classe de la BDD
			unTechnicien = Controleur.selectWhereTechnicien(nom, prenom);

			//mettre à jour l'affichage
			Object ligne[]= {unTechnicien.getIdtechnicien(), nom, prenom, adresse, cp, telephone, ville, email};
			this.unTableau.ajouterLigne(ligne);
			this.nbTechniciens = unTableau.getRowCount();
			this.lbTitre.setText("Nombre de technicien : " + this.nbTechniciens);
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtCP.setText("");
			this.txtTelephone.setText("");
			this.txtVille.setText("");
			this.txtEmail.setText("");

		}
		else if (e.getSource()== this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			Object matrice[][] = this.remplirDonnees(filtre);
			this.unTableau.setDonnees(matrice);
		}
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String cp = this.txtCP.getText();
			String telephone = this.txtTelephone.getText();
			String ville = this.txtVille.getText();
			String email = this.txtEmail.getText();
			int numLigne = this.tableTechniciens.getSelectedRow();
			int idLigne = Integer.parseInt(this.tableTechniciens.getValueAt(numLigne, 0).toString());
			//instaciation de la classe 
			Technicien unTechnicien = new Technicien(idLigne, nom, prenom, adresse, cp, telephone, ville, email);
			//modification de la base de données :
			Controleur.updateTechnicien(unTechnicien);
			//modification de la table d'affichage
			Object ligne[] = {idLigne, nom, prenom, adresse, cp, telephone, ville, email};
			this.unTableau.modifierLigne(numLigne, ligne);
			//on vide le formulaire
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtCP.setText("");
			this.txtTelephone.setText("");
			this.txtVille.setText("");
			this.txtEmail.setText("");
			this.btEnregistrer.setText("Enregistrer");

		}
	 }
	}
 


