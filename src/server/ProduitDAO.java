
package server;

import java.sql.*;
import java.util.ArrayList;
public class ProduitDAO {
    private Connection connection;
    public ProduitDAO() { 
        try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorydb", "root", ""); 
    }
    catch (SQLException e) { e.printStackTrace(); } }
    
   public boolean addProduit(Produit produit) { 
    String query = "INSERT INTO produits (nom, catégorie, quantité, prix) VALUES (?, ?, ?, ?)"; 
    try (PreparedStatement stmt = connection.prepareStatement(query)) { 
        stmt.setString(1, produit.getNom()); 
        stmt.setString(2, produit.getCategorie());
        stmt.setInt(3, produit.getQuantite()); 
        stmt.setDouble(4, produit.getPrix());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Retourne true si au moins une ligne a été ajoutée
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return false; 
    } 
}
    
  public boolean updateProduit(Produit produit) { 
    String query = "UPDATE produits SET nom = ?, catégorie = ?, quantité = ?, prix = ? WHERE id = ?"; 
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, produit.getNom()); 
        stmt.setString(2, produit.getCategorie()); 
        stmt.setInt(3, produit.getQuantite()); 
        stmt.setDouble(4, produit.getPrix()); 
        stmt.setInt(5, produit.getId());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Retourne true si au moins une ligne a été mise à jour
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return false; 
    } 
}
  
    
 public boolean deleteProduit(int id) { 
    String query = "DELETE FROM produits WHERE id = ?"; 
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, id);        
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Retourne true si au moins une ligne a été supprimée
    } catch (SQLException e) { 
        e.printStackTrace(); 
        return false; 
    } 
}
    
    public ArrayList<Produit> chercherProduits(String critere, String valeur) {
    String query = "SELECT * FROM produits WHERE " + critere + " LIKE ?";
    ArrayList<Produit> produits = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, "%" + valeur + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Produit produit = new Produit(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString(3),
                rs.getInt(4),
                rs.getDouble("prix")
            );
            produits.add(produit);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return produits;
}
   
    public ArrayList<Produit> getAllProduits() {
    String query = "SELECT * FROM produits";  // La requête pour récupérer tous les produits
    ArrayList<Produit> produits = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ResultSet rs = ps.executeQuery();  // Exécute la requête

        while (rs.next()) {  // Parcourt les résultats
            Produit produit = new Produit(
                rs.getInt(1),
                rs.getString("nom"),
                rs.getString(3),
                rs.getInt(4),
                rs.getDouble("prix")
            );
            produits.add(produit);  // Ajoute chaque produit à la liste
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Gestion des erreurs
    }
    return produits;  // Retourne la liste des produits
}

    public Produit getProduitById(int id) {
    String query = "SELECT * FROM produits WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, id); // Définir l'ID dans la requête

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { // Si un produit est trouvé
            return new Produit(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString(3),
                rs.getInt(4),
                rs.getDouble("prix")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Retourne null si aucun produit n'est trouvé ou en cas d'erreur
}

    
    public boolean login(String username, String password) {
    String query = "SELECT * FROM employés WHERE login = ? AND mot_de_passe = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        return rs.next(); // Retourne true si une ligne est trouvée (connexion réussie)
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Retourne false en cas d'erreur
    }
}
    
}
