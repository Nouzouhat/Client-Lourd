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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener {
	private JPanel panelForm = new JPanel ();
	private JTextField txtNom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTelephone = new JTextField();
	
	private JComboBox<String> txtActeur = new JComboBox<String>();
	
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtVille = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//table des clients
		private JTable tableClients ; 
		private int nbClients;
		private JLabel lbTitre = new JLabel("Nombre de clients : ");
		
		private JScrollPane uneScroll ;
		
		//panel de filtrage
		
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer = new JButton("Filtrer");
		
		private Tableau unTableau ;
	
	public PanelClients () {
		super ();
		//Constructeur du formulaire Classe.
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.setBackground(new Color (192, 57, 43));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Email :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Telephone  :"));
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(new JLabel("Acteur :"));
		this.panelForm.add(this.txtActeur);
		this.panelForm.add(new JLabel("Adresse :"));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Cp :"));
		this.panelForm.add(this.txtCp);
		this.panelForm.add(new JLabel("Ville :"));
		this.panelForm.add(this.txtVille);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table de clients
		String entetes [] = {"ID", "Nom ", "Email "," Telephone "," Acteur "," Adresse" ," Cp", "Ville"};
		this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
		
		this.tableClients = new JTable(this.unTableau);
		this.tableClients.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableClients);
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		
		//construction du panel filtre
		this.panelFiltre.setBounds(400, 30, 400, 30);
		this.panelFiltre.setBackground(new Color (192, 57, 43));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les clients par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(btFiltrer);
		this.add(this.panelFiltre);
		
		//rendre les boutons ecoutables
	    this.btAnnuler.addActionListener(this);
	    this.btEnregistrer.addActionListener(this);
	    this.btFiltrer.addActionListener(this);
	    
	  //placement du titre 
	    this.lbTitre.setBounds(200 , 330, 300, 20);
	    this.nbClients = this.unTableau.getRowCount();
	    this.lbTitre.setText("Nombre de clients : " + this.nbClients);
	    this.add(this.lbTitre);
	    
	  //remplir l'acteur 
	    this.txtActeur.addItem("entreprise");
	    this.txtActeur.addItem("particulier");
	    
	    this.tableClients.addMouseListener(new MouseListener() {

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
	        int idClient;
	        if (e.getClickCount()>= 2) {
	        	numLigne = tableClients.getSelectedRow();
	        	idClient = Integer.parseInt(tableClients.getValueAt(numLigne, 0).toString());
	        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression de l'etudiant", JOptionPane.YES_NO_OPTION);
	        	if (reponse == 0) {
	        		// confirmation de la suppresion dans la BDD
	        		Controleur.deleteClient(idClient);
	        		//confirmation de la suppression dans l'affichage des clients
	        		unTableau.supprimerLigne(numLigne);
	        		nbClients = unTableau.getRowCount();
	        		lbTitre.setText("Nombre de clients : " + nbClients);
	        	}
	        }
	        else if (e.getClickCount()==1) {
	        	numLigne = tableClients.getSelectedRow();
	        	txtNom.setText(tableClients.getValueAt(numLigne,1).toString());
	        	txtEmail.setText(tableClients.getValueAt(numLigne,2).toString());
	        	txtTelephone.setText(tableClients.getValueAt(numLigne,3).toString());
	        	//txtActeur.setText(tableClients.getValueAt(numLigne,4).toString());
	        	txtAdresse.setText(tableClients.getValueAt(numLigne,5).toString());
	        	txtCp.setText(tableClients.getValueAt(numLigne,6).toString());
	        	txtVille.setText(tableClients.getValueAt(numLigne,7).toString());
	        	btEnregistrer.setText("Modifier");
	        }
				
			}
		});

	  }
	
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
		ArrayList<Client> lesClients = Controleur.selectAllClients(filtre);
		Object [][] matrice = new Object [lesClients.size()][8];
		int i =0;
		for (Client unClient: lesClients) {
			matrice [i][0] = unClient.getIdclient();
			matrice [i][1] = unClient.getNom();
			matrice [i][2] = unClient.getEmail();
			matrice [i][3] = unClient.getTelephone();
			matrice [i][4] = unClient.getActeur();
			matrice [i][5] = unClient.getAdresse();
			matrice [i][6] = unClient.getCp();
			matrice [i][7] = unClient.getVille();
			
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			//this.txtActeur.setText("");
			this.txtAdresse.setText("");
			this.txtCp.setText("");
			this.txtVille.setText("");
			this.btEnregistrer.setText("Enregistrer");
			}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String email = this.txtEmail.getText();
			String telephone = this.txtTelephone.getText();
			String acteur = this.txtActeur.getSelectedItem().toString();
			String adresse = this.txtAdresse.getText();
			String cp = this.txtCp.getText();
			String ville = this.txtVille.getText();
			
			//instanciation d'un client
			
			Client unClient = new Client( nom, email, telephone, acteur , adresse, cp, ville);

			//insertion dans la base de données
			Controleur.insertClient(unClient);
			JOptionPane.showMessageDialog(this, "Client ajouté avec succès");
			
			//recuperation de l'id du client  de la BDD
			unClient = Controleur.selectWhereClient (nom ,email);
			
			//mettre à jour l'affichage
			Object ligne[]= {unClient.getIdclient(), nom, email, telephone, acteur, adresse, cp, ville};
			this.unTableau.ajouterLigne(ligne);
			this.nbClients = unTableau.getRowCount();
			
			this.lbTitre.setText("Nombre de clients : " + this.nbClients);
			this.txtNom.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			//this.txtActeur.setText("");
			this.txtAdresse.setText("");
			this.txtCp.setText("");
			this.txtVille.setText("");
		}
		
		else if (e.getSource()== this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			Object matrice[][] = this.remplirDonnees(filtre);
			this.unTableau.setDonnees(matrice);
		}
		
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String email = this.txtEmail.getText();
			String telephone = this.txtTelephone.getText();
			String acteur = this.txtActeur.getSelectedItem().toString();
			String adresse = this.txtAdresse.getText();
			String cp = this.txtCp.getText();
			String ville = this.txtVille.getText();
			
			int numLigne = this.tableClients.getSelectedRow();
			int idLigne = Integer.parseInt(this.tableClients.getValueAt(numLigne, 0).toString());
			
			//instaciation du client 
			Client unClient = new Client(idLigne, nom, email, telephone, acteur, adresse, cp, ville);
			
			//modification de la base de données :
			Controleur.updateClient(unClient);
			
			//modification de la table d'affichage
			Object ligne[] = {idLigne, nom, email, telephone, acteur, adresse, cp, ville};
			this.unTableau.modifierLigne(numLigne, ligne);
			
			//on vide le formulaire
			this.txtNom.setText("");
			this.txtEmail.setText("");
			this.txtTelephone.setText("");
			//this.txtActeur.setText("");
			this.txtAdresse.setText("");
			this.txtCp.setText("");
			this.txtVille.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
	 }
	}

