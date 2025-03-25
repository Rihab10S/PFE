package com.pfe.back.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.back.Services.ArticleService;
import com.pfe.back.Services.StockPrincipalService;
import com.pfe.back.Services.SubStockService;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.StockPrincipal;
import com.pfe.back.entities.SubStock;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.getArticleById(id);
        return article.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/StockPrinci")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.saveArticle(article));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article newArticle) {
        return articleService.getArticleById(id)
                .map(article -> {
                    article.setNom(newArticle.getNom());
                    article.setDescription(newArticle.getDescription());
                    article.setQuantiteDisponible(newArticle.getQuantiteDisponible());
                    article.setPrixUnitaire(newArticle.getPrixUnitaire());
                    article.setDateExpiration(newArticle.getDateExpiration());
                    article.setStockPrincipal(newArticle.getStockPrincipal());
                    article.setSubStock(newArticle.getSubStock());
                    return ResponseEntity.ok(articleService.saveArticle(article));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
       
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    // Afficher les articles d'un StockPrincipal donné
    @GetMapping("/stock-principal/{stockPrincipalId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Article>> getArticlesByStockPrincipal(@PathVariable Long stockPrincipalId) {
        List<Article> articles = articleService.getArticlesByStockPrincipal(stockPrincipalId);
        return articles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(articles);
    }

    // Afficher les articles d'un SousStock donné
    @GetMapping("/sub-stock/{subStockId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Article>> getArticlesBySubStock(@PathVariable Long subStockId) {
        List<Article> articles = articleService.getArticlesBySubStock(subStockId);
        return articles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(articles);
    }

    // Ajouter un article à un sous-stock
    @PostMapping("/{articleId}/move-to-substock/{subStockId}/{quantity}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Article> moveArticleToSubStock(@PathVariable Long articleId, 
                                                         @PathVariable Long subStockId, 
                                                         @PathVariable int quantity) {
        try {
            Article updatedArticle = articleService.moveArticleToSubStock(articleId, subStockId, quantity);
            return ResponseEntity.ok(updatedArticle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<byte[]> exportArticlesToExcel() throws IOException {
        List<Article> articles = articleService.getAllArticles();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Articles");
    
        // En-têtes de colonnes
        Row headerRow = sheet.createRow(0);
        String[] headers = { "Nom", "Description", "Quantité Disponible", "Prix Unitaire", "Date Expiration", "Référence", "Fournisseur", "Stock Principal", "Sous Stock" };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
        int rowNum = 1;
        for (Article article : articles) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(article.getNom());
            row.createCell(1).setCellValue(article.getDescription());
            row.createCell(2).setCellValue(article.getQuantiteDisponible());
            row.createCell(3).setCellValue(article.getPrixUnitaire());
           
    
            String formattedDate = (article.getDateExpiration() != null) ? dateFormat.format(article.getDateExpiration()) : "";
            row.createCell(4).setCellValue(formattedDate);
        
            row.createCell(5).setCellValue(article.getReference()); 
            row.createCell(6).setCellValue(article.getFournisseur()); 
            row.createCell(7).setCellValue(article.getStockPrincipal().getNom());
            row.createCell(8).setCellValue(article.getSubStock() != null ? article.getSubStock().getNomSousStock() : "N/A");
        }
    
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        byte[] excelFile = outputStream.toByteArray();
    
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        responseHeaders.add("Content-Disposition", "attachment; filename=StockPrincipale.xlsx");
    
        return new ResponseEntity<>(excelFile, responseHeaders, HttpStatus.OK);
    }
    @PostMapping("/import")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<String> importArticlesFromExcel(@RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("Aucun fichier sélectionné");
    }

    Workbook workbook = new XSSFWorkbook(file.getInputStream());
    Sheet sheet = workbook.getSheetAt(0);
    Iterator<Row> rowIterator = sheet.iterator();

    if (rowIterator.hasNext()) {
        rowIterator.next(); // Ignorer l'en-tête
    }

    while (rowIterator.hasNext()) {
        Row row = rowIterator.next();

        // Vérifier si la ligne est vide
        if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null) {
            continue;
        }

        String nom = getCellStringValue(row.getCell(0));
        String description = getCellStringValue(row.getCell(1));

        int quantiteDisponible = getCellNumericValue(row.getCell(2), 0);
        Double prixUnitaire = getCellNumericValue(row.getCell(3), 0.0);
        Date dateExpiration = getCellDateValue(row.getCell(4));
        String reference = getCellStringValue(row.getCell(5));
        String fournisseur = getCellStringValue(row.getCell(6));

        String stockPrincipalStr = getCellStringValue(row.getCell(7));
        String sousStockStr = getCellStringValue(row.getCell(8));

        // Traitement spécial pour "principal" -> 1
        Integer stockPrincipalId = null;
        if (!stockPrincipalStr.isEmpty()) {
            if ("principal".equalsIgnoreCase(stockPrincipalStr.trim())) {
                stockPrincipalId = 1; // Remplacer "principal" par 1
            } else {
                stockPrincipalId = tryParseInt(stockPrincipalStr); // Tenter une conversion en entier
            }
        }

        Integer sousStockId = tryParseInt(sousStockStr); // Sous-stock peut être vide

        // Vérification des données obligatoires
        if (nom.isEmpty() || description.isEmpty()) {
            continue;
        }

        // Vérifier si l'article existe déjà dans la base de données
        if (articleService.existsByReference(reference)) {
            continue;
        }

        // Récupérer les objets StockPrincipal et SubStock s'ils existent
        StockPrincipal stockPrincipal = (stockPrincipalId != null) ?
            StockPrincipalService.findById(stockPrincipalId).orElse(null) : null;

        SubStock subStock = (sousStockId != null) ?
            SubStockService.findById(sousStockId).orElse(null) : null;

        // Création de l'article avec gestion des relations
        Article article = new Article(nom, description, prixUnitaire, quantiteDisponible, dateExpiration, reference, fournisseur, stockPrincipal, subStock);

        // Enregistrer l'article dans la base de données
        articleService.saveArticle(article);
    }

    workbook.close();
    return ResponseEntity.ok("Importation réussie");
}

// Méthode pour convertir une chaîne en Integer
private Integer tryParseInt(String value) {
    try {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value.trim()) : null;
    } catch (NumberFormatException e) {
        return null; // Retourne null si la conversion échoue
    }}

    // Helper method to safely get string value from a cell
    private String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
    
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Format the date as a string
                } else {
                    return String.valueOf(cell.getNumericCellValue()); // Convert numeric value to string
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Helper method to safely get numeric values (Integer or Double)
    private <T> T getCellNumericValue(Cell cell, T defaultValue) {
        if (cell == null) {
            return defaultValue;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            if (defaultValue instanceof Integer) {
                return (T) Integer.valueOf((int) cell.getNumericCellValue());
            } else if (defaultValue instanceof Double) {
                return (T) Double.valueOf(cell.getNumericCellValue());
            }
        }

        return defaultValue;
    }

    // Helper method to safely get date values
    private Date getCellDateValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return null;
    }
}