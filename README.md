# Gestion d'Inventaire - Projet Serveur/Client

## Introduction
Ce projet est une application de gestion d'inventaire basée sur une architecture client-serveur. Le serveur gère les données des produits via une base de données MySQL et expose des services via RMI (Remote Method Invocation). Le client permet aux utilisateurs d'interagir avec le système pour effectuer des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) sur les produits.

## Fonctionnalités principales
- **Ajout de produits** : Ajouter de nouveaux produits à l'inventaire.
- **Consultation des produits** : Afficher la liste des produits disponibles.
- **Mise à jour des produits** : Modifier les informations des produits existants.
- **Suppression des produits** : Supprimer des produits de l'inventaire.
- **Architecture client-serveur** : Communication via RMI pour un accès distant.

## Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :
- **Java JDK** (version 8 ou supérieure)
- **MySQL** (version 5.7 ou supérieure)
- **Maven** (pour la gestion des dépendances, si nécessaire)
- Un IDE comme IntelliJ IDEA, Eclipse ou NetBeans

## Installation
1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/votre-repo/inventory-management.git
   cd inventory-management
   ```

2. **Configurer la base de données** :
   - Importez le fichier `database/schema.sql` pour créer la base de données et les tables.
   - Importez le fichier `database/data.sql` pour insérer des données de test.

3. **Configurer le serveur** :
   - Ouvrez le fichier `server/resources/application.properties` et configurez les informations de connexion à la base de données :
     ```
     db.url=jdbc:mysql://localhost:3306/inventory_management
     db.username=root
     db.password=
     ```

4. **Compiler le projet** :
   - Compilez le serveur et le client en utilisant votre IDE 

## Exécution
### 1. Démarrer le serveur
- Accédez au dossier `server/` et exécutez la classe principale `Server.java` :
  ```bash
  java -cp build/server.jar server.Server
  ```
- Le serveur démarre et écoute sur le port `9999` pour les connexions des clients.

### 2. Démarrer le client
- Accédez au dossier `client/` et exécutez la classe principale `Client.java` :
  ```bash
  java -cp build/client.jar client.Client
  ```
- Suivez les instructions affichées dans la console pour interagir avec le système.

## Exemple d'utilisation
1. **Afficher tous les produits** :
   - Lancez le client et choisissez l'option "1" pour afficher la liste des produits.
2. **Ajouter un produit** :
   - Choisissez l'option "2" et entrez les informations demandées (nom, catégorie, quantité, prix).
3. **Mettre à jour un produit** :
   - Implémentez l'option correspondante dans le client pour modifier un produit existant.
4. **Supprimer un produit** :
   - Implémentez l'option correspondante dans le client pour supprimer un produit.

## Structure du projet
```
InventoryManagementSystem/
├── server/
│   ├── src/
│   │   ├── dao/
│   │   ├── model/
│   │   ├── service/
│   │   └── server/
│   └── resources/
├── client/
│   ├── src/
│   │   ├── ui/
│   │   ├── service/
│   │   └── model/
│   └── resources/
├── database/
│   ├── schema.sql
│   └── data.sql
└── docs/
```

## Technologies utilisées
- **Java** : Langage principal pour le développement.
- **MySQL** : Base de données relationnelle pour stocker les données.
- **Socket** : Pour la communication entre le client et le serveur.

## Auteurs
- ELMOUKHTAR Noureddine - Développeur principal


## Licence
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, de le modifier et de le distribuer.

---

Si vous avez des questions ou des problèmes, n'hésitez pas à ouvrir une issue sur le dépôt GitHub.
