
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    private static final int PORT = 9999;
    public static void main(String[] args) {
      try  {
          ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur démarré sur le port " + PORT);
            
            ProduitDAO produitDAO = new ProduitDAO();

            while (true) {
                System.out.println("En attente de connexion...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté : " + clientSocket.getInetAddress());

                // Gestion d'une requête client
  
       BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
  
 ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                    String request = in.readLine();
                    if (request != null) {
                        System.out.println("Requête reçue : " + request);
            String[] parts = request.split(";");
            String action = parts[0];
             String rep ="";
            switch (action) {
                case "AJOUTER":
                    Produit produit = new Produit(0, parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
                    boolean ajoutReussi = produitDAO.addProduit(produit);
                    rep = ajoutReussi ? "Produit ajouté avec succès." : "Échec de l'ajout du produit.";
                   out.println(rep);
                   break;
                case "MODIFIER":
                    Produit produitModifie = new Produit(Integer.parseInt(parts[1]), parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
                    boolean modifReussie = produitDAO.updateProduit(produitModifie);
                    rep = modifReussie ? "Produit modifié avec succès." : "Échec de la modification.";
                      out.println(rep);
                      break;
                case "SUPPRIMER":
                    int idProduit = Integer.parseInt(parts[1]);
                    boolean suppressionReussie = produitDAO.deleteProduit(idProduit);
                    rep = suppressionReussie ? "Produit supprimé avec succès." : "Échec de la suppression.";
                      out.println(rep);
                      break;
                case "CHERCHER":
                    String critere = parts[1];
                    String valeur = parts[2];
                    oos.writeObject(produitDAO.chercherProduits(critere, valeur));
                    break;
                case "BYID":
                    String id = parts[1];
                    oos.writeObject(produitDAO.getProduitById(Integer.parseInt(id)));
                     break;       
                case "ALL":
                    oos.writeObject(produitDAO.getAllProduits());
                     System.out.println("send all liste");
                    break;    
                case "LOGIN":
                    String login = parts[1];
                     String pass = parts[2];
                      boolean log = produitDAO.login(login, pass);
                        rep = log ? "ok":"no";
                       out.flush();
                       out.println(rep); // Envoyer une chaîne avec encodage UTF
out.close();
    break;
                default:
                    rep = "Action non reconnue.";
                    out.println(rep);
            }
                    }
               

                System.out.println("Client déconnecté.");
            clientSocket.close();
      
    }
      } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    
}  
