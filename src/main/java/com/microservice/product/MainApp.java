package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("   TP 5 : INTEGRATION SPRING & HIBERNATE DANS UN MICROSERVICE    ");
        System.out.println("==================================================================");

        // 1. Initialisation du conteneur Spring IoC
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("\n[OK] Contexte Spring charge avec succes !");
        
        // 2. Recuperation du bean ProductService
        ProductService productService = context.getBean(ProductService.class);
        
        // 3. Ajout de produits
        System.out.println("\n--- 1. AJOUT DE NOUVEAUX PRODUITS ---");
        Product p1 = new Product("Microservice Gateway", 149.99, 20);
        Product p2 = new Product("Dell XPS 15", 1450.00, 5);
        Product p3 = new Product("Ecran Dell UltraSharp 27", 349.50, 12);
        
        productService.addProduct(p1);
        System.out.println(" -> Produit cree : " + p1);
        productService.addProduct(p2);
        System.out.println(" -> Produit cree : " + p2);
        productService.addProduct(p3);
        System.out.println(" -> Produit cree : " + p3);
        
        // 4. Consultation de la liste des produits
        System.out.println("\n--- 2. LISTE DE TOUS LES PRODUITS EN BASE ---");
        List<Product> products = productService.getAllProducts();
        for (Product p : products) {
            System.out.println(String.format("   [ID: %d] %-25s | Prix: %8.2f DH | Qte: %3d", 
                    p.getId(), p.getName(), p.getPrice(), p.getQuantity()));
        }
        System.out.println("   => Total des produits en base : " + products.size());

        // 5. Recherche par ID
        System.out.println("\n--- 3. RECHERCHE PAR ID ---");
        Product found = productService.getProductById(p1.getId());
        System.out.println(" -> Produit recherche (ID " + p1.getId() + ") : " + found);

        // 6. Fermeture du contexte et nettoyage
        System.out.println("\n==================================================================");
        System.out.println("   EXECUTION TERMINEE AVEC SUCCES                                ");
        System.out.println("==================================================================");
        context.close();
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
