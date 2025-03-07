package org.example.tap2025.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;

public class PuzzlePiece extends ImageView {
    private final int correctRow;
    private final int correctCol;
    private final Rompecabezas game;
    private boolean locked;
    private double startX, startY;

    public PuzzlePiece(String imagePath, int row, int col, Rompecabezas game) {
        super(loadImage(imagePath));
        this.correctRow = row;
        this.correctCol = col;
        this.game = game;
        this.locked = false;
        setFitWidth(Rompecabezas.TILE_SIZE);
        setFitHeight(Rompecabezas.TILE_SIZE);

        // Eventos para mover la pieza con el mouse
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);
    }

    private static Image loadImage(String imagePath) {
        try {
            String fullPath = "/image/" + imagePath;
            if (PuzzlePiece.class.getResource(fullPath) == null) {
                System.out.println("❌ Imagen no encontrada: " + fullPath);
                return null;
            }
            return new Image(PuzzlePiece.class.getResourceAsStream(fullPath));
        } catch (Exception e) {
            System.out.println("❌ Error cargando imagen: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    public int getCorrectRow() {
        return correctRow;
    }

    public int getCorrectCol() {
        return correctCol;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isInCorrectPosition() {
        Integer currentRow = GridPane.getRowIndex(this);
        Integer currentCol = GridPane.getColumnIndex(this);
        return currentRow != null && currentCol != null &&
                currentRow == correctRow && currentCol == correctCol;
    }

    private void handleMousePressed(MouseEvent event) {
        if (!locked) {
            startX = event.getSceneX();
            startY = event.getSceneY();
            System.out.println("Moviendo pieza desde [" + correctRow + ", " + correctCol + "]");
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (!locked) {
            double offsetX = event.getSceneX() - startX;
            double offsetY = event.getSceneY() - startY;
            setTranslateX(getTranslateX() + offsetX);
            setTranslateY(getTranslateY() + offsetY);
            startX = event.getSceneX();
            startY = event.getSceneY();
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (!locked) {
            // Obtener el nuevo índice de fila y columna en la cuadrícula
            final int newRow = (int) Math.round((getLayoutY() + getTranslateY()) / Rompecabezas.TILE_SIZE);
            final int newCol = (int) Math.round((getLayoutX() + getTranslateX()) / Rompecabezas.TILE_SIZE);

            // Asegurar que la pieza no salga del tablero
            final int clampedRow = Math.max(0, Math.min(game.getGridSize() - 1, newRow));
            final int clampedCol = Math.max(0, Math.min(game.getGridSize() - 1, newCol));

            // Mover la pieza a la nueva celda en la cuadrícula
            Platform.runLater(() -> {
                GridPane.setRowIndex(this, clampedRow);
                GridPane.setColumnIndex(this, clampedCol);
                setTranslateX(0);
                setTranslateY(0);
            });

            // Verificar si la pieza está en la posición correcta
            if (isInCorrectPosition()) {
                setLocked(true);
                System.out.println(" Pieza bloqueada en la posición correcta.");
            }

            // Comprobar si el rompecabezas está completo
            Platform.runLater(() -> {
                if (game.isPuzzleSolved()) {
                    System.out.println(" Rompecabezas completado.");
                }
            });
        }
    }
}


























